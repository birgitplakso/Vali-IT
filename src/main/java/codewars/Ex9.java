package codewars;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;

public class Ex9 {

    public static void main(String[] args) {

        int[] array = new int[]{2, 1, 1, 1, 2, 3, 4, 3, 3, 4, 5, 4, 5, 6};
        System.out.println(Arrays.toString(deleteNth(array, 0)));
    }

    public static int[] deleteNth(int[] elements, int maxOccurrences) {

        HashMap<Integer,Integer> numMap=new HashMap<>();

        List<Integer> resultList = new ArrayList<>();
        int[] intArray;

        if(maxOccurrences<1){
            intArray=new int[0];
        }else{
            for (int i = 0; i < elements.length; i++) {
                int number=elements[i];
                Integer n=numMap.get(number);

                if(n==null){
                    numMap.put(number,1);
                    resultList.add(number);
                }else{
                    n++;
                    if(n<=maxOccurrences){
                        numMap.replace(number,n);
                        resultList.add(number);
                    }
                }
            }

            intArray = resultList.stream().mapToInt(i -> i).toArray();

        }

        return intArray;
    }






    static Map<Integer, Integer> countMap = new HashMap<>();

    public static int[] DdeleteNth(int[] elements, int maxOccurrences) {


        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            if (canAdd(elements, i, maxOccurrences)) {
                resultList.add(elements[i]);
            }
        }

        return resultList.stream().mapToInt(i -> i).toArray();
    }



    public static boolean canAdd(int[] input, int i, int n) {
        int nr = input[i];
        Integer count = countMap.get(nr);
        if (count == null) {
            count = 0;
        }
        if (count < n) {
            countMap.put(nr, count + 1);
            return true;
        } else {
            return false;
        }
    }

}

