package codewars;


import java.util.ArrayList;

import java.util.List;


public class Ex10 {

    public static void main(String[] args) {
        System.out.println(buddy(1, 8000));

    }

    public static String buddy(long start, long limit) {

        long sum = 1;
        long secondSum = 1;
        long firstBuddy = 0;
        long secondBuddy = 0;


        for (long i = start; i <= limit; i++) { // variant kuidas tsüklid saada lühemaks-> ruutjuur limiidist
            long maxI=(long)Math.sqrt(i);
            for (long j = 2; j < maxI; j++) {
                if (i % j == 0) {
                    sum += j;
                    long d=i/j;
                    if(d!=i){
                        sum+=d;
                    }
                }
            }
            long possibleBuddy = sum - 1;
            for (long x = 2; x <= (long)Math.sqrt(possibleBuddy); x++) {
                if (possibleBuddy % x == 0) {
                    secondSum +=x;
                    long dd=possibleBuddy/x;
                    if(dd!=i){
                        secondSum+=dd;
                    }
                }
            }
            if (secondSum - 1 == i) {
                firstBuddy = i;
                secondBuddy = possibleBuddy;
                if(secondBuddy<firstBuddy){
                    firstBuddy=possibleBuddy;
                    secondBuddy=i;
                    //peab tagastama väiksema numbri esimesena
                    break;
                }

                break;
            }
            sum = 1;
            secondSum = 1;
        }
        if (firstBuddy==0 && secondBuddy==0){
            return "Nothing";
        }else{
            return "("+firstBuddy+","+secondBuddy+")";
        }
    }




    //int maxD = (int)Math.sqrt(input);
    //    int sum=1;
    //    for(int i = 2; i <= maxD; i++)
    //    {
    //        if(input % i == 0)
    //        {
    //           sum += i;
    //           int d = input/i;
    //           if(d!=i)
    //              sum+=d;
    //        }
    //    }
}
