 
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.advanced;

/**
 *
 * @author bauerpe1
 */
import java.util.*;
import static java.lang.Math.*;


public class Queens
{
    private static Scanner input = new Scanner(System.in);

    static final int N = 30;
    static int [] queens = new int[N];

    static boolean keepSearching = true;
    static boolean seekAllSolutions = false;
    static boolean printSolutions = true;
    static int solutionsFound = 0;
    static int processedStates = 0;


    static void init()
    {
        for (int i = 0; i < N; i++)
            queens[i] = -1;
    }

    static boolean isThreatened(int row, int column)
    {
        for (int i = 0; i < column; i++)
            if ((queens[i] == row) || (abs(queens[i]-row) == abs(i-column)))
                return true;

        return false;
    }

    static void placeQueen(int k)
    {
        processedStates++;
        if (k == N)
        {
            keepSearching &= seekAllSolutions;
            solutionsFound++;
            if (printSolutions)
                printSolution();
        }
        else
        {
            int r = 0;
            while (keepSearching && r < N)
            {
                if (!isThreatened(r,k))
                {
                    queens[k] = r;
                    placeQueen(k+1);
                }
                r++;
            }
        }
    }

    static void place_non_rec()
    {
        init();
        int k = 0;
        while (keepSearching && k >= 0)
        {
            processedStates++;
            if (k == N)
            {
                keepSearching &= seekAllSolutions;
                solutionsFound++;
                if (printSolutions)
                    printSolution();
                k--;
            }
            else
            {
                do queens[k]++; while ((queens[k] < N) && isThreatened(queens[k],k));
                if (queens[k] == N)
                {
                    queens[k] = -1;
                    k--;
                }
                else
                    k++;
            }
        }      
    }
	
    static void printSolution()
    {
        char [][] board = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = '.';
        
        for (int i = 0; i < N; i++)
            board[queens[i]][i] = 'Q';

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(board[N-1-i][j]);
            System.out.println();
        }     
        System.out.println();
    }

    public static void main(String [] args)
    {
        placeQueen(0);
        //place_non_rec();
    
        if (seekAllSolutions)
            System.out.println("Solutions found: " + solutionsFound);
        System.out.println("Processed states: " + processedStates);
    }
}
