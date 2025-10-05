package com.example;

public class prj01_arrays_and_memory {

    public static void main(String[] args) {
        int rows = 1000;
        int cols = 1000;

        System.out.printf("Velikost matice: %d x %d\n", rows, cols);

        double[][] a = allocateAndFill(rows, cols);
        double[][] b = allocateAndFill(cols, rows);
        double[][] c = allocateAndFill(cols, rows);
        double[][] tr = allocateAndFill(rows, cols);
        
        

        // Měření: po řádcích
        long t0 = System.nanoTime();
        double sum = sumRowMajor(a);
        long t1 = System.nanoTime();

        // Měření: po sloupcích
        long t2 = System.nanoTime();
        double sum2 = sumColMajor(a);
        long t3 = System.nanoTime();

        // Měření: po řádcích po transpozici ze sloupců
        long t4 = System.nanoTime();
        doTransposition(a, b);
        double sum3 = sumRowMajor(b);
        long t5 = System.nanoTime();

        // Měření: nasobeni matice
        long tn0 = System.nanoTime();
        doTransposition(b, tr);
        matrixMultiply(a, tr, c);
        long tn1 = System.nanoTime();

        // Měření: transpozice se změněným pořadím smyček (lepší lokalita zápisu)
        long t6 = System.nanoTime();
        double[][] cc = doTranspositionSwapped(a);
        double sum4 = sumRowMajor(c);
        long t7 = System.nanoTime();

        // Měření: dlaždicovaná (tiled) transpozice
        int block = 128; // lze zkusit 32/64/128 dle cache
        long t8 = System.nanoTime();
        double[][] d = doTranspositionTiled(a, block);
        double sum5 = sumRowMajor(d);
        long t9 = System.nanoTime();

        // Měření: transpozice pomocí plochého (1D) pole
        long t10 = System.nanoTime();
        double[] aFlat = toFlat(a);                   // rows x cols, row-major
        double[] bFlat = transposeFlat(aFlat, rows, cols); // cols x rows
        double[][] e = to2D(bFlat, cols, rows);       // zpět na 2D kvůli stejné kontrole sumy
        double sum6 = sumRowMajor(e);
        long t11 = System.nanoTime();

        double rowTime = (t1 - t0) / 1_000_000_000.0;
        double colTime = (t3 - t2) / 1_000_000_000.0;
        double transpositionTime = (t5 - t4) / 1_000_000_000.0;
        double transpositionSwappedTime = (t7 - t6) / 1_000_000_000.0;
        double transpositionTiledTime = (t9 - t8) / 1_000_000_000.0;
        double transpositionFlatTime = (t11 - t10) / 1_000_000_000.0;

        double multTime = (tn1 - tn0) / 1_000_000_000.0;

        System.out.printf("sum A: %f\n", sum);
        System.out.printf("sum B: %f\n", sum2);
        System.out.printf("sum C: %f\n", sum3);
        System.out.printf("sum D: %f\n", sum4);
        System.out.printf("sum E: %f\n", sum5);
        System.out.printf("sum F: %f\n", sum6);

        System.out.printf("Po řádcích: %.3fs\n", rowTime);
        System.out.printf("Po sloupcích: %.3fs\n", colTime);
        System.out.printf("Po transpozici: %.3fs\n", transpositionTime);
        System.out.printf("Po transpozici (swapped loops): %.3fs\n", transpositionSwappedTime);
        System.out.printf("Po transpozici (tiled %d): %.3fs\n", block, transpositionTiledTime);
        System.out.printf("Po transpozici (flat 1D): %.3fs\n", transpositionFlatTime);

        System.out.printf("Nasobeni matice: %.3fs\n", multTime);
    }

    private static void matrixMultiply(double[][] a, double[][] b, double[][] c) {

        int m = a.length;
        int n = b[0].length;
        int kk = a[0].length;
        int p = b.length;

        if (kk != p) {
            throw new IllegalArgumentException("Matice nelze nasobit");
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    c[i][j] += a[i][k] * b[j][k];
                }
            }
        }


    }

    private static double[][] allocateAndFill(int rows, int cols) {
        double[][] a = new double[rows][];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            a[rowIndex] = new double[cols];
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                a[rowIndex][colIndex] = Math.random();
            }
        }
        return a;
    }

    // Průchod po řádcích
    private static double sumRowMajor(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double sum = 0.0;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            double[] currentRow = a[rowIndex]; // nechávám lokální referenci pro rychlejší přístup
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                sum += currentRow[colIndex];
            }
        }
        return sum;
    }

    // Průchod po sloupcích
    private static double sumColMajor(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double sum = 0.0;
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                sum += a[rowIndex][colIndex];
            }
        }
        return sum;
    }

    // Provede transpozici matice
    private static void doTransposition(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            double[] aRow = a[rowIndex];
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                b[colIndex][rowIndex] = aRow[colIndex];
            }
        }
    }

    // Transpozice se změněným pořadím smyček: lepší lokalita pro zápis do b
    private static double[][] doTranspositionSwapped(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] b = new double[cols][rows];
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            double[] bRow = b[colIndex]; // zapisujeme sekvenčně do řádku b
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                bRow[rowIndex] = a[rowIndex][colIndex];
            }
        }
        return b;
    }

    // Dlaždicovaná (tiled) transpozice: lepší lokalita čtení i zápisu v blocích
    private static double[][] doTranspositionTiled(double[][] a, int blockSize) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] b = new double[cols][rows];
        for (int rowBlock = 0; rowBlock < rows; rowBlock += blockSize) {
            int rEnd = Math.min(rowBlock + blockSize, rows);
            for (int colBlock = 0; colBlock < cols; colBlock += blockSize) {
                int cEnd = Math.min(colBlock + blockSize, cols);
                for (int r = rowBlock; r < rEnd; r++) {
                    double[] aRow = a[r];
                    for (int c = colBlock; c < cEnd; c++) {
                        b[c][r] = aRow[c];
                    }
                }
            }
        }
        return b;
    }

    // Převede 2D pole na ploché (row-major): index = r * cols + c
    private static double[] toFlat(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double[] flat = new double[rows * cols];
        int k = 0;
        for (int r = 0; r < rows; r++) {
            double[] row = a[r];
            for (int c = 0; c < cols; c++) {
                flat[k++] = row[c];
            }
        }
        return flat;
    }

    // Transponuje ploché pole: vstup je rows x cols (row-major), výstup je cols x rows (row-major)
    private static double[] transposeFlat(double[] flat, int rows, int cols) {
        double[] out = new double[rows * cols]; // velikost stejná, ale rozměry prohozené
        for (int r = 0; r < rows; r++) {
            int rBase = r * cols;
            for (int c = 0; c < cols; c++) {
                // v out je řádek c o délce rows
                out[c * rows + r] = flat[rBase + c];
            }
        }
        return out;
    }

    // Převede ploché pole zpět na 2D matici rozměrů rows x cols (row-major)
    private static double[][] to2D(double[] flat, int rows, int cols) {
        double[][] a = new double[rows][cols];
        int k = 0;
        for (int r = 0; r < rows; r++) {
            double[] row = a[r];
            for (int c = 0; c < cols; c++) {
                row[c] = flat[k++];
            }
        }
        return a;
    }

}
