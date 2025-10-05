/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.basics;
import com.example.cviceni.arrays.ArrayTools;
import java.util.*;

import static com.example.cviceni.arrays.ArrayTools.printArray;
import static com.example.cviceni.arrays.ArrayTools.printReverse;

/**
 *
 * @author bauerpe1
 */
public class Procvicovani
{
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    private static String [] nazvyMesicu = {"Leden", "Unor", "Brezen", "Duben", "Kveten", "Cerven", "Cervenec", "Srpen", "Zari", "Rijen", "Listoped", "Prosinec"};
    private static int [] delkyMesicu = {31,28,31,30,31,30,31,31,30,31,30,31};

    private static void filtrujLicha()
    {
        int N = 10;
        int [] licha = new int[N];
        
        int pocet = 0;
        System.out.println("Zadavejte cela cisla, zadavani ukoncete nulou:");

        int r = input.nextInt();

        while (pocet < N && r != 0)
        {
            if (r % 2 != 0)
            {
                licha[pocet] = r;
                pocet++;
            }
            r = input.nextInt();
        }

        System.out.print("Licha cisla jsou: ");
        for (int i = 0; i < pocet; i++)
            System.out.print(licha[i] + " ");
        System.out.println();
    }
    
    private static void nejmensiLiche()
    {
        int r = input.nextInt();
        int min = Integer.MAX_VALUE;
                        
        while(r != 0)
        {
            if (r % 2 == 1 && r < min)
                min = r;
            r = input.nextInt();
        }
        if (min == Integer.MAX_VALUE)
            System.out.println("Nebylo zadano zadne liche cislo.");
        else
            System.out.println("Nejmensi liche cislo bylo " + min);
    }
    
    private static void sudaMinusLicha()
    {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int vysledek = 0;

        System.out.println("Zadavejte posloupnost cisel ukoncenou nulou:");
        int r = input.nextInt();

        while (r != 0)
        {
            if (r % 2 == 0)
            {
                vysledek += r;
                if (r > max)
                    max = r;
            }
            else
            {
                vysledek -= r;
                if (r < min)
                    min = r;
            }
            r = input.nextInt();
        }
        
        System.out.println("Soucet sudych minus soucet lichych byl " + vysledek);
        System.out.println("Nejvetsi sude bylo " + max + " a nejmensi liche " + min);
    }

    private static void flip(int [] array )
    {
        int n = array.length;
        for (int i = 0; i < n/2; i++)
        {
            int p = array[i];
            array[i] = array[n-1-i];
            array[n-1-i] = p;
        }
    }

    private static void obrat()
    {
        int [] a = ArrayTools.arithmeticSequence(10,0,1);
        ArrayTools.printArray(a);
        flip(a);
        ArrayTools.printArray(a);
    }
    
    private static void kalendarMesic(int dni, int prvniDen)
    {
        int den = prvniDen;
        
        System.out.println(" Po Ut St Ct Pa So Ne");
        
        for (int i = 0; i < den; i++)
            System.out.format(" --");
        
        for (int i = 1; i <= dni; i++)
        {
            System.out.format("%3d", i);
            if (den == 6)
            {
                System.out.println();
                den = 0;
            }
            else
                den++;
        }
        System.out.println("\n");
    }
    
    private static int pocetDni(int rok, int mesic)
    {
        if (mesic==2)
            if (prestupny(rok))
                return 29;
            else
                return 28;
        else
            return 30 + (mesic + mesic/8)%2;
    }

    private static int pocetDni_alt(int rok, int mesic)
    {
        if (mesic==2 && prestupny(rok))
            return 29;
        return delkyMesicu[mesic-1];
    }
    
    private static boolean prestupny(int rok) 
    {
        return (rok % 4 == 0 && rok % 100 != 0) || rok % 400 == 0;
    }
    
    private static void kalendarRok(int rok, int novyRok)
    {
        int den = novyRok;
        System.out.println("Kalendar pro rok " + rok);
        for (int i = 0; i < nazvyMesicu.length; i++)
        {
            System.out.println(nazvyMesicu[i]);
            int pocet = pocetDni(rok, i+1);
            kalendarMesic(pocet,den);
            den = (den + pocet) % 7;
        }
    }

    public static void main(String[] args)
    {
//        filtrujLicha();
//        sudaMinusLicha();
//        obrat();

//        kalendarMesic(30,1);
        kalendarRok(2022,5);
//        kalendarRok(2024,0);
    }

}
