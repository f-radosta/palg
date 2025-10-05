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
public class Vector
{
    final int n;

    double [] d;

    public Vector(int n)
    {
        this.n = n;
        d = new double[n];
    }

    public Vector(double [] v)
    {
        this.n = v.length;
        this.d = v;
    }

    public int size()
    {
        return n;
    }
    
    public void swapElements(int i, int k)
    {
        if (i < 0 || i >= n || k < 0 || k >= n)
            System.out.println("Element index out of range!");
        else
        {
            double tmp = d[i];
            d[i] = d[k];
            d[k] = tmp;
        }    
    }

    public void print()
    {
        print("% 04.4f");
    }
    
    public void print(String tag)
    {
        for(int i = 0; i < n; i++)
            System.out.format(tag + "\n", d[i]);
        System.out.println();
    }
}
