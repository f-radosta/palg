/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.arrays2d;

/**
 *
 * @author ugla
 */
import java.util.*;

public class Arrays2d {

    private static double [][] matrix = {{0,2,-1,3},{4,0,-1,2},{3,1,3,0},{2,1,5,-2}};
    private static double [] rhs = {4,5,7,6};

    private static Random random = new Random();
    
    private static void init(double [][] A)
    {
        for (int i = 0; i < A.length; i++)
        {
            A[i][0] = i+1;
            for(int j = 1; j < A[0].length; j++)
                A[i][j] = A[i][0]*A[i][j-1];
        }
    }

    private static void pokus()
    {
        int [][] a = randomMatrix(6,10);
        print(a);
        preklop(a);
        print(a);
    }

    private static void preklop (int [][] a)
    {
        int M = a.length;
        int  N= a[0].length;

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N/2; j++)
            {
                int tmp = a[i][j];
                a[i][j] = a[i][N-1-j];
                a[i][N-1-j] = tmp;
            }

    }
    public static int [][] randomMatrix(int M, int N)
    {

        int [][] a = new int [M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = random.nextInt(20);

        return a;
    }

    public static void print(int [][] a) {
        int M = a.length;
        int N = a[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.format("%3d ", a[i][j]);
            System.out.println();
        }
        System.out.println();
    }


    private static void sektory() {
        final int N = 11;
        int[][] a = new int[N][N];


        for (int i = 0; i < N / 2; i++)
            for (int j = i + 1; j < N - 1 - i; j++) {
                a[i][j] = 1;
                a[j][i] = 4;
                a[j][N-1-i] = 2;
                a[N-1-i][j] = 3;
            }

        print(a);



    }

    public static void main(String[] args)
    {
//        Matrix A = new Matrix(matrix);
//        A.print();
//        Vector b = new Vector(rhs);
//        b.print();
//        A.gauss(b);
//        A.print();
//        b.print();
        sektory();
    }
}
