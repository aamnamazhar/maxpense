/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication115;

/**
 *
 * @author dell
 */
public class JavaApplication115 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


























class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        int n = 5; // Change this value to the number of nodes you want to create
        for (int i = 1; i <= n; i++) {
            list.insert(i);
        }

        System.out.println("Linked List before removing the first element:");
        list.display();

        list.removeFirst();

        System.out.println("Linked List after removing the first element:");
        list.display();
    }
}
