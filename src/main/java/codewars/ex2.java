package codewars;

public class ex2 {



    public static void main(String[] args) {

        int[] myArray=new int[]{1,3};

        System.out.println(nthPower(myArray,2));
    }

    public static int nthPower(int[] array, int n){
        if(n> array.length-1){
            return -1;
        }
        int number=array[n];
        int nthPower=number;

        for(int i=0; i<n-1; i++){
            nthPower=nthPower*number;
        }
        return nthPower;
    }
}
