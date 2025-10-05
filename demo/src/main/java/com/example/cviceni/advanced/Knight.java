/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.advanced;

import java.awt.Point;
import java.util.*;

/**
 *
 * @author bauerpe1
 */
public class Knight
{
    private static Scanner input = new Scanner(System.in);
    static boolean keepSearching = true;
    static Board board = new Board(20,20);
    static Pieces piece = Pieces.DEFAULT;

    
    static void move(int x, int y, int k)
    {
        board.setValue(x,y,k);
        if (k == board.area())
            keepSearching = false;
        else {
            Point [] moves = Pieces.neighbours(piece,x,y);
            for (Point p : moves)
                if (board.isAvailable(p.x, p.y))
                    move(p.x, p.y, k + 1);

            if (keepSearching)
                board.setValue(x, y, 0);
        }
    }
    
    private static void wave_full(int sx, int sy)
    {
        int steps = board.wave_full(sx, sy);
        System.out.println("Tested fields = " + (steps*board.area()));
        System.out.println("Marked fields: " + board.usedFields());
    }

    private static void wave_front(int sx, int sy)
    {
        Wave wave = new Wave(board,piece);
        wave.build(sx,sy);
        System.out.println("Tested fields = " + wave.size());
        System.out.println("Marked fields: " + board.usedFields());
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int x0 = 0;
        int y0 = 0;

//        move(x0,y0,1);
//        if (keepSearching)
//            System.out.println("No solution found.");
//        else
//            board.print();
        
        board.clear();
        long tic = System.nanoTime();
        wave_full(x0,y0);
        long tac = System.nanoTime();
        System.out.println("Original version: " + (tac-tic)/1.0E+6 + " ms");
        board.print();

        board.clear();
        tic = System.nanoTime();
        wave_front(x0,y0);
        tac = System.nanoTime();
        System.out.println("Wave front implementation: " + (tac-tic)/1.0E+6 + " ms");
        board.print();

    }
    
}
