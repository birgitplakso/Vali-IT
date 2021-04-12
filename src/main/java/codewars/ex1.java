package codewars;

public class ex1 {

    public static void main(String[] args) {

        String talk="9 years old";

        System.out.println(returnAge(talk));

    }

    public static int returnAge(String childTalk){
        char age=childTalk.charAt(0);
        int ageInNr=Integer.parseInt(String.valueOf(age));

        return ageInNr;
    }

}
