package elimgaussmiov4;
 
import java.util.Scanner;
 
public class Main {
    static menu i;
    static menu j;
   
   
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    String[] Menu= {"print original","print reduced","rank","determinant","new reduction","exit"};
    String[] Menu2= {"input from console", "input from file"};
    i= new menu(Menu);
    j= new menu(Menu2);
    int s=0;
    int s2=0;
    String path;
    while(Menu[s]!="exit"){
         s=0;
         s2=0;
         j.display();
         s2=j.getchoice();
         Matrix m;
         switch(s2) {
                  case 0: m=new Matrix();
                          m.gauss();
                          while(Menu[s]!="new reduction"&&Menu[s]!="exit") {
                             i.display();
                             s=i.getchoice();
                          switch(s) {
                                   case 0:m.printoriginal();System.out.println("\n\n");break;
                                   case 1:m.print();System.out.println("\n\n");break;
                                   case 2:System.out.println("rank is: "+m.rank()+"\n\n");break;  
                                   case 3:m.findDeterminant();break;                  
                                      }
                         
                         }break;
                 
                 
                 
                  case 1: System.out.println("instert matrix txt file");
                          path=sc.next();
                          m=new Matrix(path);
                          m.gauss();
                          while(Menu[s]!="new reduction"&&Menu[s]!="exit") {
                             i.display();
                             s=i.getchoice();
                          switch(s) {
                                  case 0:m.printoriginal();System.out.println("\n\n");break;
                                  case 1:m.print();System.out.println("\n\n");break;
                                  case 2:System.out.println("rank is: "+m.rank()+"\n\n");break;  
                                  case 3:m.findDeterminant();break;                    
                              }
                         
                          }break;
         
        }
                         
         
    }
  sc.close();
  }
}
