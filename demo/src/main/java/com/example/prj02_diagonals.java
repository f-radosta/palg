package com.example;

public class prj02_diagonals {
    public static void main(String[] args) {

        int[][] matice = new int[1<<14][1<<14];

        // vytvorAVypln(3);
        // zobraz(matice);

        // matice = pokus(5);
        // zobraz(matice);

        // matice = pokus(6);
        // zobraz(matice);

        // matice = vytvorAVypln(11);
        // zobraz(matice);

        // matice = vytvorAVypln(16);
        // zobraz(matice);

        long t0 = System.nanoTime();
        int[][] matice2 = vytvorAVypln(matice);
        long t1 = System.nanoTime();
        System.out.println("stara funkce pocet ms: " + (t1-t0)/1_000_000.0);
        zobraz(matice2);

        long t2 = System.nanoTime();
        matice2 = pokus(matice);
        long t3 = System.nanoTime();
        System.out.println(" nova funkce pocet ms: " + (t3-t2)/1_000_000.0);
        zobraz(matice2);

        long t4 = System.nanoTime();
        matice2 = pokus2(matice);
        long t5 = System.nanoTime();
        System.out.println(" nova funkce 2 pocet ms: " + (t5-t4)/1_000_000.0);
        zobraz(matice2);
    }

    private static void zobraz(int[][] matice) {
        if (matice.length < 16) {
            for (int i = 0; i < matice.length; i++) {
                System.out.println();
                for (int j = 0; j < matice.length; j++) {
                    System.out.printf("%d ", matice[i][j]);
                }
            }
            System.out.println();
        } else {

            System.out.println("velikost matice: " + matice.length);
        }
    }

    private static int[][] vytvorAVypln(int[][] matice) {
        int d = matice.length;

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {

                if (i == j || i == d - 1 - j) {
                    matice[i][j] = 0;
                } else if (i < j) {
                    if (j >= d - i) {
                        matice[i][j] = 2;
                    } else {
                        matice[i][j] = 1;
                    }
                } else if (i > j) {
                    if (i >= d - j) {
                        matice[i][j] = 3;
                    } else {
                        matice[i][j] = 4;
                    }
                }
                
            }
        }

        return matice;
    }

    private static int[][] pokus(int[][] matice) {
        int d = matice.length;

        for (int i = 0; i < d/2; i++) {
            for (int j = i+1; j < d-1-i; j++) {

                matice[i][j] = 1;
                matice[j][i] = 4;
                matice[d-1-i][j] = 3;
                matice[j][d-1-i] = 2;
                
            }
        }

        return matice;
    }
        
    private static int[][] pokus2(int[][] matice) {
        int d = matice.length;

        for (int i = 0; i < d/2; i++) {
            for (int j = i+1; j < d-1-i; j++) {

                matice[i][j] = 1;
                matice[d-1-i][j] = 3;
                
            }
            for (int j = 0; j < i; j++) {

                matice[i][j] = 4;
                matice[d-1-i][j] = 4;
                matice[i][d-1-j] = 2;
                matice[d-1-i][d-1-j] = 2;

            }
        }

        if (d % 2 == 1) {
            int i = d/2;
            for (int j = 0; j < i; j++) {

                matice[i][j] = 4;
                matice[d-1-i][j] = 4;
                matice[i][d-1-j] = 2;
                matice[d-1-i][d-1-j] = 2;
            }
            
        }

        return matice;
    }

    
}


// puvodni cas 4
// zakomentovana 1 a 3 - 3.5
// zakomentovana 2 a 4 - 3





// 00 01 02 03 04
// 10 11 12 13 14
// 20 21 22 23 24
// 30 31 32 33 34
// 40 41 42 43 44
