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
public class Board
{
    final int M;
    final int N;
    
    int [][] data;
    int usedFields = 0;
    
    public Board(int N)
    {
        this(N,N);
    }
    
    public Board(int M, int N)
    {
        this.M = M;
        this.N = N;
        
        data = new int[M][N];
        usedFields = 0;
    }

    public int area() {
        return M*N;
    }

    public int usedFields()
    {
        return usedFields;
    }
    
    public boolean filledUp()
    {
        return usedFields == M*N;
    }
            
    public boolean inside(int x, int y)
    {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    boolean isFree(int x, int y)
    {
        return data[y][x] == 0;
    }
    
    boolean isAvailable(int x, int y)
    {
        return inside(x,y) && isFree(x,y);
    }

    public void setValue (int x, int y, int value)
    {
        data[y][x] = value;
        usedFields++;
    }
    
    public int getValue(int x, int y)
    {
        return data[y][x];
    }

    public void add(int x, int y, int k)
    {
        if (isAvailable(x,y))
            setValue(x,y,k);
    }
    int wave_full(int sx, int sy) {
        setValue(sx, sy,1);
        int k = 1;
        while(!filledUp())
        {
            for (int x = 0; x < N; x++)
                for (int y = 0; y < M; y++)
                {
                    if (data[x][y] == k)
                    {
                        int[][] moves = Pieces.moves(Knight.piece);
                        for (int i = 0; i < moves.length; i++)
                            add(x+moves[i][0],y+moves[i][1], k+1);
                    }
                }
            k++;
        }
        return k;
    }

    public void clear()
    {
        usedFields = 0;

        for(int y = 0; y < M; y++)
            for(int x = 0; x < N; x++)
                data[y][x] = 0;
    }
    
    public void print()
    {
        if (N <= 20)
        {
            for(int y = 0; y < M; y++)
            {
                for(int x = 0; x < N; x++)
                    System.out.format("%2d ", data[M-1-y][x]);
                System.out.println();
            }
            System.out.println();
        }
    }
}
