/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication109;
import java.util.LinkedList;
/**
 *
 * @author dell
 */
public class JavaApplication109 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> a= new LinkedList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        System.out.println("List Elements: " +a);
       
        a.remove(3);
         System.out.println("After removing index three Element: " +a);
       
        
        // TODO code application logic here
    }
    
}
