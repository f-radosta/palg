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
public class Fibonacci
{
    static int index = 45;
    static long n_calls = 0;

    
    static long fibo_iter(int n)
    {
        long [] F = new long[index+2];
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i <= n; i++)
            F[i] = F[i-1] + F[i-2];
        
        return F[n];
    }

    static long fibo_rec(int n)
    {
        n_calls++;
        if (n < 2)
            return n;
        else
            return fibo_rec(n-1) + fibo_rec(n-2);
    }        
            

    
    public static void main(String[] args)
    {
        //System.out.println("F("+index+")= " + fibo_iter(index));
        System.out.println("F("+index+")= " + fibo_rec(index));
        System.out.println("Number of recursive calls: " + n_calls);
    }
}
