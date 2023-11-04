
public class SortedRomanNumeralList extends RomanNumeralList{

   public void add(RomanNumeral d) {
      RomanNumeralListNode n = new RomanNumeralListNode(d);
      RomanNumeralListNode prev = first; //one node to keep track of previous node
      RomanNumeralListNode curr = first.next; //another node to keep track of current node

      while (curr != null) {
         if(d.compareTo(curr.data)<0){ //if n has smaller value than current node
            prev.next = n; //link n to previous node
            n.next = curr; //link current to the node n
            length++; //update the length
            return; //return it to the list
         }
         prev = curr; //current node becomes previous node
         curr = curr.next; //update current node to curr.next
      }
      prev.next = n; //then we add n to the end of the list. After the while loop ends, everything inside there is sorted and prev now holds the last sorted value
      last = n; //make n the new tail
      length++; //update length
   } //add

} //SortedRomanNumeralList
