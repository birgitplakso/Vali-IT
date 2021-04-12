package ee.bcs.valiit.tasks;

import org.apache.tomcat.util.security.Escape;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {

    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    public static void main(String[] args) {
        Random random = new Random();
        int generatedRandom = random.nextInt(100);

        numberGame(generatedRandom);

    }

    public static Scanner scanner = new Scanner(System.in);

    public static void numberGame(int randomNumber) {
        System.out.println("Arva ära suvaline number vahemikus 0-99");
        int numberOfGuesses = 0;
        while (true) {
            System.out.println("Sisesta number: ");
            int enteredNumber = scanner.nextInt();
            numberOfGuesses++;
            if (enteredNumber == randomNumber) {
                System.out.println(enteredNumber + " on õige number! Arvasid " + numberOfGuesses + " korda.");
                break;
            } else if (enteredNumber < randomNumber) {
                System.out.println("Sinu pakutud number on liiga väike");
            } else {
                System.out.println("Sinu pakutud number on liiga suur");
            }
        }
    }


}
