/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication105;
class Node {
    int data1;
    double data2;
    Node next;
    Node(int data1, double data2) {
        this.data1 = data1;
        this.data2 = data2;
        this.next = null;
    }}
class LinkedList {
    Node head;
    void add(int data1, double data2) {
        Node newNode = new Node(data1, data2);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;  }
            current.next = newNode;} }
    void display() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data1 + ", " + current.data2 + "]  ");
            current = current.next;
        }
        System.out.println("null");
    }
    public boolean search(Node targetNode) {
        Node current = head;
        while (current != null) {
            if (current.data1 == targetNode.data1 && current.data2 == targetNode.data2) {
                return true;   }
            current = current.next; }
        return false;  }}
public class JavaApplication105 {
    public static void main(String[] args) {
         LinkedList linkedList = new LinkedList();
        linkedList.add(1, 1.1);
        linkedList.add(2, 2.2);
        linkedList.add(3, 3.3);

        System.out.println("Linked List:");
        linkedList.display();

        Node searchNode = new Node(2, 2.2);
        boolean found = linkedList.search(searchNode);

        if (found) {
            System.out.println("Data " + searchNode.data1 + ", " + searchNode.data2 + " found in the Linked List.");
        } else {
            System.out.println("Data " + searchNode.data1 + ", " + searchNode.data2 + " not found in the Linked List.");
    }
}}
