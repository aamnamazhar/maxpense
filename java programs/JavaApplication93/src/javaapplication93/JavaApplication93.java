/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication93;

/**
 *
 * @author dell
 */
public class JavaApplication93 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int[] A = {1, 2, 3, 4, 5};
        int[] c = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            c[i] = A[i];
        }
        System.out.print("Copied Array: ");
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        // TODO code application logic here
    }
    
}
