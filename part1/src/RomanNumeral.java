import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumeral {

   private String romanNumeral;
   private int arabicValue;

   public RomanNumeral(String s) {
      romanNumeral = s;
      arabicValue = valueOf(s);
   } //constructor

   public String getromanString() {
      return romanNumeral;
   } //get method for Roman

   public void setromanString(String s) {
      romanNumeral = s;
   } //set method for Roman

   public int getarabicValue() {
      return arabicValue;
   } //get method for Arabic value

   public int compareTo(RomanNumeral other) {
      return this.getarabicValue() - other.getarabicValue(); //returns the value of current RomanNumeral - passed RomanNumeral
   } //compareTo

   //valueOf method that will return an integer value of a Roman numeral
   public static int valueOf(String word) {
      int result = 0;
      int currValue = 0;
      int prevValue = 0;

      for(int i=word.length()-1; i>=0; i--) { //iterating from rightmost char to leftmost char
         char x = word.charAt(i);
         //setting integer values to each char so that I can compare later
         if(x == 'I') currValue = 1;
         else if(x == 'V') currValue = 5;
         else if(x == 'X') currValue = 10;
         else if(x == 'L') currValue = 50;
         else if(x == 'C') currValue = 100;
         else if(x == 'D') currValue = 500;
         else if(x == 'M') currValue = 1000;

         if(currValue < prevValue) result -= currValue; //if left char has smaller value than right char, we subtract left char
         else result += currValue; //otherwise add

         prevValue = currValue; //once we done with a char, that char becomes previous char
      } //for
      return result;
   } //valueOf

   //boolean method that will return true or false based on the validity of a RomanNumeral
   public static boolean isValidRoman(String r) {
      Pattern p = Pattern.compile("[MDCLXVI]+"); //making the pattern with every valid Roman Numeral character
      Matcher m = p.matcher(r);
      return m.matches(); //it will return false if any of the character from the String r has anything other than valid Roman numeral characters
   } //isValidRoman

} //class RomanNumeral
