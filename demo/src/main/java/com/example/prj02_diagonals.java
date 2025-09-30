package com.example;

public class prj02_diagonals {
    public static void main(String[] args) {

        int[][] matice = vytvorAVypln(3);
        zobraz(matice);

        matice = vytvorAVypln(5);
        zobraz(matice);

        matice = vytvorAVypln(6);
        zobraz(matice);

        matice = vytvorAVypln(11);
        zobraz(matice);

        matice = vytvorAVypln(16);
        zobraz(matice);
    }

    private static void zobraz(int[][] matice) {
        for (int i = 0; i < matice.length; i++) {
            System.out.println();
            for (int j = 0; j < matice.length; j++) {
                System.out.printf("%d ", matice[i][j]);
            }
        }
        System.out.println();
    }

    private static int[][] vytvorAVypln(int delka) {
        int[][] matice = new int[delka][delka];
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
}



// 00 01 02 03 04
// 10 11 12 13 14
// 20 21 22 23 24
// 30 31 32 33 34
// 40 41 42 43 44
