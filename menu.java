package elimgaussmiov4;
import java.util.Scanner;
 
public class menu {
public Scanner sc1 = new Scanner(System.in);
String[] voices;
 
 
public menu(String[] input) {
    voices=input;
    }
 
 
 
public void display() {
    for(int i=0; i<voices.length;i++) {
    System.out.println(i+"-"+voices[i]);
     }
}
public int getchoice() {
    int choice = 0;
    while(true) {
               System.out.println("insert your choice");
               choice = sc1.nextInt();
           if (choice < voices.length && choice >= 0) {
           return choice;
            }
         }
}
public String getchoicestring() {
    int choice = 0;
    while(true) {
           System.out.println("insert your choice");
           choice = sc1.nextInt();
           if (choice < voices.length && choice >= 0) {
           return voices[choice];
           }
         }
}
 
}
 