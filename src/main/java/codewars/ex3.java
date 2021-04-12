package codewars;

public class ex3 {

    public static void main(String[] args) {
        System.out.println(toBinary(4));
        System.out.println(toBinary(300));
        System.out.println(toBinary(256));

    }

    public static int toBinary(int number) {
        String x = "";
        while (number > 1) {
            if(number%2==0){
                x=x+"0";
            }else{
                x=x+"1";
                number--;
            }
            number=number/2;

        }
        x=x+1;

        int xLength=x.length();
        String b="";
        for(int i=xLength-1; i>=0; i--){
            b=b+x.charAt(i);
        }

        int binary=Integer.parseInt(b);
        return binary;

    }

}
