/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication99;
import java.io.*;

 class Stack {
   private int maxStack;
   private int emptyStack;
   private int top;
   private char[] items;

public Stack(int size) {
      maxStack= size;
      emptyStack = -1;
      top = emptyStack;
      items = new char[maxStack];
   }

   public void push(char c) {
      items[++top] = c;
   }

   public char pop() {
      return items[top--];
   }

   public boolean full()  {
      return top + 1 == maxStack;
   }
public boolean empty()  {
      return top == emptyStack;
   }
}

/**
 *
 * @author dell
 */
public class JavaApplication99 {
    public static void main(String[] args)throws IOException {
            Stack s = new Stack(10); // 10 chars
    char ch;
    System.out.println("Please enter 10 characters:");
    while ((ch = (char)System.in.read())!= '\n')
       if (!s.full()) s.push(ch);

    while (!s.empty())
       System.out.print(s.pop());

    System.out.println();

        // TODO code application logic here
    }
    
}
