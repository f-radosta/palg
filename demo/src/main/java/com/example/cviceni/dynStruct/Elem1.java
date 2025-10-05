package com.example.cviceni.dynStruct;

public class Elem1 {
    private int data;
    private Elem1 next;


    public Elem1(int data) {
        this.data = data;
        this.next = null;
    }

    public Elem1(int data, Elem1 next) {
        this.data = data;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public Elem1 getNext() {
        return next;
    }

    public void setNext(Elem1 next) {
        this.next = next;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Elem1{" +
                "data=" + data +
                '}';
    }
}
