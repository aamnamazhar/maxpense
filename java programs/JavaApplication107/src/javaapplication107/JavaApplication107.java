/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication107;
class Node {
    int data1;
    double data2;
    Node next;
    Node(int data1, double data2) {
        this.data1 = data1;
        this.data2 = data2;
        this.next = null;
    }
}
class LinkedList {
    Node head;
    public void add(int data1, double data2) {
        Node newNode = new Node(data1, data2);
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
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data1 + ", " + current.data2 + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    public boolean search(Node targetNode) {
        Node current = head;
        while (current != null) {
            if (current.data1 == targetNode.data1 && current.data2 == targetNode.data2) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public Node deleteAfter(Node targetNode) {
        Node current = head;
        while (current != null) {
            if (current.data1 == targetNode.data1 && current.data2 == targetNode.data2 && current.next != null) {
                Node deletedNode = current.next;
                current.next = current.next.next;
                return deletedNode;
            }
            current = current.next;
        }
        return null;  
    }
}
public class JavaApplication107 {
    public static void main(String[] args) {
         LinkedList linkedList = new LinkedList();
        linkedList.add(1, 1.1);
        linkedList.add(2, 2.2);
        linkedList.add(3, 3.3);
        System.out.println("Linked List:");
        linkedList.display();
        Node targetNode = new Node(2, 2.2);
        Node deletedNode = linkedList.deleteAfter(targetNode);
        if (deletedNode != null) {
            System.out.println("Deleted Node: [" + deletedNode.data1 + ", " + deletedNode.data2 + "]");
        } else {
            System.out.println("No node was deleted.");
        }
        System.out.println("Linked List after deleting the node after the target node:");
        linkedList.display();
        
    }
    
}
