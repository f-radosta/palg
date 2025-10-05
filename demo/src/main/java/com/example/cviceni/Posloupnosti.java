package com.example.cviceni;

import java.util.*;

public class Posloupnosti
{
    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();

    private static void hledej_max()
    {
        System.out.println("Zadavejte cela cisla. Vstup ukoncite zadanim nuly.");
        int r = input.nextInt();
        int max = r;

        while (r != 0)
        {
            if (r > max)
                max = r;
            r = input.nextInt();
        }
        System.out.println("Nejvetsi zadane cislo bylo " + max);
    }

    private static void hledej_min()
    {
        System.out.println("Zadavejte cela cisla. Vstup ukoncite zadanim nuly.");
        //int min = Integer.MAX_VALUE;
        //int min = 0b01111111111111111111111111111111;
        //int min = 0x7FFFFFFF;
         
        int r = input.nextInt();
        int min = r;
         
        while (r != 0)
        {
            if (r < min)
                min = r;
            r = input.nextInt();
        }
        System.out.println("Nejmensi zadane cislo bylo " + min);
    }
    
    private static void prumer()
    {
        System.out.println("Zadavejte cela cisla. Vstup ukoncite zadanim nuly.");
        int soucet = 0;
        int pocet = 0;

        int r = input.nextInt();
        while (r != 0)
        {
            soucet += r;
            pocet++; 
            r = input.nextInt();
        }
         
        if (pocet == 0)
            System.out.println("Nebylo zadano zadne nenulove cislo");
        else
            System.out.println("Prumer zadanych cisel je " + (double)soucet/pocet);
    }
    
    private static void otoc()
    {
        System.out.println("Zadavejte cela cisla. Vstup ukoncite zadanim nuly.");
         
        int kapacita = 20;
        int pocet = 0;
        int [] cisla = new int[kapacita];
         
        int r = input.nextInt();
        while (pocet < kapacita && r != 0)
        {
            cisla[pocet] = r;
            pocet++; 
            r = input.nextInt();
        }
        vypis(cisla,0,pocet);
        vypis_pozpatku(cisla,0,pocet);
        
        vypis(cisla);
        vypis_pozpatku(cisla);
        
        int [] vysledek = new int[pocet];
        for (int i = 0; i < pocet; i++)
            vysledek[i] = cisla[pocet-1-i];
        vypis(vysledek);
        
//         int [] klon = new int[vysledek.length];
//         for (int i = 0; i < vysledek.length; i++)
//             klon[i] = vysledek[i];

    }

    private static void vypis(int [] a)
    {
        vypis(a,0,a.length);  
    }
    
    private static void vypis(int [] a, int dolni, int horni)
    {
        for (int i = dolni; i < horni; i++)
            System.out.print(a[i] + " ");
        System.out.println();   
    }

    
    private static void vypis_pozpatku(int [] a)
    {
        vypis_pozpatku(a,0,a.length);
    }

    private static void vypis_pozpatku(int [] a, int dolni, int horni)
    {
        for (int i = horni-1; i >= dolni; i--)
            System.out.print(a[i] + " ");
        System.out.println();   
    }

    
    private static void filtruj()
    {
        System.out.println("Zadavejte cela cisla. Vstup ukoncite zadanim nuly.");

        int kapacita = 20;
        int [] cisla = new int[kapacita];
        int pocet = 0;
         
        int r = input.nextInt();
        while (pocet < kapacita && r != 0)
        {
            if (r % 2 != 0 || (5 <= r && r <= 10))
                cisla[pocet++] = r;
            r = input.nextInt();
        }
        //System.out.println("Zadana licha cisla: ");
        System.out.println("Cisla splnujici kriterium: ");
        vypis(cisla,0,pocet);           
    }
    
    private static void pocitej_sestky()
    {
      int pocet_hodu = 20;
      int [] cisla = new int[pocet_hodu];
      int pocet = 0;
        
      for (int i = 0; i < pocet_hodu; i++)
      {
         int r = 1 + random.nextInt(6);       
         cisla[i] = r;
         if (r == 6)
            pocet++;
      }
      vypis(cisla);
      System.out.print("Padlo " + pocet + " sestek");    
    }
    
    private static void cetnosti_hodu()
    {
      int pocet_hodu = 20;
      int [] cisla = new int[pocet_hodu];
      int rozsah = 6;
      int [] cetnosti = new int[rozsah];
        
      for (int i = 0; i < pocet_hodu; i++)
      {
         int r = random.nextInt(rozsah);       
         cisla[i] = r+1;
         cetnosti[r]++;
      }
      vypis(cisla);
      for(int i = 0; i < rozsah; i++)
         if (cetnosti[i] !=0 )
            System.out.print((i+1) + "[" + cetnosti[i] + "x] ");
      System.out.println();          
    }

    private static void cetnosti_neomez()
    {
      int pocet_hodu = 20;
      int [] raw = new int[pocet_hodu];

      int [] cisla = new int[pocet_hodu];
      int [] cetnosti = new int[pocet_hodu];
      int ruznych = 0;
         
      for (int i = 0; i < pocet_hodu; i++)
      {
         int r = random.nextInt(100);       
         raw[i] = r;
         int pozice = 0;
         while(pozice < ruznych && cisla[pozice] != r)
            pozice++;
         
         if (pozice == ruznych)
            cisla[ruznych++] = r;
            
         cetnosti[pozice]++;   
      }
      vypis(raw);
      vypis(cisla, 0, ruznych);
      for(int i = 0; i < ruznych; i++)
           System.out.print(cisla[i] + "["+ cetnosti[i] + "x] ");
      
      System.out.println();          
    }

    public static int indexOf(int [] a, int hodnota)
    {
        int index = 0;
        while (index < a.length && a[index] != hodnota)
            index++;
        
        if (index == a.length)
            return -1;
        else
            return index;
    }

    public static int lastIndexOf(int [] a, int hodnota)
    {
        int index = a.length-1;
        while (index >= 0 && a[index] != hodnota)
            index--;

        return index;
    }    
    
    public static int [] generuj(int kapacita, int rozsah)
    {
        int [] a = new int[kapacita];
        
        for (int i = 0; i < kapacita; i++)
            a[i] = random.nextInt(rozsah);

         return a;
    }
    
    
    private static void vyskyt()
    {
        int [] cisla = generuj(20,10);
            
        vypis(cisla);

        System.out.print("Kterou hodnotu chcete nalezt: ");
        int hodnota = input.nextInt();
        int index = indexOf(cisla,hodnota);
        
        if (index == -1)
            System.out.println("Hledana hodnota v poli neni");
        else
            System.out.println("Hledana hodnota se nachazi na pozici " + index);
    }

    public static void main(String[] args)
    {
//         hledej_max();
//         hledej_min();
//         prumer();
//         otoc();
//        filtruj();
//         pocitej_sestky();  
//         cetnosti_hodu();
//           cetnosti_neomez();
         vyskyt();
    }
}

