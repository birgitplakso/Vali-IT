package codewars;


import java.util.Arrays;

public class Ex6 {

    public static void main(String[] args) {

        int[] myArray = new int[]{15,11,10,7,12};

        System.out.println(Arrays.toString(solve(myArray)));

    }


    // In this Kata, you will be given an array of unique elements,
// and your task is to rearrange the values so that
// the first max value is followed by the first minimum,
// followed by second max value then second min value, etc.
//For example: solve([15,11,10,7,12]) = [15,7,12,10,11]

    public static int[] solve (int[] arr){
        Arrays.sort(arr);
        int[] outputArray=new int[arr.length];
        //defineerin indeksid mida kasutan sisendArray väärtuste lugemiseks (loop on vaja kaheks jagada)
        int i1=0;
        int i2=0;
        for(int i=1; i<= arr.length; i++){
            int index=i-1;
            //kui on paaritu i siis võtab sisend-array lõpust väärtuse ja lisab uude arraysse
            if(i%2!=0){
                outputArray[index]=arr[arr.length-1-i1];
                i1=i1+1;
             // kui on paaris i, siis võtab sisend-array algusest väärtuse ja lisab uude arraysse
            }else{
                outputArray[index]=arr[i2];
                i2=i2+1;
            }
        }
        return outputArray;
    }
}
