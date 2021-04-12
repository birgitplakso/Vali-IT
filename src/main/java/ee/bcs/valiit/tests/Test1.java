package ee.bcs.valiit.tests;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {

        System.out.println(dividesThreeOrSeven(21));
        System.out.println(dividesThreeOrSeven(7));
        System.out.println(dividesThreeOrSeven(8));
        System.out.println(dividesThreeOrSeven(6));

        int[] myArray = new int[]{4, 1, 8, 5};

        System.out.println(Arrays.toString(addToArray(myArray,3)));


    }
//    ÜL 1
//    Tee funktsioon, mis tagastab boolean väärtuse ja võtab sisse ühe parameetri
//    funktsioon peab tagastama
//		true: kui sisend parameeter jagub 3 või 7 (aga ei jagu mõlema numbriga)
//            false: kui sisend parameeter ei jagu 3 ega 7 või jagub mõlema numbriga

    public static boolean dividesThreeOrSeven(int x){
        if(x%7==0 && x%3==0 ){
            return false;
        }else if(x % 7 == 0){
            return true;
        }else if(x % 3 == 0){
            return true;
        }
        return false;
    }


    // ÜL2
    // lisa x igale array elemendile
    // Näiteks
    // sisend [1,2,3], 5
    // vastus [6,7,8]
    public static int[] addToArray(int[] array, int x){
        for(int i=0; i< array.length; i++){
            array[i]=array[i]+x;
        }

        return array;
    }

    public static int[] returnArray(int[] someArray){
        someArray[0]=0;

        return someArray;
    }

}
