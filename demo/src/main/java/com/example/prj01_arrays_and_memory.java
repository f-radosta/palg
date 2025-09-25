package com.example;

public class prj01_arrays_and_memory {

    public static void main(String[] args) {
        int rows = 4000;
        int cols = 4000;

        System.out.printf("Velikost matice: %d x %d\n", rows, cols);

        double[][] a = allocateAndFill(rows, cols);

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
        double[][] b = doTransposition(a);
        double sum3 = sumRowMajor(b);
        long t5 = System.nanoTime();

        double rowTime = (t1 - t0) / 1_000_000_000.0;
        double colTime = (t3 - t2) / 1_000_000_000.0;
        double transpositionTime = (t5 - t4) / 1_000_000_000.0;

        System.out.printf("sum A: %f\n", sum);
        System.out.printf("sum B: %f\n", sum2);
        System.out.printf("sum C: %f\n", sum3);

        System.out.printf("Po řádcích: %.3fs\n", rowTime);
        System.out.printf("Po sloupcích: %.3fs\n", colTime);
        System.out.printf("Po transpozici: %.3fs\n", transpositionTime);
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
    private static double[][] doTransposition(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        double[][] b = new double[cols][rows];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            double[] aRow = a[rowIndex];
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                b[colIndex][rowIndex] = aRow[colIndex];
            }
        }
        return b;
    }

}
