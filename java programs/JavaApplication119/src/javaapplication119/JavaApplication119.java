/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication119;

/**
 *
 * @author dell
 */
public class JavaApplication119 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int [] arr={1,2,3,4,5};
        int elementtoinsert=10;
        int insertindex=2;
        int[] arr1= new int[arr.length+1];
        System.out.println("original array");
        for(int i=0; i<arr.length;i++){
            arr1[i]=arr[i];
               System.out.println(+arr[i]);
        }
         System.out.println();

        System.out.print("Updated Array: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
    
}}}
