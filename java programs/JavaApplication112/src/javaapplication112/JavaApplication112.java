/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication112;
class LinkedList {
    Node head;
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            next = null;
        }
    }
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    public void displayNode(int key) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data == key) {
                System.out.println("Node with key " + key + " found. Data: " + current.data);
                found = true;
                break;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Node with key " + key + " not found in the linked list.");
        }
    }}
public class JavaApplication112 {
    public static void main(String[] args) {
         LinkedList linkedList = new LinkedList();

        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(5);

        int keyToFind = 3;
        linkedList.displayNode(keyToFind);

    }
    
}
