package com.example.cviceni.advanced;

import java.util.Scanner;

class Primes
{
   private static Scanner input = new Scanner(System.in);

   static int [] primes;
   
   private static void eratosthenes()
   {
      System.out.println("Searching for all prime numbers < n:");
      System.out.print("n = ");
      int range = input.nextInt();
      
      boolean [] status = new boolean[range];

      status[0] = false;
      status[1] = false;
      for (int i = 2; i < range; i++)
         status[i] = true;

      for (int i = 2; i*i < range; i++)
         if (status[i])
         {
            for (int j = i*i; j < range; j += i)
               status[j] = false;
         }
      output(status);
   }
   
   private static void output(boolean [] status)
   {
      String row = "";
      for (int i = 2; i < status.length; i++)
         if (status[i])
         {
            row += i + " ";
            
            if (row.length() > 80)
            {
               System.out.println(row);
               row = "";
            }
         }
      System.out.println(row);
   }
   
   private static boolean isPrime(int number)
   {
      for (int i = 0; primes[i]*primes[i] <= number; i++)
         if (number % primes[i] == 0)
            return false;
      return true;
   }

   private static void getPrimes()
   {
      System.out.println("Searching for first n prime numbers:");
      System.out.print("n = ");
      int count = input.nextInt();
      
      primes = new int[count];
      primes[0] = 2;
      primes[1] = 3;

      int index = 2;
      for (int i = 5; index < count; i += 2)
      {
         if (isPrime(i))
         {
            primes[index] = i;
            index++;
         }
      }
      printArray(primes);
   }
   
    private static void printArray(int [] array)
    {
        printArray(array,0,array.length);
    }

    private static void printArray(int [] array, int from, int before)
    {
        String row = "";
        for (int i = from; i < before; i++)
        {
            row += array[i] + " ";
            if (row.length() > 80)
            {
                System.out.println(row);
                row = "";
            }
        }
        System.out.println(row);
    }

   public static void main(String[] args)
   {
       eratosthenes();
       getPrimes();
   }
}
