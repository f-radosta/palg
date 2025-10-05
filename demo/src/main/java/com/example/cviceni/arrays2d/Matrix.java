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
public class Matrix
{
    final int m;
    final int n;

    double [][] d;

    public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        d = new double[m][n];
    }
            
    public Matrix(double [][] a)
    {
        this.m = a.length;
        this.n = a[0].length;
        
        d = a;
    }
            
    public void gauss(Vector b)
    {
        double eps = 1.0e-3;

        if(m != n)
        {
            System.out.println("Matice neni ctvercova");
            return;
        }
        if(b.size() != n)
        {
            System.out.println("Rozmer prave strany neodpovida matici"); 
            return;
        }
                
        for(int k = 0; k < n; k++)
        {            
            double max = Math.abs(d[k][k]);
            int imax = k;
            for(int i = k+1; i < n; i++)
                if (Math.abs(d[i][k]) > max)
                {
                    imax = i;
                    max = Math.abs(d[i][k]);
                }
            if(max < eps)
            {
                System.out.println("Matice je singularni nebo spatne podminena");
                return;
            }
            
            if(imax != k)
            {
                swapRows(k, imax);
                b.swapElements(k, imax);
            }
            
            for(int i = k+1; i < n; i++)
            {
                double factor = d[i][k]/d[k][k];
                for(int j = k+1; j < n; j++)
                    d[i][j] -= factor*d[k][j];
                b.d[i] -= factor*b.d[k]; 
            }
            
        }
        b.d[n-1] /= d[n-1][n-1];
        for (int k = n-2; k >=0; k--)
        {
            for(int j = k+1; j < n; j++) 
                b.d[k] -= b.d[j]*d[k][j]; 
            b.d[k] /= d[k][k];
        }
    }

    public Matrix transpose()
    {
        Matrix transposed = new Matrix(n,m);
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                transposed.d[i][j] = d[j][i];
        
        return transposed;
    }

    public void swapRows(int i, int k)
    {
        if (i < 0 || i >= m || k < 0 || k >= m)
            System.out.println("Row index out of range!");
        else
            for(int j = 0; j < n; j++)
            {
                double tmp = d[i][j];
                d[i][j] = d[k][j];
                d[k][j] = tmp;
            }
    }
    
    public void swapColumns(int j, int k)
    {
        if (j < 0 || j >= n || k < 0 || k >= n)
            System.out.println("Column index out of range!");
        else
            for(int i = 0; i < m; i++)
            {
                double tmp = d[i][j];
                d[i][j] = d[i][k];
                d[i][k] = tmp;
            }
    }

    public Matrix flipHorizontally()
    {
            Matrix flipped = new Matrix(m,n);
    
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                flipped.d[i][n-1-j] = d[i][j];

        return flipped;       
    }

    public Matrix flipVertically()
    {
        Matrix flipped = new Matrix(m,n);
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                flipped.d[m-1-i][j] = d[i][j];

        return flipped;       
    }

    public void flipHorizontally_alt()
    {
        for(int j = 0; j < n/2; j++)
            swapColumns(j,n-1-j);
    }

    public void flipVertically_alt()
    {
        for(int i = 0; i < m/2; i++)
            swapRows(i, m-1-i);
    }            

    public Matrix rotateLeft()
    {
        Matrix rotated = new Matrix(n,m);
    
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                rotated.d[i][j] = d[j][n-i-1];
        
        return rotated;
    }
    
    public Matrix rotateRight()
    {
        Matrix rotated = new Matrix(n,m);

        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                rotated.d[j][m-i-1] = d[i][j];
        
        return rotated;
    }

    public void print()
    {
        print("% 04.4f");
    }

    public void print(String tag)
    {
        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
                System.out.format(tag + " ", d[i][j]);
            System.out.println();
        }      
        System.out.println();
    }
}
