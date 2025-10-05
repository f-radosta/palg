/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.basics;
/**
 *
 * @author FIM.UHK
 */
import com.example.cviceni.arrays.ArrayTools;
import java.util.*;


import static com.example.cviceni.arrays.ArrayTools.arrayToString;
import static com.example.cviceni.arrays.ArrayTools.arrayToString;
import static com.example.cviceni.arrays.ArrayTools.reverseArrayToString;
import static com.example.cviceni.arrays.ArrayTools.reverseArrayToString;


public class Sequences
{
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);


    private static void countSixes()
    {
        final int count = 30;
        
        int sixes = 0;
        
        System.out.println("Generujeme nahodna cisla / Generating random numbers between 1 and 6:");
        for(int i = 0; i < count; i++)
        {
            int rnd = 1 + random.nextInt(6);
            System.out.print(rnd + " ");
            if (rnd == 6)
                sixes++;
        }
        System.out.format("\nCelkem padlo sestek / Number of sixes was: %d.\n", sixes);
    }

    private static void countEvenOdd()
    {
        System.out.println("Zadavejte cisla, vstup ukoncite nulou / Insert a sequence of numbers terminated by 0:");
        int r = input.nextInt();

        int even = 0;
        int odd = 0;

        while (r != 0)
        {
            if (r % 2 == 0)
                even++;
            else
                odd++;
                
            r = input.nextInt();
        }

        System.out.println("Sudych cisel / number of even values: " + even);
        System.out.println("Lichych cisel / number of odd values " + odd);
    }

    private static void splitEvenOdd()
    {
        final int count = 30;
        int odd [] = new int[count];
        int even [] = new int[count];
        int p = 0;
        int q = 0;

        System.out.println("Generujeme nahodna cisla / Generating random numbers");
        for(int i = 0; i < count; i++)
        {
            int rnd = 1 + random.nextInt(10);
            System.out.print(rnd + " ");
            if (rnd % 2 == 0)
                even[q++] = rnd;
            else
                odd[p++] = rnd;
        }
        System.out.println();
        
        System.out.println("Licha cisla jsou / Odd numbers are: ");
        ArrayTools.printArray(odd,0,p);
        System.out.println("Suda cisla jsou / Even numbers are: ");
        ArrayTools.printArray(even,0,q);
    }

    private static void frequenciesFromSmallRange()
    {
        int count = 20;
        int range = 6;
        int [] freq = new int[range];
        System.out.format("Generujeme nahodna cisla / Generating random numbers between 1 and %d:\n", range);
        
        for(int i = 0; i < count; i++)
        {
            int r = random.nextInt(range);
            System.out.print((r+1) + " ");
            freq[r]++;
        }
        System.out.println("\nCetnosti jednotlivych hodnot byly / The values and their frequencies were:");
        for(int i = 0; i < range; i++)
            System.out.format("%d[%dx] ", i+1, freq[i]);
        System.out.println("\n");
    }

    private static void frequenciesFromLargeRange()
    {
        int count = 10;
        int range = 1000;
        int [] freq = new int[count];
        int [] values = new int[count];

        System.out.format("Generujeme nahodna cisla / Generating random numbers between 0 and %d:\n", range-1);
        int distinct = 0;
        for(int i = 0; i < count; i++)
        {
            int r = random.nextInt(range);
            System.out.print(r + " ");
            int k = 0;
            while (k < distinct && r != values[k])
                k++;

            if (k == distinct) //r tam neni / r not found
            {
                values[k] = r;
                distinct++;
            }
            freq[k]++; //kazdopadne zvysujeme cetnost / increasing frequency either way 
        }

        System.out.println("\nCetnosti jednotlivych hodnot byly / The values and their frequencies were:");
        for(int i = 0; i < distinct; i++)
            System.out.format("%d[%dx] ", values[i], freq[i]);
        System.out.println("\n");
    }

    private static void splitAtInput()
    {
        int count = 20;
        int range = 6;
        int j = 0;
        int k = count-1;
        int [] values = new int[count];
        
        System.out.format("Generujeme nahodna cisla / Generating %d random numbers between 1 and %d:\n", count, range);
        for(int i = 0; i < count; i++)
        {
            int r = 1 + random.nextInt(range);
            System.out.print(r + " ");
            if (r <= range/2)
                values[j++] = r;
            else
                values[k--] = r;
        }
        System.out.format("\nVsechna / All(%d): %s\n", count, arrayToString(values));
        System.out.format("Mensi / Smaller(%d): %s\n", j, arrayToString(values,0,j));
        System.out.format("Vetsi / Larger(%d): %s\n", count-j, reverseArrayToString(values,k+1,count));
    }

    private static void splitAtOutput()
    {
        int count = 20;
        int range = 6;
        int smallerCount = 0;
        int largerCount = 0;

        System.out.format("Generujeme nahodna cisla / Generating %d random numbers between 1 and %d:\n", count, range);
        int [] values = ArrayTools.randomSequence(count, 1, range);
        ArrayTools.printArray(values);
        
        System.out.print("Mensi / Smaller: ");
        for(int i = 0; i < count; i++)
            if (values[i] <= range/2)
            {
                smallerCount++;
                System.out.print(values[i] + " ");
            }

        System.out.print("\nVetsi / Larger: ");
        for(int i = 0; i < count; i++)
            if (values[i] > range/2)
            {
                largerCount++;
                System.out.print(values[i] + " ");
            }
        System.out.format("\nmensich / smaller (%d), vetsich / larger (%d).\n", smallerCount, largerCount);
    }

    private static void splitAsArrays()
    {
        int count = 20;
        int range = 6;

        System.out.format("Generujeme nahodna cisla / Generating %d random numbers between 1 and %d:\n", count, range);
        int [] values = ArrayTools.randomSequence(count, 1, range);
        ArrayTools.printArray(values);

        int j = 0;
        for(int i = 0; i < count; i++)
            if (values[i] <= range/2)
                j++;
        
        int [] smaller = new int[j];
        int [] larger = new int[count-j];
        
        j = 0;
        int k = 0;
        
        for(int i = 0; i < count; i++)
            if (values[i] <= range/2)
                smaller[j++] = values[i];
            else
                larger[k++] = values[i];

        System.out.format("Mensi / Smaller(%d): %s\n", j, arrayToString(smaller));
        System.out.format("Vetsi / Larger(%d): %s\n", k, arrayToString(larger));
    }

    private static void splitAsStrings()
    {
        int count = 20;
        int range = 6;

        String smaller = "";
        String larger = "";

        int smallerCount = 0;
        int largerCount = 0;

        System.out.format("Generujeme nahodna cisla / Generating %d random numbers between 1 and %d:\n", count, range);
        for(int i = 0; i < count; i++)
        {
            int r = 1 + random.nextInt(range);
            System.out.print(r + " ");
            if (r <= range/2)
            {
                smallerCount++;
                smaller += r + " ";
            }
            else
            {
                largerCount++;
                larger += r + " ";
            }
        }
        System.out.format("\nMensi / Smaller(%d): %s\n", smallerCount, smaller);
        System.out.format("Vetsi / Larger(%d): %s\n", largerCount, larger);
    }

    private static void filter()
    {
        int count = 20;
        int range = 20;
        int size = 10;
        int [] values = new int[size]; //[14,33,0..,0]
        int matching = 0;
        
        System.out.format("Generujeme nahodna cisla / Generating random numbers between 1 and %d:\n", range);
        for (int i = 0; i < count && matching < size; i++)
        {
            int r = 1 + random.nextInt(range);
            System.out.print(r + " ");
            if ((r > 12) && ((r % 7 == 0) || (r % 3 == 0)))
                values[matching++] = r;
        }
        System.out.println();
        
        if (matching > 0)
        {
            System.out.println("Cisla vyhovujici stanovenym kriteriim jsou / Values matching the criteria:");
            for (int i = 0; i < matching; i++)
                System.out.print(values[i] + " ");
            System.out.println();
        }
        else
            System.out.println("Zadne ze zadanych cisel nevyhovuje stanovenym kriteriim / No value matched given criteria.");
        
    }

    private static void minMax()
    {
        System.out.println("Zadavejte cisla, vstup ukoncite nulou / Insert a sequence of numbers terminated by 0:");
        int r = input.nextInt();

        int max = r;
        int min = r;
    
        while (r != 0)
        {
            if (r > max)
                max = r;
            if (r < min)
                min = r;
            r = input.nextInt();
        }
    
        if (min == 0)
            System.out.println("Nebyla zadana zadna nenulova cisla / No non-zero values provided.");
        else
            System.out.println("Nejvetsi z cisel / max value: " + max + " a nejmensi / min value: " + min);
    }

    private static void smallestTwo()
    {
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        System.out.println("Zadavejte cisla, vstup ukoncite nulou / Insert a sequence of numbers terminated by 0:");
        int r = input.nextInt();
        while(r != 0)
        {
            if (r < min)
            {
                min2 = min;
                min = r;
            }
            else
                if (r < min2)
                   min2 = r;
            r = input.nextInt();
        }

        if (min == Integer.MAX_VALUE)
            System.out.println("Nebyla zadana zadna nenulova cisla / No non-zero values provided.");
        else
            if (min2 == Integer.MAX_VALUE)
                System.out.format("Byla zadana jedina hodnota / Only one value provided: %d.\n", min);
            else
                System.out.format("Nejmensi hodnoty byly / The smallest values were %d, %d.\n", min, min2);
    }

    private static void sumAndAverage()
    {
        System.out.println("Zadavejte cisla, vstup ukoncite nulou / Insert a sequence of numbers terminated by 0:");
        int count = 0;
        int sum = 0;
        
        int r = input.nextInt();
        while(count < 1000 && r != 0)
        {
            sum += r;
            count++;
            r = input.nextInt();
        }
        if (count > 0)
        {
            
            System.out.format("Soucet zadanych hodnot / The sum of entered values: %d\n", sum);
            System.out.format("Prumer / Average: %4.2f.\n", (double)sum/count);
        }
        else
            System.out.println("Nebyla zadana zadna platna cisla.");
    }

    public static void main(String[] args)
    {
//        countSixes();
//        countEvenOdd();
//        splitEvenOdd();

//        frequenciesFromSmallRange();
//        frequenciesFromLargeRange();

//        splitAtInput();
//        splitAtOutput();
//        splitAsArrays();
//        splitAsStrings();

//        filter();
//        minMax();
//        smallestTwo();
//        sumAndAverage();
    }
}
