package codewars;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;

public class Ex9 {

    public static void main(String[] args) {

        int[] array = new int[]{2, 1, 1, 1, 2, 3, 4, 3, 3, 4, 5, 4, 5, 6};
        System.out.println(Arrays.toString(deleteNth(array, 2)));
    }

    public static int[] deleteNth(int[] elements, int maxOccurrences) {

        int occurrence = 0;
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements.length; j++) {

                if (elements[i] == elements[j]) {
                    occurrence = occurrence + 1;
                }

            }
            if (occurrence <= maxOccurrences) {
                intList.add(elements[i]);
            }

            occurrence=0;

        }

        int[] intArray = intList.stream().mapToInt(i -> i).toArray();


        return intArray;
    }
}
