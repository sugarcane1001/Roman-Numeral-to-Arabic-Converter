import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
      //2 TextAreas for Romans and arabic values
      TextArea myRomanNumbers = new TextArea();
      TextArea myArabicNumbers = new TextArea();
      //adding the textAreas to the Container
      myContentPane.add(myRomanNumbers);
      myContentPane.add(myArabicNumbers);
      //giving each TextArea a title at the beginning
      myRomanNumbers.append("Roman Numerals\n");
      myArabicNumbers.append("Arabic Values\n");

      //setting the treemap structure with RomanNumeral and its corresponding Arabic value
      TreeMap<RomanNumeral, Integer> treeMap = new TreeMap<>(new RomanNumeralComparator());

      //running a for loop to iterate through every element in romanArray
      for(int i=0; i<romanArray.length; i++) {
         try {
            //if the String from romanArray[i] passes the validity test, add it to the treemap
            if(RomanNumeral.isValidRoman(romanArray[i])) {
               RomanNumeral x = new RomanNumeral(romanArray[i]); //making the values from Strings to RomanNumerals because the treemap has RomanNumerals as its key
               treeMap.put(x, x.getarabicValue()); //adding the Roman value in key and its arabic value in value
            }
            //Otherwise, if its not valid, we throw the exception with message
            else 
               throw new IllegalRomanNumeralException("Invalid input: " + romanArray[i]);
         } catch (IllegalRomanNumeralException irne) { //catching the exception from try block and outputting in the console
            System.out.println(irne.getMessage());
         } //try-catch
      } //for  

      //Print the map iteratively
      Set set = treeMap.entrySet();
      Iterator i = set.iterator();
      while (i.hasNext()) { //while there are more keys left in the map
         Map.Entry me = (Map.Entry) i.next(); //Grab a key-value pair from the map
         myRomanNumbers.append(((RomanNumeral) me.getKey()).getromanString() + "\n"); //casting the key Object to RomanNumeral then appending it to the first textarea
         myArabicNumbers.append(me.getValue() + "\n"); //appending the corresponding value of the RomanNumeral to the second textarea
      } //while

      jframe.setVisible(true); //making the frame visible after filling up with values
   } //readSource

} //FileMenuHandler
