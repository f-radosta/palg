package com.example.cviceni.advanced;

import java.awt.*;

public enum Pieces {
    DEFAULT,KNIGHT;
    static final int[][] defaultMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int[][] knightsMoves = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};

    public static final int[][] moves(Pieces piece) {
        switch (piece) {
            case KNIGHT:
                return knightsMoves;

            default:
                return defaultMoves;
        }
    }

        public static Point[] neighbours(Pieces piece, int x, int y) {
            int [][] moves = moves(piece);
            int n = moves.length;
            Point[] p = new Point[n];

            for (int i = 0; i < n; i++)
                p[i] = new Point(x + moves[i][0],y + moves[i][1]);

            return p;
    }
}
