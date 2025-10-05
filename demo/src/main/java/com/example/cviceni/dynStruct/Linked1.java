package com.example.cviceni.dynStruct;

public class Linked1 {
    private Elem1 head;
    private Elem1 indent;
    private int elems;
    private final int indentData = Integer.MAX_VALUE;

    public Linked1() {
        this.elems = 0;
        this.indent = new Elem1(indentData);
        this.head = this.indent;
    }

    public int size() {
        return elems;
    }

    public void add(Elem1 elem)
    {
        elem.setNext(this.head);
        this.head = elem;
        this.elems++;
    }

    public void remove(Elem1 elem)
    {
       if (elem != null && elem != this.indent) {
           Elem1 nextElem = elem.getNext();
           elem.setData(nextElem.getData());
           elem.setNext(nextElem.getNext());
           if (nextElem == this.indent)
               this.indent = elem;
           this.elems--;
       }
    }

    public void append(Elem1 elem) {
        this.indent.setData(elem.getData());
        this.indent.setNext(elem);
        elem.setData(indentData);
        this.indent = elem;
        this.elems++;
    }

    public void clear()
    {
        while (this.head != indent) {
            Elem1 tmp = this.head;
            this.head = this.head.getNext();
            tmp.setNext(null);
        }
        this.elems = 0;
    }

    @Override
    public String toString() {
        String s = "Linked1{" +
                "elems=" + elems + " : ";
        Elem1 p = this.head;

        while (p != indent)
        {
            s += p.getData() + " ";
            p = p.getNext();
        }
        s += '}';
        return s;
    }

    private Elem1 find(int value)
    {
        Elem1 tmp = this.head;
        while (tmp!= this.indent && tmp.getData() != value)
            tmp = tmp.getNext();

        return tmp == indent ? null : tmp;
    }

    private Elem1 get(int index)
    {
        if (index >= this.elems)
            return null;
        else
        {
            Elem1 tmp = this.head;
            for (int i = 0; i < index; i++)
                tmp = tmp.getNext();

            return tmp;
        }
    }

    public static void main(String[] args)
    {
        Linked1 list = new Linked1();
        list.add(new Elem1(1));
        list.add(new Elem1(2));

        Elem1 tmp = new Elem1(3);
        list.add(tmp);

        list.append(new Elem1(11));
        list.append(new Elem1(12));
        list.append(new Elem1(13));

        System.out.println(list);
        list.remove(tmp);
        list.remove(null);
        System.out.println(list);

        System.out.println(list.find(5));
        System.out.println(list.find(11));
        System.out.println(list.get(3));
        System.out.println(list.get(10));

        list.clear();
        System.out.println(list);

    }
}
