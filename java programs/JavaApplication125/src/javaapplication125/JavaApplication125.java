/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication125;

/**
 *
 * @author dell
 */
public class JavaApplication125 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3, 4, 5};
        int elementToInsert = 10;
        int insertIndex = 2;
        int[] newArray = new int[originalArray.length + 1];
        for (int i = 0; i < insertIndex; i++) {
            newArray[i] = originalArray[i];
        }
        newArray[insertIndex] = elementToInsert;
        for (int i = insertIndex + 1; i < newArray.length; i++) {
            newArray[i] = originalArray[i - 1];
        }
        System.out.print("Original Array: ");
        for (int num : originalArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.print("Updated Array: ");
        for (int num : newArray) {
            System.out.print(num + " ");
        }
    }
}
