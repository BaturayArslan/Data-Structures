package com.mycompany.datastructures;
import java.util.Arrays;

public class CustomArrayList<T> {
    private int size = 2;
    // TODO: figure out how we can take a input from user that decide arr type
    private T[] arr;
    private int length = 0;
    
    public CustomArrayList(){
        arr = (T[]) new Object[size];
    }
    
    public T get(int index){
        return arr[index];
    }
    
    public void set(int index, T value){
        arr[index] = value;
    }
    
    private void doubleSize(){
        size *=2; 
        T[] newArr = (T[]) new Object[size];
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
        
    }
    
    public void push(T value){
        if(length >= size ) doubleSize();
        arr[length] = value;
        length++;
        
    }
    
    public T pop() throws IndexOutOfBoundsException {
        if(length <= 0) throw new IndexOutOfBoundsException();
        T value = arr[length - 1];
        arr[length - 1] = (T) new Object();
        length--;
        return value;
    }
    
    public int length(){
        return length;
    }
    
    public int capacity(){
        return size;
    }
    
    public T at(int index){
        return arr[index];
    }
    
    public void remove(int index){
        for(int i = index + 1; i < length; i++){
            arr[i - 1] = arr[i];
        }
        length--;
    }
    
    public void insert(int index,T value){
        if(length >= size ) doubleSize();
        for(int i = length - 1; i >= index; i--){
            arr[i+1] = arr[i];
        }
        arr[index] = value;
        length++;
    }
    
    
    public static void display(CustomArrayList arr){
        for(int i = 0; i < arr.length(); i++){
            System.out.printf("%d ",arr.get(i));
        }
        System.out.printf("\n");
        System.out.printf("Size: %d     Length: %d \n ",arr.capacity(),arr.length());
    }
    
    public static void main(String[] args)
    {
        CustomArrayList<Integer> arrList = new CustomArrayList<Integer>();
        arrList.push(1);
        display(arrList);
        arrList.push(2);
        display(arrList);
        arrList.push(3);
        display(arrList);
        arrList.pop();
        display(arrList);
        arrList.push(3);
        arrList.insert(0, 5);
        display(arrList);
        arrList.push(4);
        display(arrList);
        arrList.remove(0);
        display(arrList);
    }
}
