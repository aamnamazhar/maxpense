/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication100;
class Stack {
   private int[] array;
   private int top;
   
   public Stack(int capacity) {
      array = new int[capacity];
      top = -1;
   }
   
   public void push(int element) {
      if (top == array.length - 1) {
         System.out.println("Stack is full. Cannot push element.");
      } else {
         top++;
         array[top] = element;
         System.out.println("Pushed element: " + element);
      }
   }
   
   public int pop() {
      if (top == -1) {
         System.out.println("Stack is empty. Cannot pop element.");
         return -1;
      } else {
         int poppedElement = array[top];
         top--;
         System.out.println("Popped element: " + poppedElement);
         return poppedElement;
      }
   }
   
   public int peek() {
      if (top == -1) {
         System.out.println("Stack is empty. No element to peek.");
         return -1;
      } else {
         System.out.println("Peeked element: " + array[top]);
         return array[top];
      }
   }
   
   public boolean isEmpty() {
      return (top == -1);
   }
}
/**
 *
 * @author dell
 */
public class JavaApplication100 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack stack = new Stack(5);
      
      stack.push(10);
      stack.push(20);
      stack.push(30);
      
      stack.pop();
      
      stack.push(40);
      stack.push(50);
      
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
        // TODO code application logic here
    }
    
}
