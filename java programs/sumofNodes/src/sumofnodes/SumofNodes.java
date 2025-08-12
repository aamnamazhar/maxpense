/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sumofnodes;
  class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            next = null;}
  }
class LinkedList {
    Node head; 
    public void add(int data) {
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
    public int sum() {
        int total = 0;
        Node current = head;
        while (current != null) {
            total += current.data;
            current = current.next;
        }
        return total;
    }}
public class SumofNodes {
    public static void main(String[] args) {
         LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        int sum = linkedList.sum();
        System.out.println("Sum of all nodes: " + sum);
        // TODO code application logic here
    }
    
}
