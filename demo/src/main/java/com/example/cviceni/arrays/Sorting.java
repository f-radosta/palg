/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cviceni.arrays;

/**
 *
 * @author bauerpe1
 */
public class Sorting
{
    public static void bubbleSort(int [] a)
    {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 1; j < a.length - i; j++)
                if(a[j-1] > a[j])
                    swapElems(a,j-1,j);
    }
    
    public static void bLastSort(int [] a)
    {
        int to = a.length-1;
        while (to > 0)
        {
            int last_swap = 0;
            for (int j = 0; j < to; j++)
                if (a[j] > a[j+1])
                {
                    swapElems(a,j,j+1);
                    last_swap = j;
                }
            to = last_swap;
        }
    }
            
    public static void insertionSort(int [] a)
    {
        for(int i = 1; i < a.length; i++)
        {
            int k = i-1;
            int aux = a[i];
            
            while ((k >= 0) && (aux < a[k]))
            {
               a[k+1] = a[k];
               k--;
            }
            a[k+1] = aux;
        }
    }
    
    public static void selectionSort(int [] a)
    {
        for(int i = 0; i < a.length-1; i++)
        {
            int index = i;
            int min = a[i];
            for(int j = i+1; j < a.length; j++)
                if (a[j] < min)
                {
                    index = j;
                    min = a[j];
                }
            if (i != index)
                swapElems(a,i,index);
        }
    }

    public static void shakeSort(int [] a)
    {
        int from = 0;
        int to = a.length-1;
        while (from < to)
        {
            int last_swap = 0;
            for (int j = from; j < to; j++)
                if (a[j] > a[j+1])
                {
                    swapElems(a,j,j+1);
                    last_swap = j;
                }
            to = last_swap;

            for (int j = to; j > from; j--)
                if (a[j] < a[j-1])
                {
                    swapElems(a,j,j-1);
                    last_swap = j;
                }
            from = last_swap;
        }
    }
    
    public static int [] merge(int [] a, int [] b)
    {
        int [] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        
        while (i < a.length && j < b.length)
        {
            if (a[i] <= b[j])
               c[k] = a[i++];
            else
               c[k] = b[j++];
            k++;
        }
        while (i < a.length)
        {
            c[k] = a[i++];
            k++;
        }
        while (j < b.length)
        {
            c[k] = b[j++];
            k++;
        }

        return c;
    }
    
    private static void merge_segment(int [] source, int [] target, int s, int len)
    {
        int i = s;
        int j = s + len;
        int k = s; 
        while (i < s + len && j < s + 2*len)
        {
            if (source[i] <= source[j])
               target[k] = source[i++];
            else
               target[k] = source[j++];
            k++;
        }
        while (i < s + len)
        {
            target[k] = source[i++];
            k++;
        }
        while (j < s + 2*len)
        {
            target[k] = source[j++];
            k++;
        }
        
    }
    public static void mergeSort(int [] a)
    {
        int n = a.length;
        int [] source = a;
        int [] target = new int[n];
        
        for (int k = 1; k < n; k *= 2)
        {
            for (int j = 0; j < n; j += 2*k)
                merge_segment(source,target,j,k);
            
            int [] tmp = source;
            source = target;
            target = tmp;
        }

        if (source != a)
            System.arraycopy(source, 0, a, 0, n);
    }

    public static int bSearch(int[] a, int value, int l, int r) {
        if (l > r)
            return -1;
        else {
            int k = (l + r) / 2;

            if (value == a[k])
                return k;
            else if (value < a[k])
                return bSearch(a, value, l, k - 1);
            else
                return bSearch(a, value, k + 1, r);
        }
    }

    public static int binarySearch(int[] a, int value) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            int k = (l + r) / 2;
            if (value == a[k])
                return k;
            else if (value < a[k])
                r = k - 1;
            else
                l = k + 1;
        }
        return -1;
    }

    public static void swapElems(int [] a, int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void split(int[] a, int l, int r)
    {
        int k = (l + r) / 2;
        int value = a[k];
//        System.out.println(value);
        int i = l;
        int j = r;
        while (i < j)
        {
            while (i <= j && a[i] < value)
                i++;
            while (j >= i && a[j] > value)
                j--;
            if (i <= j) {
                swapElems(a, i, j);
                i++;
                j--;
            }
        }
//        ArrayTools.printArray(a,l ,r+1,j);
        if (l < j)
            split(a,l,j);
        if (i < r)
            split(a,i,r);
    }

    public static void quickSort(int[] a) {
        split(a,0,a.length-1);
    }

    static void addToHeap(int [] a, int l, int r)
    {
        int i = l;
        int val = a[i];
        int j = 2*i+1;
        if (j < r-1 && a[j] < a[j+1])
            j++;
        while (j < r && val <= a[j])
        {
            a[i] = a[j];
            i = j;
            j = 2*i+1;
            if (j < r-1 && a[j] < a[j+1])
                j++;
        }
        a[i] = val;
    }

    static void checkHeap(int [] a, int l, int r)
    {
        for (int i = l; i < r/2; i++)
            if (a[i] < a[2*i+1] || (2*i+2 < r) && a[i] < a[2*i+2])
                System.out.format("chyba na pozici %d s hodnotou %d\n", i, a[i]);
    }

    public static void heapSort(int[] a)
    {
        int n = a.length;
        for (int l = n/2; l > 0; l--)
        {
            addToHeap(a,l-1,n);
//            checkHeap(a,l-1,n);
//            ArrayTools.printArray(a);
        }
//        System.out.println();
        for (int r = n; r > 0; r--)
        {
            int val = a[0];
            a[0] = a[r-1];
            a[r-1] = val;
            addToHeap(a,0,r-1);
//            checkHeap(a,0,r-1);
//            ArrayTools.printArray(a);
        }
    }

}
