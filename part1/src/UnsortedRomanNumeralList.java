
public class UnsortedRomanNumeralList extends RomanNumeralList{

   public void append(RomanNumeral d) {
      RomanNumeralListNode n = new RomanNumeralListNode(d); //create a new node n
      last.next = n; //tail links to n
      last = n; //n becomes new tail
      length++; //update the length
   } //append

} //class UnsortedRomanNumeralList
