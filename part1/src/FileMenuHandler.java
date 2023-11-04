import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;
public class FileMenuHandler implements ActionListener {

   JFrame jframe;
   public static StringTokenizer myTokens;
   public static String[] romanArray = new String[8];

   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   } //constructor

   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Open")) //if the user clicks open
         openFile();
      else if (menuName.equals("Quit")) //if the user clicks quit
         System.exit(0); //close the program
   } //actionPerformed

   private void openFile() {
      JFileChooser chooser = new JFileChooser();
      int status = chooser.showOpenDialog(null);
      if (status == JFileChooser.APPROVE_OPTION) //if the user successfully opens a file, call readSource method with the users chosen file
         readSource(chooser.getSelectedFile());
      else 
         JOptionPane.showMessageDialog(null, "Open File dialog canceled");
   } //openFile

   private void readSource(File chosenFile) {
      String chosenFileName = chosenFile.getAbsolutePath(); //setting the path of the file to a string

      TextFileInput in = new TextFileInput(chosenFileName);
      String line = in.readLine();
      int j=0;
      while(line != null) { //as long as the file has more lines to read
         myTokens = new StringTokenizer(line, ","); //setting myTokens structure

         while(myTokens.hasMoreTokens()) { //as long as myTokens has more tokens left from line
            String word = myTokens.nextToken(); //storing the next token in the variable word
            romanArray[j] = word; //storing the Roman values in the romanArray array
            j++; //updating the counter
         } //while

         line = in.readLine(); //reading the next line in file
      } //while

      Container myContentPane = jframe.getContentPane();
      myContentPane.removeAll(); //clearing the container so that if the user opens a second file, it does not overlap with the previous one

      //3 TextAreas for Romans, unsorted values and sorted values
      TextArea myRomanNumbers = new TextArea();
      TextArea unsorted = new TextArea();
      TextArea sorted = new TextArea();
      //adding the textAreas to the Container
      myContentPane.add(myRomanNumbers);
      myContentPane.add(unsorted);
      myContentPane.add(sorted);
      //giving each TextArea a title at the beginning
      myRomanNumbers.append("Roman Numerals\n");
      unsorted.append("Unsorted Values\n");
      sorted.append("Sorted Values\n");

      //two linked lists for unsorted and sorted list
      UnsortedRomanNumeralList myList1 = new UnsortedRomanNumeralList();
      SortedRomanNumeralList myList2 = new SortedRomanNumeralList();

      //running a for loop to iterate through every element in romanArray
      for (int i = 0; i < romanArray.length; i++) {
         try {
            //if the String from romanArray[i] passes the validity test, add it to myList1 and myList2
            if(RomanNumeral.isValidRoman(romanArray[i])) {
               RomanNumeral x = new RomanNumeral(romanArray[i]); //making the values from Strings to RomanNumerals because append and add method has RomanNumeral as parameter
               myList1.append(x); //calling the append method from UnsortedRomanNumeralList
               myList2.add(x); //calling the add method from SortedRomanNumeralList
            }
            //Otherwise, if its not valid, we throw the exception with message
            else 
               throw new IllegalRomanNumeralException("Invalid input: " + romanArray[i]);
         } catch (IllegalRomanNumeralException irne) { //catching the exception from try block and outputting in the console
            System.out.println(irne.getMessage());
         } //try-catch
      } //for

      RomanNumeralListNode currentUnsortedNode = myList1.first.next; //tracking the nodes in unsorted list
      RomanNumeralListNode currentSortedNode = myList2.first.next; //tracking the nodes in sorted list
      while(currentUnsortedNode != null){
         //since both linked lists are of same length, they will reach the end in the same iteration
         //adding the lists to the TextAreas
         myRomanNumbers.append(currentUnsortedNode.data.getromanString() + "\n");
         unsorted.append(currentUnsortedNode.data.getarabicValue() + "\n");
         sorted.append(currentSortedNode.data.getarabicValue() + "\n");
         currentUnsortedNode = currentUnsortedNode.next; //updating the node in unsorted list
         currentSortedNode = currentSortedNode.next; //updating the node in sorted list
      } //while

      jframe.setVisible(true); //making the frame visible after filling up with values
   } //readSource

} //FileMenuHandler
