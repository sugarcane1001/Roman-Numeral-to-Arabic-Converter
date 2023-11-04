
public class RomanNumeralList {

   public RomanNumeralListNode first = new RomanNumeralListNode(null);
   public RomanNumeralListNode last = first;
   public int length = 0;

   public int getLength() {
      return length;
   } //will return length of the linked list

   public String toString() {
      RomanNumeralListNode p = first.next;
      String returnString = ""; //setting the return value null
      while (p != null) { //as long as the list has more nodes
         returnString += p.data + " "; //adding each nodes one after another
         p = p.next; //changing the current node after it has been stored
      }
      return returnString;
   } //toString

   public boolean equals(Object other) {
      //if the passed object is null or class types are different or does not have same length, it returns false
      if (other == null || getClass() != other.getClass() || length != ((RomanNumeralList) other).length)
         return false;

      RomanNumeralListNode nodeThis = first;
      RomanNumeralListNode nodeOther = ((RomanNumeralList) other).first; //parsing the object to RomanNumeralList
      while (nodeThis != null) {
         // Since the two linked lists are the same length, they should reach null on the same iteration

         if (nodeThis.data != nodeOther.data) //if any data values are different in one iteration
            return false;

         nodeThis = nodeThis.next; //changing the current node to become the next node
         nodeOther = nodeOther.next;
      } // while
      return true; //if it passes all cases, they are equal
   } //equals

} //RomanNumeralList
