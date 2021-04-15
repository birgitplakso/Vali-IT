package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomController {
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    int count=0;

    @GetMapping("numberGame/{enterNumber}")
    public String numberGame(@PathVariable("enterNumber") int enteredNumber) {


        count=count+1;

            if (enteredNumber == randomNumber) {
                return "Õige number! Arvasid "+count+" korda.";
            } else if (enteredNumber < randomNumber) {
                return "Sinu pakutud number on liiga väike";

            } else {
                return "Sinu pakutud number on liiga suur";
            }

    }


}
