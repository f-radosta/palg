package com.example.cviceni.basics;

import java.util.*;


class Loops
{
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);
    
    private static void form()
    {
        System.out.println("Vytiskneme prezencni listinu / Printing a list of participants");
        
        String hlavicka = "Prezencni listina cviceni ALGDS / AALGD participants list";
        System.out.print("Zadejte pocet radku / Insert the number of lines: ");
        int r = input.nextInt();
        
        System.out.print("Zadejte delku radku / Enter the line's length: ");
        int d = input.nextInt();
        
        System.out.println(hlavicka);
        String radek = ") ";
        
        for (int i = 0; i < d; i++)
            radek += ".";
        
        for (int i = 0; i < r; i++)
            System.out.println((i+1) + radek);
    }

    private static void simpleSum()
    {
        System.out.println("Vypiseme cisla od 1 do 10 / Printing numbers from 1 to 10:");
        int soucet = 0;
        for (int i = 1; i <= 10; i++)
        {
            System.out.print(i + " ");
            soucet += i;
        }
        System.out.println("\nJejich soucet je / Their sum is " + soucet);
    }
    
    private static void evenOdd()
    {
        System.out.println("Vypiseme nejprve suda cisla / first the even numbers:");
        for (int i = 2; i <= 10; i += 2)
            System.out.print(i + " ");
        System.out.println("\na potom licha / then the odd numbers:");
        for (int i = 1; i <= 10; i += 2)
            System.out.print(i + " ");
        System.out.println();
    }
    
    private static void cubesBackwards()
    {    
        System.out.println("Treti mocniny cisel pozpatku / Third powers backwards:");
        for (int i = 10; i > 0; i--)
            System.out.print(power(i,3) + " ");
        System.out.println();
    }
    
    private static double power(double x, int n)
    {
        double result = 1.0;
        for(int i = 1; i <= n; i++)
            result *= x;
            
        return result;
    }
    
    private static void powersOfTwo()
    {    
        System.out.println("Vypiseme mocniny dvou az do 2^20 s vyuzitim operatoru bitoveho posunu << ");
        System.out.println("Printing powers of two up to 2^20 using bit shifting operator << ");
        int hodnota = 2;
        for (int i = 0; i < 20; i++)
        {    
            System.out.print(hodnota + " ");
            hodnota <<= 1;
        }
        System.out.println();
    }
    
    private static void sumLimit()
    {
        System.out.println("Pro ktere k prekroci soucet prvnich k prirozenych cisel hodnotu 10000?");
        System.out.println("For which n will the sum of the first n integers exceed 10000?");
        int s = 0;
        int k = 0;
        while (s < 10000)
        {
            k++;
            s += k;
        }
        System.out.format("k = %d s = %d\n", k, s); //formatovany vystup
    }

    private static void asteriscs()
    {
        System.out.println("Vykreslovani obrazcu z hvezdicek / Drawing shapes with asteriscs.");

        int m = 6; // pocer radku / number of lines
        int n = 12; // pocet sloupcu / number of columns
        
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n-i; j++)
                System.out.print("-");
            
            for (int j = 0; j < 2*i+1; j++)
                System.out.print("*");
            
            for (int j = 0; j < n+1-i; j++)
                System.out.print("-");
            System.out.println();
        }
    }

    private static void asciiTable()
    {
        for (int i = 32; i < 256; i++)
        {
            System.out.format("%3d %c ", i, (char)i);
            if((i+1)% 10 == 0)
                System.out.println();
        }
        System.out.println();
    }
    
    private static void multiplicationTable()
    {
        System.out.println("Velka nasobilka / Multiplication table");

        int n = 20;

        System.out.print(" 1|");
        for (int j = 2; j <= n; j++)
            System.out.format("%3d ", j);

        for (int j = 1; j <= 82; j++)
            System.out.print("-");
        System.out.println();
        
        for (int i = 2; i <= n; i++)
        {
            System.out.format("%2d|",i);
            for (int j = 2; j <= n; j++)
                System.out.format("%3d ", i*j);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Priklady cyklu / Showing some simple loops");
//        simpleSum();
//        evenOdd();
//        cubesBackwards();
//        powersOfTwo();
//        sumLimit();
//        asciiTable();
//        multiplicationTable();
//        form();
//        asteriscs();

    }
    
}
