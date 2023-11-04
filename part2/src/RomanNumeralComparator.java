import java.util.Comparator;

public class RomanNumeralComparator implements Comparator<RomanNumeral> {

   public int compare(RomanNumeral num1, RomanNumeral num2) {//comparing two RomanNumerals by calling the compareTo method
      return num1.compareTo(num2); //it will return a negative number if num1 is smaller than num2, otherwise return positive number
   } //compare
} //RomanNumeralComparator
