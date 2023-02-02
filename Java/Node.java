
package com.mycompany.datastructures;

public class Node {
    private Object data;
    private Node next;
    
    public Node(Object value,Node next){
        this.data = value;
        this.next = next;
    }
    
    public Node getNext(){
        return next;
    }
    
    public Object getData(){
        return data;
    }
    
    public void setData(Object value){
        this.data = value;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
      
}
