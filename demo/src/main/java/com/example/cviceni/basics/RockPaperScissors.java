package com.example.cviceni.basics;

import java.util.*;

class RockPaperScissors
{
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        rps();
    }
    
    private static void rps()
    {
        System.out.println("Welcome to the rock-paper-scissors game!");
        System.out.print("First player's choice:");
        String first = input.next();
        System.out.print("Second player's choice:");
        String second = input.next();

        first = first.toLowerCase();
        second = second.toLowerCase();

        if (first.equals(second))
            System.out.println("It is a draw!");
        else if (first.equals("rock") && second.equals("scissors")
                || first.equals("scissors") && second.equals("paper")
                || first.equals("paper") && second.equals("rock"))
            System.out.println("First player wins.");
        else if (first.equals("rock") && second.equals("paper")
                || first.equals("scissors") && second.equals("rock")
                || first.equals("paper") && second.equals("scissors")) {
            System.out.println("Second player wins.");
        } else
            System.out.println("Invalid input!");
    }
}
