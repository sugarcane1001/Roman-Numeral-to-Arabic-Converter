
import javax.swing.*;
import java.awt.*;
public class RomanNumeralGUI extends JFrame {

   JMenuBar menuBar = new JMenuBar();

   public RomanNumeralGUI() {
      //initializing
      setTitle("Roman to Arabic Converter");
      setSize(500,300);
      setLocation(400,200);
      setLayout(new GridLayout(1,3));
      createFileMenu();                 //calling the respective Menus to add it to the GUI
      createConvertMenu();
      setJMenuBar(menuBar);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   } //RomanNumeralGUI

   private void createFileMenu() {
      JMenuItem item;
      JMenu fileMenu = new JMenu("File");
      FileMenuHandler fmh = new FileMenuHandler(this); //calling the FileMenuHandler to attach it to the JFrame RomanNumeralGUI

      item = new JMenuItem("Open");      //Open...
      item.addActionListener( fmh );
      fileMenu.add( item );

      fileMenu.addSeparator();           //add a horizontal separator line

      item = new JMenuItem("Quit");      //Quit...
      item.addActionListener( fmh );
      fileMenu.add( item );

      menuBar.add(fileMenu);             //adding the FileMenu to the menuBar
   } //createFileMenu

   private void createConvertMenu() {
      JMenuItem item;
      JMenu convertMenu = new JMenu("Convert");
      ConvertMenuHandler cmh = new ConvertMenuHandler(this); //calling the ConvertMenuHandler to attach it to the JFrame RomanNumeralGUI

      item = new JMenuItem("Roman to Arabic"); //Roman to Arabic...
      item.addActionListener( cmh );
      convertMenu.add( item );

      menuBar.add(convertMenu);          //adding the ConvertMenu to the menuBar
   } //createConvertMenu

} //RomanNumeralGUI
