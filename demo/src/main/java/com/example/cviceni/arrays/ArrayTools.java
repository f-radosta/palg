/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.arrays;

/**
 *
 * @author ugla
 */
import java.util.*;


public class ArrayTools
{
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    
    public static int [] arithmeticSequence(int count, int value, int increment)
    {
        int [] a = new int[count];
        for (int i = 0; i < count; i++)
        {
            a[i] = value;
            value += increment;
        }

        return a;
    }

    public static int [] geometricSequence(int count, int value, int quotient)
    {
        int [] a = new int[count];
        for (int i = 0; i < count; i++)
        {
            a[i] = value;
            value *= quotient;
        }

        return a;
    }
    
    public static int [] randomSequence(int count, int min, int max)
    {
        int [] a = new int[count];
        for (int i = 0; i < count; i++)
            a[i] = min + random.nextInt(max - min + 1);
        
        return a;
    }
    
    public static int binarySearch(int [] a, int value)
    {
        int start = 0;
        int end = a.length;
        
        while (end - start > 0)
        {
            int mid = (start + end) / 2;
            if (value == a[mid])
               return mid;
            else
                if (value < a[mid])
                    end = mid-1;
                else
                    start = mid+1;
        }
        return (value  == a[start]) ? start : -1;
    }

    public static int [] sequence(int count)
    {
        return arithmeticSequence(count,0,1);
    }

    public static void printArray(int [] a)
    {
        printArray(a,0,a.length);
    }

    public static void printArray(int [] a, int lower, int upper, int barrier)
    {
        for(int i = lower; i < upper; i++) {
            System.out.format("%d ", a[i]);
            if (i == barrier)
                System.out.print("|");
        }
        System.out.println();
    }

    public static void printArray(int [] a, int pivot)
    {
        printArray(a, 0, a.length, pivot);
    }

    public static void printReverse(int [] a)
    {
        printReverse(a,0,a.length);
    }
    
    public static void printArray(int [] a, int lower, int upper) 
    {
        for(int i = lower; i < upper; i++)
            System.out.format("%d ", a[i]);
        System.out.println();
    }

    public static void printReverse(int [] a, int lower, int upper) 
    {
        for(int i = upper-1; i >= lower; i--)
 
            System.out.format("%d ", a[i]);
        System.out.println();
    }

    public static String arrayToString(int [] a)
    {
        return ArrayTools.arrayToString(a,0,a.length);
    }

    public static String arrayToString(int [] a, int lower, int upper)
    {
        String result = "";
        for (int i = lower; i < upper; i++)
            result += a[i] + " ";
        
        return result;
    }

    public static String reverseArrayToString(int [] a)
    {
        return reverseArrayToString(a,0,a.length);
    }

    public static String reverseArrayToString(int [] a, int lower, int upper)
    {
        String result = "";
        for (int i = upper-1; i >= lower; i--)
            result += a[i] + " ";

        return result;
    }

    public static int min(int[] a) throws ArrayIndexOutOfBoundsException
    {
        int min = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] < min)
                min = a[i];
        return min;
    }

    public static int max(int[] a)
    {
        try {
            int max = a[0];
            for (int i = 1; i < a.length; i++)
                if (a[i] > max)
                    max = a[i];
            return max;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Pole je prazdne!");
            return 0;
        }
    }
}
