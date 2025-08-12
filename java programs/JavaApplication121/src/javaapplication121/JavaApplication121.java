/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication121;
import java.util.LinkedList;
import java.util.Queue;
class Main {
   
    public void queueExample() {
    
        Queue queue = new LinkedList();
        queue.add("item1");
        queue.add("item2");
       
        queue.offer("Item3");
        queue.offer("Item4");
        System.out.println("remove: " + queue.remove());
        System.out.println("element: " + queue.element());
        
        System.out.println("poll: " + queue.poll());

        System.out.println("peek: " + queue.peek());
        
    }
}
public class JavaApplication121 {
    public static void main(String[] args) {
        new Main().queueExample();
    }
    
}
