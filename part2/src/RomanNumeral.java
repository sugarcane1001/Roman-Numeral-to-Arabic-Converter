import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

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
      //if nothing is entered or input length is 0, we return -1
      if (word == null || word.length() == 0) return -1;

      //setting the hashmap structure with a character and its corresponding integer value
      HashMap<Character, Integer> map = new HashMap<Character, Integer>();
      map.put('I', 1);
      map.put('V', 5);
      map.put('X', 10);
      map.put('L', 50);
      map.put('C', 100);
      map.put('D', 500);
      map.put('M', 1000);
      int wordLength = word.length();
      int result = map.get(word.charAt(wordLength - 1)); //initializing result to the rightmost character value
      //iterating from second rightmost char to leftmost char
      for (int i = wordLength - 2; i >= 0; i--) {
         //if the left char is greater or equal than right char, we add the value to result
         if (map.get(word.charAt(i)) >= map.get(word.charAt(i + 1)))
            result += map.get(word.charAt(i));
         //otherwise we subtract
         else
            result -= map.get(word.charAt(i));
      } //for
      //returning the result came out from the hashmap
      return result;
   } //valueOf

   //boolean method that will return true or false based on the validity of a RomanNumeral
   public static boolean isValidRoman(String r) {
      Pattern p = Pattern.compile("[MDCLXVI]+"); //making the pattern with every valid Roman Numeral character
      Matcher m = p.matcher(r);
      return m.matches(); //it will return false if any of the character from the String r has anything other than valid Roman numeral characters
   } //isValidRoman

} //class RomanNumeral
