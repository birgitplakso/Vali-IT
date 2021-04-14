package codewars;


import java.util.Locale;

public class Ex8 {

    public static void main(String[] args) {

        System.out.println(toCamelCase("the-stealth-warrior"));

        System.out.println(toCamelCase("The_Stealth_Warrior"));
    }

    //Complete the method/function so that it converts dash/underscore delimited words into camel casing.
// The first word within the output should be capitalized only
// if the original word was capitalized (known as Upper Camel Case, also often referred to as Pascal case).
//Examples
//"the-stealth-warrior" gets converted to "theStealthWarrior"
//"The_Stealth_Warrior" gets converted to "TheStealthWarrior"


    static String toCamelCase(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        String returnString = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '-' && s.charAt(i) != '_') {
                returnString = returnString + s.charAt(i);
            } else if ((s.charAt(i) == '_') || (s.charAt(i) == '-')) {
                String toUpper = s.substring(i + 1, i + 2).toUpperCase(Locale.ROOT);
                returnString = returnString + toUpper;
                i++;
            }
        }
        return returnString;
    }


}
