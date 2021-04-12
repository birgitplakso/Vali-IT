package codewars;

import java.util.Locale;

public class ex5 {

    public static void main(String[] args) {


        System.out.println(toJadenCase("i have a dream"));
    }

    public static String toJadenCase(String phrase) {
        if(phrase==null || phrase.equals("")){
            return null;
        }
        String toJadenCase=phrase.substring(0,1).toUpperCase(Locale.ROOT);

        for(int i=1; i<phrase.length(); i++){
            if(!phrase.substring(i,i+1).equals(" ")){
                toJadenCase=toJadenCase+phrase.substring(i,i+1);
            }else if(phrase.substring(i,i+1).equals(" ")){
                String toUpper=phrase.substring(i+1,i+2).toUpperCase(Locale.ROOT);
                toJadenCase=toJadenCase+" "+toUpper;
                i++;
            }

        }

        return toJadenCase;
    }
}
