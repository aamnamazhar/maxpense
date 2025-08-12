/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication108;
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
class LinkedList {
    Node head;
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
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
            return;
        }
        prev.next = current.next;
    }
 public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
public class JavaApplication108 {
    public static void main(String[] args) {
         LinkedList linkedList = new LinkedList();
        linkedList.insert(0);
        linkedList.insert(10);
        linkedList.insert(20);
        linkedList.insert(30);

        System.out.println("Original Linked List:");
        linkedList.display();

        linkedList.delete(20);
        System.out.println("Linked List after deleting node with key 20:");
        linkedList.display();
        
    }
    
}
