package com.example.cviceni.basics;

import java.util.*;


public class Intro
{
    private static Scanner input = new Scanner(System.in);

    private static void arithmetics()
    {
        System.out.println("Zakladni aritmetika s cisly / Basic arithmetics");
        System.out.println("Zadejte dve cisla / Insert two integers:");
        int a = input.nextInt(); //nacitam hodnotu a z klavesnice / input from keyboard
        int b = input.nextInt(); //nacitam hodnotu b z klavesnice / input from keyboard

        System.out.println(a + " + " + b + " = " + (a+b)); //vyraz (a+b) musi byt v zavorkach / expression (a+b) must be in parentheses
        System.out.println(a + " - " + b + " = " + (a-b));
        System.out.println(a + " * " + b + " = " + (a*b));
        System.out.println(a + " / " + b + " = " + (a/b));
        System.out.println(a + " % " + b + " = " + (a%b));
        System.out.println("(double)" + a + " / " + b + " = " + ((double)a/b));

        System.out.println("1/2 = " + 1/2);
        System.out.println("1.0/2 = 1/2.0 = 1.0/2.0" + " = " + 1.0/2.0);
    }

    private static void min2()
    {
        System.out.println("Porovnavani cisel / Comparing numbers");
        System.out.println("Zadejte dve cela cisla / Insert 2 integers:");
        int a = input.nextInt();
        int b = input.nextInt();

        if (a == b) //nejprve testujeme rovnost / first testing equality
        {
            System.out.println("Cisla jsou stejna / Numbers are equal.");
        }
        else
        {
            int min;
            if (a < b)
                min = a;
            else
                min = b;
            System.out.println("Mensi z cisel je / Lesser of the two numbers is  " + min);   
        }
    }        

    private static void swap()
    {
        System.out.println("Prohozeni obsahu dvou promennych / Swapping two variables");
        System.out.println("Zadejte dve cela cisla / Insert 2 integers: ");
        System.out.print("a = ");
        int a = input.nextInt();
        System.out.print("b = ");
        int b = input.nextInt();
        
        int tmp = a; //vytvorime pomocnou promennou / creating auxiliary variable
        a = b;
        b = tmp;
        
        System.out.println("Po vymene / Swapped values: ");
        System.out.println("a = " + a + "\nb = " + b);
    }

    private static void xor_swap()
    {
        System.out.println("Prohozeni obsahu dvou promennych pomoci bitove XOR operace");
        System.out.println("Swapping the contents of two variables using bitwise XOR operation");
        
        System.out.print("a = ");
        int a = input.nextInt();
        System.out.print("b = ");
        int b = input.nextInt();
        
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("Po vymene / After the swap:");
        System.out.println("a = " + a + "\nb = " + b);
    }
    
    private static int factorial(int k)
    {
        return factorial_iter(k);
    }
   
    private static int factorial_iter(int n)
    {
        int result = 1;
        
        for (int i = 2; i <=n ; i++)
            result *= i;
        
        return result;
    }
    
    private static int factorial_rec(int n)
    {
        if(n < 2)
            return 1;
        else
            return n*factorial_rec(n-1);
                  
    }
    
    private static void min3()
    {
        System.out.println("Minimum ze 3 cisel / Computing minimum of 3 numbers");
        System.out.println("Zadejte 3 cela cisla / Insert 3 integers:");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        int min;
        if (a < b)
            if (a < c)
                min = a;
            else
                min = c;
        else
            if (b < c)
                min = b;
            else
                min = c;

        int min_ab = a < b ? a : b;
        min = ( min_ab < c ) ? min_ab : c;

        System.out.println("Nejmensi ze zadanych cisel je / The smallest number is " + min);
    }

    private static void sort3()
    {
        System.out.println("Serazeni 3 cisel podle velikosti / Sorting 3 numbers");
        System.out.println("Zadejte 3 cela cisla / Insert 3 integers: ");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        if (b < a)
        {
            int tmp = b;
            b = a;
            a = tmp;
        }

        if (c < b)
        {
            int tmp = c;
            c = b;
            b = tmp;
        }

        if (b < a)
        {
            int tmp = b;
            b = a;
            a = tmp;
        }

        System.out.println("Cisla serazena od nejmensiho po nejvetsi / The sorted sequence is: " + a + " " + b + " " + c );
    } 

    private static void ranges()
    {
        System.out.println("Vypocet faktorialu / Factorial computation:");
        System.out.println("Integer.SIZE = " + Integer.SIZE + " (bitu / bits)");
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE + "\n");
        
        for(int i = 10; i < 15; i++)
            System.out.println(i + "! = " + factorial(i));
        System.out.println("Vysledek 14! < 13! je zjevne chybny, ale spatne je uz 13!");
        System.out.println("Result 14! < 13! is obviously wrong, but errors start already with 13!");
        
        System.out.println("13!/12! = " + factorial(13) + " / " + factorial(12) + " = " + factorial(13)/factorial(12));
    
        System.out.println("\nLong.SIZE = " + Long.SIZE + " (bitu / bits)");
        System.out.println("Long.MIN_VALUE = " + Long.MIN_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE + "\n");
        
        long prev = factorial(12);
        long fac = prev*13;

        int i = 13; //dopocitame to bez pouziti funkce / we finish the task without using functions

        
        while(i < 50 && fac/prev == i)
        {
            System.out.println(i + "! = " + fac);
            prev = fac;
            i++;
            fac *= i; 
        }
        System.out.println(i + "! = " + fac);
        System.out.println("Zde nam to preteklo do zapornych hodnot / Overflow yielded a negative value");
    }
    
    public static void main(String[] args)
    {
        System.out.println("Ahoj vsem! / Hello world!\n");
//        arithmetics();
//        min2();
//        swap();
//        xor_swap();
//        
//        System.out.println("Pokracujeme dal! / Keep going!\n");
//        min3();
//        sort3();
//        ranges();
    }
}
