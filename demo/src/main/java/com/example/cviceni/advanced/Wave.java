package com.example.cviceni.advanced;

public class Wave {
    private int [][] front;
    private int [] seg;
    private int elems = 0;
    private int segments = 0;
    private Board board;
    private Pieces piece;

    public Wave(Board board, Pieces piece) {
        this.board = board;
        this.piece = piece;
        int n = board.area();
        this.front = new int[n][2];
        this.seg = new int[n];
    }

    public int size() {
        return elems;
    }

    public void add(int x, int y) {
        front[elems][0] = x;
        front[elems][1] = y;

        elems++;
    }

    private void addField(int x, int y, int k)
    {
        if (board.isAvailable(x,y)) {
            board.setValue(x,y,k);
            add(x,y);
        }
    }
    public void build(int sx, int sy)
    {
        addField(sx,sy,1);
        seg[0] = 0;
        seg[1] = 1;
        segments = 1;
        for(int k = 1; k < board.area() && !board.filledUp(); k++)
            buildSegment(k);
    }

    public void buildSegment(int k) {
        int[][] moves = Pieces.moves(piece);
        for (int j = seg[k - 1]; j < seg[k]; j++) {
            int x = front[j][0];
            int y = front[j][1];

            if (board.getValue(x, y) == k)
                for (int i = 0; i < moves.length; i++)
                    addField(x + moves[i][0], y + moves[i][1], k + 1);
        }
        seg[++segments] = elems;
    }
}