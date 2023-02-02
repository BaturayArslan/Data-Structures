package com.mycompany.datastructures;
import java.util.Arrays;


interface MyStringBuilder {
    public void append(String value);
    public String toString();
    public void reverse();
    public void trimSize();
    //public void insert(int index, String value);
    //public void delete(int start );
    //public void delete(int start, int end);
    
}


public class CustomStringBuilder implements MyStringBuilder{
    
    private int capacity = 16;
    private int length = 0;
    private char[] arr = new char[capacity];
    
    
    @Override
    public void append(String value){
        // check if capacity is avaible
        if(length + value.length() > capacity) doubleCapacity();
        for(int i = 0 ; i < value.length(); i++){
            arr[length - 1 + i] = value.charAt(i);
        }
        length += value.length();
    }
    
    private void doubleCapacity(){
        capacity *= 2;
        char[] newArr = new char[capacity];
        for(int i = 0; i < length; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
    
    @Override
    public String toString(){
        return new String(arr);
    }
    
    @Override
    public void reverse(){
        for(int i = length ; i > length/2; i-- ){
            char tmp = arr[length - i];
            arr[length - i] = arr[i - 1];
            arr[i - 1] = tmp;
        }
    }
    
    @Override
    public void trimSize(){
        if(capacity > length){
            capacity = length;
            char[] newArr = new char[capacity];
            for(int i = 0; i < length; i++){
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    }
    
            
            
            
    public static void main(String[] args){
        StringBuilder test = new StringBuilder();
        System.out.printf("Capacity: %d    Length: %d \n",test.capacity(),test.length());
        test.append("hellowodsdsdsdsds");
        System.out.printf("Capacity: %d    Length: %d \n",test.capacity(),test.length());
    }
}
