package Sorting;

import java.util.Arrays;

public class sort {
	int []sorting=new int[15];
	
	public void srting(){
		
			System.out.println("\n-----------Sorting------------------");			
			int[]array={-9, -7, -3, -2, 0, 2, 4, 5, 6, 8};//Inpt.nextInt();
			System.out.println("\n-----------Before Sorting------------------");			
			for(int i=0;i<sorting.length;i++)
			      Arrays.sort(array);
		      printArray("Sorted array", array);
		      int index = Arrays.binarySearch(array, 1);
		      System.out.println("Didn't find 1 @ "+
		      + index);
		      int newIndex = -index - 1;
		      array = insertElement(array, 1, newIndex);
		      printArray("With 1 added", array);


			
	}

	   public  void printArray(String message, int array[]) {
		      System.out.println(message
		      + ": [length: " + array.length + "]");
		      for (int i = 0; i < array.length; i++) {
		         if (i != 0){
		            System.out.print(", ");
		         }
		         System.out.print(array[i]);         
		      }
		      System.out.println();
		   }
		   public   int[] insertElement(int original[],
		   int element, int index) {
		      int length = original.length;
		      int destination[] = new int[length + 1];
		      System.arraycopy(original, 0, destination, 0, index);
		      destination[index] = element;
		      System.arraycopy(original, index, destination, index
		      + 1, length - index);
		      return destination;
		   }

}
