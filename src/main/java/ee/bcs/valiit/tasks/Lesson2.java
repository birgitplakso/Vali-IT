package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

public class Lesson2 {

    public static void main(String[] args) {

        int[] myArray = new int[]{1, 8, 9, 5, 6};

        System.out.println(Arrays.toString(reverseArray(myArray)));

        System.out.println("Array of doubles: " + Arrays.toString(evenNumbers(8)));

        System.out.println("min on: " + min(myArray));
        System.out.println("max on: " + max(myArray));
        System.out.println("sum on: " + sum(myArray));

        multiplyTable(4, 4);

        System.out.println(fibonacci(4));

        System.out.println(sequence3n(10, 20));


    }

    // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras
    public static int[] reverseArray(int[] inputArray) {
        int[] outputArray = inputArray.clone();
        int last = outputArray.length - 1;
        int middle = outputArray.length / 2;
        for (int i = 0; i < middle; i++) {
            int temp = outputArray[i];
            outputArray[i] = outputArray[last - i];
            outputArray[last - i] = temp;
        }

        return outputArray;
    }

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) {
        int[] arrOfDbl = new int[n];
        for (int i = 0; i < n; i++) {
            arrOfDbl[i] = (i + 1) * 2;
        }
        return arrOfDbl;
    }

    // TODO, leia massiivi kõige väiksem element
    public static int min(int[] x) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < min) {
                min = x[i];
            }
        }
        return min;
    }

    // TODO, leia massiivi kõige suurem element
    public static int max(int[] x) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > max) {
                max = x[i];
            }
        }
        return max;
    }

    // TODO, leia massiivi kõigi elementide summa
    public static int sum(int[] x) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum = sum + x[i];
        }
        return sum;
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee

    public static int[][] multiplyTable(int x, int y) {
        int[][] multiplyTable = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                multiplyTable[i][j] = (i + 1) * (j + 1);
                System.out.print(multiplyTable[i][j] + "\t");

            }
            System.out.println();
            //break; et oleks ainult esimene rida
        }
        return multiplyTable;

    }

    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fib(int n) {
        int a = 0;
        int b = 1;
        int c;
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }




    // TODO
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20

    public static int sequence3n(int x, int y) {

        int maxSequence = 0;

        for (int i = x; i <= y; i++) {
            int numberOfActions = 0;
            int result = i;
            while (result >= 1) {
                if (result == 1) {
                    break;
                } else if (result % 2 == 0) {
                    result = result / 2;
                    numberOfActions++;
                } else {
                    result = (3 * result) + 1;
                    numberOfActions++;
                }
            }
            if (numberOfActions > maxSequence) {
                maxSequence = numberOfActions;
            }
        }
        return maxSequence;
    }


}
