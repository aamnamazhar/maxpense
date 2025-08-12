/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication114;

class Node{
    int data;
    Node head;
    Node next;
    
    public Node(int data){
        data=this.data;
        this.next = null;
    }
}
class linkedlist{
    Node head;
    
    public void addNode(int data){
        Node newNode= new Node(data);
        if(head==null){
            head=newNode;
        }else{
            Node current=head;
            while(current.next !=null){
                current=current.next;
            }
            current.next=newNode;
        }
    }
    public void display(){
        Node current=head;
        while(current!=null){
            System.out.println(current.data +" ");
            current=current.next;
        }
        System.out.println();
    }
    public void displayReverse(){
        
}
    public void displayReverseUtil(Node node){
        if (node==null){
            return;
        }
        display
    }
}

public class JavaApplication114 {
    public static void main(String[] args) {
        linkedlist list = new linkedlist();
          list.addNode(1);
          list.addNode(3);
          list.addNode(5);
        
        System.out.println("Reverse order: " );
        list.displayreverse();
    }
    
}


















