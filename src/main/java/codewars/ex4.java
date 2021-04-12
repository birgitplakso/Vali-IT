package codewars;
//Your task is to sum the differences between consecutive pairs in the array in descending order.
//        For example: //        sumOfDifferences([2, 1, 10])   Returns 9
//        Descending order: [10, 2, 1]
//        Sum: (10 - 2) + (2 - 1) = 8 + 1 = 9
//        If the array is empty or the array has only one element
//        the result should be 0 (Nothing in Haskell).

import java.util.Arrays;



public class ex4 {

    public static void main(String[]args){

        int[] myArray=new int[]{6};
        int[] myArray2=new int[]{4,7,8,1,10,89,55,2};

        System.out.println(sumOfDifferences(myArray));
        System.out.println(sumOfDifferences(myArray2));

    }


    public static int sumOfDifferences(int[] arr){
        Arrays.sort(arr);
        int sum=0;
        for(int i=1; i<arr.length; i++){
            int difference=arr[i]-arr[i-1];
            sum = sum + difference;

        }

     return sum;
    }

}
