/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.arrays;

import static com.example.cviceni.arrays.ArrayTools.*;
import static com.example.cviceni.arrays.Sorting.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ugla
 */
public class ArrayBasics
{
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);
    
    private static void test_local_array()
    {
        int [] a = sequence(10);

        System.out.println("The array is initialized with a growing sequence of numbers:");
        printArray(a);   
        System.out.println("Setting the first element to 12");
        a[0] = 12;
        printArray(a);   
        int b = 18;
        System.out.println("Setting the second element to the value of local variable");
        a[1] = b;
        printArray(a);   
        System.out.print("Insert the new value of the third element: ");
        a[2] = input.nextInt();
        printArray(a);
        System.out.println("Subtracting 10 from the last three elements: ");
        if(a.length > 2)
            for (int i = a.length-3; i < a.length; i++)
                a[i] -= 10;
        printArray(a);
        System.out.print("Insert the position where to copy the above value (0.." + (a.length-1) + "): ");
        int index = input.nextInt();
        a[index] = a[2];
        System.out.println("The resulting array:");
        printArray(a);
    }

    private static void test_array_class()
    {
        Array array = Array.randomSequence(10,-5,5);
                
        System.out.println("A randomly generated array with values from -5 to 5:");
        array.print();
        for (int i = 0; i < 8; i++)
            array.insertElement(i+10,4);
        System.out.println("8 values from 10 to 17 inserted at position 4:");
        array.print();
        System.out.println("Removing second element");
        array.removeElement(1);
        array.print();
        
        System.out.println("The smallest value is " + array.minValue() + ", the largest value is " + array.maxValue()); 
        System.out.println("Appending 48");
        array.append(48);
        System.out.println("Elements used: " + array.size() + "/" + array.getData().length);
        System.out.println("Inserting -49 at the front");
        array.insertAtFront(-49);
        array.print();
        System.out.println("Sorting the array");
        System.out.println("Elements used: " + array.size() + "/" + array.getData().length);
        Sorting.shakeSort(array.getData());
        array.print();
        System.out.println("Elements used: " + array.size() + "/" + array.getData().length);
    }

    private static void merge_test()
    {
        int [] a = randomSequence(6,0,20);
        int [] b = randomSequence(6,0,20);
    
        System.out.println("Input arrays:");
        printArray(a);
        printArray(b);
        insertionSort(a);
        insertionSort(b);
        System.out.println("\nInput sorted:");
        printArray(a);
        printArray(b);
        
        int [] c = merge(a,b);

        System.out.println("\nMerged array:");
        printArray(c);
    }
    
    private static void search_test()
    {
        int count = 10000;
        int range = 1000;
        Array array = Array.randomSequence(count,-range, range);
        insertionSort(array.getData());
        
        System.out.println("Sorted input:");
        array.print();
        
        long tic = System.nanoTime();
        int index1 = array.indexOf(0);
        long tac = System.nanoTime(); 
        long linearSearchTime = tac - tic;
        
        tic = System.nanoTime();
        int index2 = array.binarySearch(0);
        tac = System.nanoTime();
        long binarySearchTime = tac - tic;
           
        System.out.println("\nLinear search algorithm:");
        if (index1 > -1)
            System.out.print("Zero found at position " + index1);
        else
            System.out.print("Zero not found");
        System.out.println(" [" + linearSearchTime + " ns]");

        System.out.println("\nBinary search algorithm:");
        if (index2 > -1)
            System.out.print("Zero found at position " + index2);
        else
            System.out.print("Zero not found");
        System.out.println(" [" + binarySearchTime + " ns]");
    }

    public static void sort_test()
    {
        int N = 1 << 24;
        long sort1 = 0;
        long sort2 = 0;
        long genTime = 0;
        
        int [] a;
        int [] b;
        System.out.println("N = " + N);
        for (int i = 0; i < 1; i++)
        {
            long tic = System.nanoTime();
            a = randomSequence(N,0,N);
            
            b = new int [a.length];
            System.arraycopy(a, 0, b, 0, b.length);

            long tac = System.nanoTime();
            genTime += tac - tic; 

            tic = System.nanoTime();
            mergeSort(a);
//            printArray(a);
            tac = System.nanoTime();
            sort1 += tac - tic;
            
            tic = System.nanoTime();
            quickSort(b);
//            printArray(b);
            tac = System.nanoTime();
            sort2 += tac - tic;
        }
        System.out.println("Generation time: " + genTime/1.0E+6 + " ms");
        System.out.println("Sort time1: " + sort1/1.0E+6 + " ms");
        System.out.println("Sort time2: " + sort2/1.0E+6 + " ms");

    } 

    private static void uvod()
    {
        Array a = Array.sequence(15);
        
        a.print();
        a.removeElement(2);
        a.removeElement(6);
        a.print();
        a.insertElement(-1,1);
        a.insertAtFront(-2);
        a.print();
        a.insertElement(7,5);
        a.print();
        a.append(18);
        a.print();
        a.removeValue(9);
        a.removeValue(90);
        a.print();
    }
        
    public static void main(String[] args)
    {
//        test_local_array();
//        test_array_class();
        sort_test();
//        search_test();
//        merge_test();
        //uvod();
    }
}
