/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication123;

/**
 *
 * @author dell
 */
public class JavaApplication123 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int[][]marks=new int[2][3];
         marks[0][0]=101;
          marks[0][1]=102;
           marks[0][2]=103;
            marks[1][0]=201;
             marks[1][1]=202;
              marks[1][2]=203;
        for(int i=0; i<marks.length;i++){
             for(int j=0; j<marks[i].length; j++){
            System.out.println(marks[i][j]);
        }
        // TODO code application logic here
    }
    
}}
