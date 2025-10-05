/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.advanced;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author bauerpe1
 */
public class Maze {
    static int dim = 6;
    static int [][] classic = {{0,-4,-4,0,0,0},
                            {0,-4,0,-4,0,-4},
                            {0,-4,0,0,0,-4},
                            {0, 0,0,-4,0,0},
                            {0,-4,0,0,-4,0},
                            {-4,0,0,0,-1,0}};

    static int [][] circular = {{0,0,0,0,0,0},
                            {0,-4,-4,-4,-4,0},
                            {0,0,0,-4,-4,0},
                            {0,-4,0,-1,-4,0},
                            {0,-4,-4,-4,-4,0},
                            {0,0,0,0,0,0}};

    static int [][] empty = {{0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0},
                            {0,0,0,0,0,0}};

    static int [][] maze = classic;

    static boolean keepSearching = true;
    static boolean findAllSolutions = false;
    
    static boolean inside(int x, int y)
    {
        return 0 <= x && x < dim && 0 <= y && y < dim;
    }
    
    static boolean isFree(int x, int y)
    {
        return maze[x][y] == 0 || maze[x][y] == -1;
    }
    
    static boolean isValid(int x, int y)
    {
        return inside(x,y) && isFree(x,y);
    }

    static boolean isWorse(int k, int x, int y)
    {
        return inside(x,y) && maze[x][y] > k;
    }
    
    static void mark(int n, int x, int y)
    {
        if (maze[x][y] == -1) 
            keepSearching = false;
        else
            maze[x][y] = n;
    }
    
    private static void wave(int sx, int sy)
    {
        mark(1,sx,sy);
        for (int k = 1; k < dim*dim; k++)
        {
            for (int x = 0; x < dim; x++)
            for (int y = 0; y < dim; y++)
            {
                if (maze[x][y] == k)
                {
                    if (isValid(x-1,y)) mark(k+1,x-1,y);
                    if (isValid(x+1,y)) mark(k+1,x+1,y);
                    if (isValid(x,y-1)) mark(k+1,x,y-1);
                    if (isValid(x,y+1)) mark(k+1,x,y+1);
                }
            }
        }
    }

    private static void front(int sx, int sy)
    {
        Point [] wave_front = new Point[dim*dim];
        mark(1,sx,sy);
        wave_front[0] = new Point(sx,sy);
        int s = 0;
        int n = 1;


        for (int k = 1; k < dim*dim; k++)
        {
            int b = n;
            for (int i = s; i < b; i++)
            {
                Point p = wave_front[i];
                int x = p.x;
                int y = p.y;
                
                if (maze[x][y] == k)
                {
                    if (isValid(x-1,y))
                    {
                        mark(k+1,x-1,y);
                        wave_front[n++] = new Point(x-1,y);
                    }
                    if (isValid(x+1,y))
                    {
                        mark(k+1,x+1,y);
                        wave_front[n++] = new Point(x+1,y);
                    }
                    if (isValid(x,y-1))
                    {
                        mark(k+1,x,y-1);
                        wave_front[n++] = new Point(x,y-1);
                    }
                    if (isValid(x,y+1))
                    {
                        mark(k+1,x,y+1);
                        wave_front[n++] = new Point(x,y+1);
                    }
                }
            }
            s = b;
        }
    }
    
    static void move(int k, int x, int y)
    {
        if (keepSearching)
        {
            if (maze[x][y] == -1)
            {
                printMaze();
                keepSearching &= findAllSolutions;
            }
            else
            {
                maze[x][y] = k;
                        
                if (isValid(x-1,y)) move(k+1,x-1,y);
                if (isValid(x+1,y)) move(k+1,x+1,y);
                if (isValid(x,y-1)) move(k+1,x,y-1);
                if (isValid(x,y+1)) move(k+1,x,y+1);

                if (inside(x-1,y) && maze[x-1][y] > k+1) move(k+1,x-1,y);
                if (inside(x+1,y) && maze[x+1][y] > k+1) move(k+1,x+1,y);
                if (inside(x,y-1) && maze[x][y-1] > k+1) move(k+1,x,y-1);
                if (inside(x,y+1) && maze[x][y+1] > k+1) move(k+1,x,y+1);
                
                if (keepSearching)
                    ;//maze[x][y] = 0;
            }
        }
    }
    
    static void move_loc(int k, int x, int y, int dx, int dy)
    {
        if (keepSearching)
        {
            if (maze[x][y] == -1)
            {
                //printMaze();
                keepSearching &= findAllSolutions;
            }
            else
            {
                maze[x][y] = k;
                        
                if (isValid(x-dy,y+dx)) move_loc(k+1,x-dy,y+dx,-dy,dx);
                if (isValid(x+dx,y+dy)) move_loc(k+1,x+dx,y+dy,dx,dy);
                if (isValid(x+dy,y-dx)) move_loc(k+1,x+dy,y-dx,dy,-dx);
                
                
                //if (isWorse(k+1,x+dx,y+dy)) move_loc(k+1,x+dx,y+dy,dx,dy);
                //if (isWorse(k+1,x+dy,y-dx)) move_loc(k+1,x+dy,y-dx,dy,-dx);
                //if (isWorse(k+1,x-dy,y+dx)) move_loc(k+1,x-dy,y+dx,-dy,dx);

                if (keepSearching)
                    ;//maze[x][y] = 0;
            }
        }
    }

    static void printMaze()
    {
        for(int y = 0; y < dim; y++)
        {
            for(int x = 0; x < dim; x++)
                System.out.format("%2d ", maze[y][x]);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        move(1,0,0);
//        move_loc(1,0,0,0,-1);
//        wave(0,0);
        front(0,0);
        printMaze();
    }
    
}
