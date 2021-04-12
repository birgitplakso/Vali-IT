package ee.bcs.valiit.tasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(reverseString("Hello"));
        System.out.println(isPrime(0));
        System.out.println(isPrime(5));
        System.out.println(isPrime(11));
        System.out.println(isPrime(13));
        System.out.println(isPrime(16));
        int[] myArray = new int[]{7, 3, 0, 15, 688, 9, 1, 66};
        sort(myArray);
        System.out.println(Arrays.toString(myArray));

        System.out.println(morseCode("SOS"));
    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int factorial = 1;
        for (int i = x; i > 0; i--) {
            factorial = factorial * i;
        }

        return factorial;
    }

    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {
        int aLength = a.length();
        String b = "";
        for (int i = aLength - 1; i >= 0; i--) {
            b = b + a.charAt(i);

            //char x=a.charAt(i)
            // b.charAt(aLength-1-1)=x; - ei saa kuna Stringi ei saa muuta, tuleb uus teha ja liita sinna tähti.
        }

        return b;
    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        if (x == 2) {
            return true;
        } else if (x > 2) {
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        boolean flag = true;
        int n = 0;
        while (flag) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
            n = n + 1;
            if (n >= a.length) {
                flag = false;
            }
        }
        return a;
    }

//    public static int evenFibonacci(int x){
//        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
//
//        int fibSum=0;
//        int fib=0;
//        for(int i=2; i<=x; i++){
//            if(fibonacci_num_check(i)){
//                fib=i;
//                if(fib%2==0){
//                    fibSum=fibSum+fib;
//                }
//            }
//
//        }
//        return fibSum;
//    }
//    public static boolean perfect_square_check(int val){
//        int s = (int) Math.sqrt(val);
//        return (s*s == val);
//    }
//    public static boolean fibonacci_num_check(int n){
//        return perfect_square_check(5*n*n + 4) || perfect_square_check(5*n*n - 4);
//    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x

        int a = 0;
        int b = 1;
        int fibSum = 0;
        for (int i = 0; i < x; i++) {
            int c = a + b;
            a = b;
            b = c;
            if (c >= x) {
                break;
            }
            if (c % 2 == 0) {
                fibSum = fibSum + c;
            }
        }
        return fibSum;
    }


    //hello ja sos on testis
    // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
    // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga

    public static String morseCode(String text) {
        String output = "";
        int inputLength = text.length();

        Map<Character, String> morseCode = new HashMap<>();
        morseCode.put('s', "...");
        morseCode.put('o', "---");
        morseCode.put('h', "....");
        morseCode.put('e', ".");
        morseCode.put('l', ".-..");
        morseCode.put('a', ".-");
        morseCode.put('b', "-...");
        morseCode.put('c', "-.-.");
        morseCode.put('d', "-..");

        for (int i = 0; i < inputLength; i++) {
            char textCharacter = text.toLowerCase(Locale.ROOT).charAt(i);
            output = output + morseCode.get(textCharacter) + " ";


        }
        return output.trim();
    }
}
