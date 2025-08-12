/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication118;
import java.util.LinkedList;
/**
 *
 * @author dell
 */
public class JavaApplication118 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> a=new LinkedList<>();
        a.add(1);
           a.add(5);
           a.add(10);
           a.add(20);
           a.add(30);
           a.add(40);
           a.add(50);
           a.add(60);
           a.add(70);
           System.out.println("Elements: " +a);
           a.removeFirst();
            System.out.println();
           System.out.println("After removing first Element");
            System.out.println(a);
        // TODO code application logic here
    }
    
}
