
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
public class ConvertMenuHandler implements ActionListener {

   JFrame jframe;

   public ConvertMenuHandler (JFrame jf) {
      jframe = jf;
   } //constructor

   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Roman to Arabic")) {
         String input = JOptionPane.showInputDialog(null,"Give me a Roman numeral: "); //storing the user input
         try {
            //if the user input returns true from the method isValidRoman
            if(RomanNumeral.isValidRoman(input))
               JOptionPane.showMessageDialog(null,"Your input value is: " + RomanNumeral.valueOf(input));
            //Otherwise, if its not valid, we throw the exception with message
            else {
               JOptionPane.showMessageDialog(null,"Your input value is invalid.");
               throw new IllegalRomanNumeralException("Invalid input: " + input);
            }
         } catch (IllegalRomanNumeralException irne) { //catching the exception from try block and outputting in the console
            System.out.println(irne.getMessage());
         } //try-catch
      } //if the action command is "Roman to Arabic"
   } //actionPerformed

} //ConvertMenuHandler
