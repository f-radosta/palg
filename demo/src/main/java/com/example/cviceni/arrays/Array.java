/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.arrays;

/**
 *
 * @author Petr Bauer
 */


public class Array
{
    private int elems = 0;
    private int [] data = new int[10];

    public static Array sequence(int count)
    {
        return arithmeticSequence(count, 0, 1);
    }
    
    public static Array arithmeticSequence(int count, int value, int increment)
    {
        return new Array(ArrayTools.arithmeticSequence(count, value, increment));
    }
        
    public static Array randomSequence(int count, int from, int to)
    {
        return new Array(ArrayTools.randomSequence(count, from, to));
    }        

    public static Array geometricSequence(int count, int value, int ratio)
    {
        return new Array(ArrayTools.geometricSequence(count, value, ratio));
    }
    
    public Array(int [] data)
    {
        this.data = data;
        this.elems = data.length;
    }
    
    public int size ()
    {
        return elems;
    }

    public int[] getData() {
        return data;
    }

    public int getElems() {
        return elems;
    }
    
    public void append(int value)
    {
        insertElement(value, elems);
    }

    private void grow()
    {
        System.out.println("Growing array from " + data.length + " to " + 2*data.length + " elements");
        int [] bigger = new int[2*data.length];
        System.arraycopy(data, 0, bigger, 0, elems);
        data = bigger;
        print();
    }

    public void insertAtFront(int value)
    {
        insertElement(value, 0);
    }
      
    public void insertElement(int value, int index)
    {
        if (elems >= data.length)
            grow();

        for (int i = elems; i > index; i--)
            data[i] = data[i-1];
        data[index] = value;
        elems++;
    }
   
    public int maxValue()
    {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < elems; i++)
            if (data[i] > max)
                max = data[i];

        return max;      
    }

    public int minValue()
    {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < elems; i++)
            if (data[i] < min)
                 min = data[i];

        return min;
    }

    public int indexMin()
    {
        if (elems == 0 )
            return -1;
        else
        {
            int index = 0;
            for (int i = 1; i < elems; i++)
                if (data[i] < data[index])
                    index = i;
            return index;
        }
    }

    public int indexMax()
    {
        if (elems == 0 )
            return -1;
        else
        {
            int index = 0;
            for (int i = 1; i < elems; i++)
                if (data[i] > data[index])
                    index = i;
            return index;
        }
    }
    
    public void print()
    {
        for (int i = 0; i < elems; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < elems-1; i++)
            s += data[i] + " ";
        s += data[elems-1];
        
        return s;
    }

    private void shrink()
    {
        System.out.println("Shrinking array from " + data.length + " to " + data.length/2 + " elements");
        int [] smaller = new int[data.length/2];
        for (int i = 0; i < data.length/2; i++)
            smaller[i] = data[i];

        data = smaller;

    }
    public void removeElement(int index)
    {
        if (index >= 0 && index < elems)
        {
            for(int i = index; i < elems - 1; i++)
                data[i] = data[i+1];
            data[elems-1] = 0;
            elems--;
        }
        if (elems < data.length/4 && data.length >= 20)
            shrink();
    }
    
    public void removeValue(int value)
    {
        int index = indexOf(value);
        if (index != -1)
            removeElement(index);
        else
            System.out.println("Hodnota " + value + " v poli neni.");
    }
    
    public int binarySearch(int value)
    {
        return ArrayTools.binarySearch(this.data, value);
    }
    
    public int indexOf(int value)
    {
        int index = 0;
        while (index < elems && data[index] != value)
            index++;
      
        return (index < elems) ? index:-1;
    }

    public int lastIndexOf(int value)
    {
        int index = elems - 1;
        while (index >= 0 && data[index] != value)
            index--;

        return index;
    }
}
