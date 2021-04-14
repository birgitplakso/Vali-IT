package ee.bcs.valiit.controller;

import ee.bcs.valiit.sample.Employee;
import ee.bcs.valiit.tasks.Lesson1;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//meetodid või funktsioonid mis me tahame välja kutsuda: (restController käib *klassi* ette)
@RestController
public class TestController {

    List<Employee> employeeList=new ArrayList<Employee>();

    @GetMapping("employee/")
    public List<Employee> dtoTest(){
        return employeeList;
    }

    @GetMapping("employee/{id}")
    public Employee dtoTest(@PathVariable("id") int index){
        return employeeList.get(index);
    }

    @PostMapping("employee")
    public void dtoAdd(@RequestBody Employee employee){
        employeeList.add(employee);
    }

    @PutMapping("employee/{id}")
    public void dtoReplace(@PathVariable("id") int index,@RequestBody Employee employee){
        employeeList.set(index,employee);
    }

    @DeleteMapping("employee/{id}")
    public void dtoDelete(@PathVariable("id") int index){
        employeeList.remove(index);

    }

    //GetMapping tuleb lisada meetodi ette
    @GetMapping("sample/hello-world/{nameInUrl}") //see aadress tuleb lisada veebi: localhost:8080/ sample/hello-world
    public static String helloWorld(@PathVariable("nameInUrl") String name, @RequestParam("action") String a){
        return a+" "+name;

    }

    @GetMapping("/fibonacci/{n}")
    public int fibonacci(@PathVariable("n") int n){
        return Lesson2.fib(n);
    }

    @GetMapping("/evenNumbers/{n}")
    public int[] evenNumbers(@PathVariable("n") int n){
        return Lesson2.evenNumbers(n);
    }

    @GetMapping("/sum/{num}")
    public int sum(@PathVariable("num") int[] numArray){
        return Lesson2.sum(numArray);
    }

    @GetMapping("/max/{num}")
    public int max(@PathVariable("num") int[] numArray){
        return Lesson2.max(numArray);
    }

    @GetMapping("/min")
    public int min(@RequestParam("num") int[] numArray){
        return Lesson2.min(numArray);
    }

    @GetMapping("/reverse/{num}")
    public int[] reverse(@PathVariable("num") int[] numArray){
        return Lesson2.reverseArray(numArray);
    }

    @GetMapping("/factorial")
    public int factorial(@RequestParam("num") int a){
        return Lesson3.factorial(a);
    }

    @GetMapping("reverseString")
    public String reverse(@RequestParam("string") String a){
        return Lesson3.reverseString(a);
    }

    @GetMapping("isPrime")
    public boolean prime(@RequestParam("num") int a){
        return Lesson3.isPrime(a);
    }

    @GetMapping("sort/{numbers}")
    public int[] sort(@PathVariable("numbers") int[] array){
        return Lesson3.sort(array);
    }

    @GetMapping("evenFibonacci/{num}")
    public int evenFibonacci(@PathVariable("num") int n){
        return Lesson3.evenFibonacci(n);
    }

    @GetMapping("morseCode/{string}")
    public String morse(@PathVariable("string") String a){
        return Lesson3.morseCode(a);
    }

    @GetMapping("min/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a,
                   @PathVariable("b") int b,
                   @PathVariable("c") int c){
        return Lesson1.min3(a, b, c);
    }

    @GetMapping("max/{a}/{b}/{c}")
    public int max3(@PathVariable("a") int a,
                    @PathVariable("b") int b,
                    @PathVariable("c") int c){
        return Lesson1.max3(a, b, c);
    }

    @GetMapping("min/{a}/{b}")
    public int min(@PathVariable("a") int a,
                    @PathVariable("b") int b){
        return Lesson1.min(a, b);
    }

    @GetMapping("max/{a}/{b}")
    public int max(@PathVariable("a") int a,
                    @PathVariable("b") int b) {
        return Lesson1.max(a, b);
    }

    @GetMapping("abs")
    public int abs(@RequestParam("num") int a){
        return Lesson1.abs(a);
    }
    @GetMapping("isEven")
    public boolean isEven(@RequestParam("num") int a){
        return Lesson1.isEven(a);
    }

    @GetMapping("array")
    public int[] array(@RequestParam("numbers") int a){
        return Lesson2b.exercise1(a);
    }

    @GetMapping("sampleArray")
    public int[] sampleArray(){
        return Lesson2b.sampleArray();
    }

    @GetMapping("generateArray")
    public int[] generateArr(@RequestParam("num") int a){
        return Lesson2b.generateArray(a);
    }

    @GetMapping("decreasingArray")
    public int[] decreasingArr(@RequestParam("num") int a){
        return Lesson2b.decreasingArray(a);
    }

    @GetMapping("3Array")
    public int[] threeArr(@RequestParam("num") int a){
        return Lesson2b.yl3(a);
    }

    @GetMapping("multiplyTable/{x}/{y}")
    public int[][] multiplyTable(@PathVariable("x") int x,@PathVariable("y") int y){
        return Lesson2.multiplyTable(x,y);
    }


}
