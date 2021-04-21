package codewars;


public class Ex10 {

    public static void main(String[] args) {
        System.out.println(buddy(381, 4318));

    }

    public static String buddy(long start, long limit) {

        long sum = 0;
        long secondSum = 0;
        long firstBuddy = 0;
        long secondBuddy = 0;

        for (long i = start; i <= limit; i++) { // variant kuidas tsüklid saada lühemaks, ruutjuur limiidist
            for (long j = 1; j < i; j++) {
                if (i % j == 0) {
                    sum = sum + j;
                }
            }
            long possibleBuddy = sum - 1;
            for (long x = 1; x < possibleBuddy; x++) {
                if (possibleBuddy % x == 0) {
                    secondSum = secondSum + x;
                }
            }
            if (secondSum - 1 == i) {
                firstBuddy = i;
                secondBuddy = possibleBuddy;
                break;
            }
            sum = 0;
            secondSum = 0;
        }

        return "[" + firstBuddy + ", " + secondBuddy + "]";
    }
}
