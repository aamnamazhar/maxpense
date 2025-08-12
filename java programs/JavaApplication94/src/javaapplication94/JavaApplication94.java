/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication94;

/**
 *
 * @author dell
 */
public class JavaApplication94 {

    /**
     * @param args the command line arguments
     */
    public static int removeduplicates(int a[], int n){
        
        if (n == 0) {
                return n;
        }
        int[] temp = new int[n];
        int j=0 ;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) {
            temp[j++] = a[i];
            }
        }
        temp[j++] = a[n - 1];
         for (int i = 0; i < j; i++) {
            a[i] = temp[i];}
        return j;
	}

	public static void main(String[] args)
	{
		int a[] = {1,1,2,2,3,3,4,4,5,5,7};
		int n = a.length;
		n=removeduplicates(a,n);
		for (int i = 0; i < n; i++)
			System.out.print(a[i] + " ");
	}
}

        // TODO code application logic here
 
    
