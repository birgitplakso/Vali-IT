package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(exercise1(5)));
        System.out.println("Näidis massiv" + Arrays.toString(sampleArray()));
        System.out.println();
        System.out.println("Genereeritud massiiv" + Arrays.toString(generateArray(8)));
        System.out.println();
        System.out.println("Kahanev massiv" + Arrays.toString(decreasingArray(0)));
        System.out.println();
        System.out.println("yl3 " + Arrays.toString(yl3(10)));


    }

    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static int[] exercise1(int n) {
        int[] array=new int[n];
        for (int i = 1; i <= n; i++) {
            array[i-1]=i;

        }
        return array;
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        return new int[]{1, 2, 3, 4, 5};

    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        int[] myArray = new int[n];
        for (int i = 0; i < n; i++) {
            myArray[i] = i + 1;
        }
        return myArray;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {
        int arrayLength = Math.abs(n) + 1;
        int[] decreasingArray = new int[arrayLength];
        int index = 0;
        if (n >= 0) {
            for (int i = n; i >= 0; i--) {
                decreasingArray[index] = i;
                index++;
            }
        } else {
            for (int i = n; i <= 0; i++) {
                decreasingArray[index] = i;
                index++;
            }
        }

        return decreasingArray;
    }

    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = 3;
        }
        return array;

    }
}
