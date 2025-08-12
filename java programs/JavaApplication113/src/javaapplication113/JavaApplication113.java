/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication113;
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
     public LinkedList() {
     this.head = null;
     }

     public void insert(int data) {
     Node newNode = new Node(data);
     if (head == null) {
     head = newNode;
     } else {
     Node current = head;
     while (current.next != null) {
     current = current.next;
     }
     current.next = newNode;
     }
     }

     public void delete(int key) {
     if (head == null) {
     return;
     }
     if (head.data == key) {
     head = head.next;
     return;
     }
     Node current = head;
     Node prev = null;
     while (current != null && current.data != key) {
     prev = current;
     current = current.next;
     }
     if (current == null) {
     System.out.println("Node with key " + key + " not found.");
     return;
     }
     prev.next = current.next;
     }

     public int sumOfData() {
     int sum = 0;
     Node current = head;
     while (current != null) {
     sum += current.data;
     current = current.next;
     }
     return sum;
     }

     public Node findNode(int key) {
     Node current = head;
     while (current != null) {
     if (current.data == key) {
     return current;
     }
     current = current.next;
     }
     return null;
     }
    }

/**
 *
 * @author dell
 */
public class JavaApplication113 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
     list.insert(5);
     list.insert(10);
     list.insert(15);
     list.insert(20);
     System.out.println("Original Linked List:");
     printLinkedList(list);
     list.delete(10);
     System.out.println("Linked List after deleting node with key 10:");
     printLinkedList(list);
     int sum = list.sumOfData();
     System.out.println("Sum of data in all nodes: " + sum);
     Node foundNode = list.findNode(15);
     if (foundNode != null) {
     System.out.println("Node with key 15 found. Data: " + foundNode.data);
     } else {
     System.out.println("Node with key 15 not found.");
     }
     }
     public static void printLinkedList(LinkedList list) {
     Node current = list.head;
     while (current != null) {
     System.out.print(current.data + " -> ");
     current = current.next;
     }
     System.out.println("null");
        // TODO code application logic here
    }
    
}
