package ee.bcs.valiit.tasks;

import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = enterChoiceOfMethod();

        if (choice == 1) {
            System.out.println("Sisesta kaks numbrit, millest soovid miinimumi: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int minimum = min(a, b);
            System.out.println("Vastus: " + minimum);
        } else if (choice == 2) {
            System.out.println("Sisesta kaks numbrit, millest soovid maksimumi: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int maximum = max(a, b);
            System.out.println("Vastus: " + maximum);
        } else if (choice == 3) {
            System.out.println("Sisesta number, millest soovid absoluut arvu: ");
            int a = scanner.nextInt();
            int absolut = abs(a);
            System.out.println("Vastus: " + absolut);
        } else if (choice == 4) {
            System.out.println("Sisesta number, mille kohta soovid teada kas on paarisarv: ");
            int a = scanner.nextInt();
            boolean ifIsEven = isEven(a);
            System.out.println("Vastus: " + ifIsEven);
        } else if (choice == 5) {
            System.out.println("Sisesta kolm numbrit, millest soovid miinimumi: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int min3 = min3(a, b, c);
            System.out.println("Vastus: " + min3);
        } else if (choice == 6) {
            System.out.println("Sisesta kolm numbrit, millest soovid maksimumi: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int max3 = max3(a, b, c);
            System.out.println("Vastus: " + max3);
        } else {
            System.out.println("Sisestatud number ei ole vahemikus 1-6");
        }


    }

    public static int enterChoiceOfMethod() {
        System.out.println("Vali meetod mida soovid kasutada: \n" +
                "1 - kui soovid kasutada meetodit MIN \n" +
                "2 - kui soovid kasutada meetodit MAX \n" +
                "3 - kui soovid kasuatada meetodit ABS \n" +
                "4 - kui soovid kasutada meetodit IsEVEN \n" +
                "5 - kui soovid kasutada meetodit MIN3 \n" +
                "6 - kui soovid kasutada meetodit MAX3 \n" +
                "sisesta vastav number: ");
        return scanner.nextInt();
    }


    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }

    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
        if (a > 0) {
            return a;
        }
        return a * (-1);

    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        return a % 2 == 0;
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        // return min(min(a,b),c);
        if (a <= b && a <= c) {
            return a;
        }
        else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        // return max(max(a,b),c);
        if (a >= b && a >= c) {
            return a;
        }
        else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}
