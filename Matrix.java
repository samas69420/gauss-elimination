package elimgaussmiov4;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
 
class Matrix {
	
	{
		//System.out.println("initializiation block");
	}
   
    //VARIABLES
    static boolean debug=false;
    int s=1; //determinant sign, it changes after a row swap
    double[][] matrix; //input matrix
    String[][] stringmatrix; //input string matrix for input from file
    double[][] reducedmatrix; //matrix that will be used by all methods after being reduced with gauss
    Scanner sc = new Scanner(System.in);
    int diagonal;
    double determinant;
 
    //CONSTRUCTORS
    public Matrix() {//CONSOLLE CONSTRUCTOR
        System.out.println("insert number of rows");
        int a=sc.nextInt();
        System.out.println("insert number of columns");
        int b=sc.nextInt();
        matrix= new double[a][b];
        reducedmatrix= new double[a][b];
            int i=0,j=0;
            for (i=0; i<matrix.length; i++) {
                for (j=0; j<matrix[0].length; j++) {
                  System.out.println("insert element ["+i+"]"+"["+j+"]");
                 matrix[i][j]=sc.nextInt();
               }
            }
            for (i=0; i<reducedmatrix.length; i++) {      
                for (j=0; j<reducedmatrix[0].length; j++) {
                 reducedmatrix[i][j]=matrix[i][j];
               }
            }
            diagonal=Math.min(reducedmatrix[0].length,reducedmatrix.length);
        }
    public Matrix(String path){//FILE CONSTRUCTOR
                //get matrix dimensions
                int[] V = new int[2];
                int rows=1;
                int columns=0;
                try {
                Scanner srow= new Scanner(new File(path));
                String Y =srow.nextLine();
                Scanner scol = new Scanner (Y);
                while (srow.hasNextLine()){
                    srow.nextLine();
                    rows+=1;
                    V[1]=rows;
                    }
                while(scol.hasNextInt()) {
                    scol.nextInt();
                    columns+=1;
                    V[0]=columns;
                    }
                scol.close();
                int c=V[0];
                int r=V[1];
                srow.close();
                scol.close();
                if(debug){System.out.println('\n' + "columns= "+V[0]);System.out.println("rows= "+V[1]+'\n');}//print values for debug
                //fill a list with every element in the matrix
                List<String> list = new ArrayList<String>();
                    Scanner sc1= new Scanner(new File(path));
                    while (sc1.hasNext()){
                    list.add(sc1.next());
                     }
                if(debug){System.out.println("filled list: ");int n=list.size();for(int i=0;i<n;i++){System.out.print(list.get(i));}System.out.println("\n");}//print list for debug
                //create matrix from list
                stringmatrix=new String[r][c];
                int i=0;
                for(int j=0;j<r;j++) {
                    for(int k=0;k<c;k++) {
                     stringmatrix[j][k]=list.get(i);
                     i+=1;
                    }
                }
                //convert matrix from string to double
                matrix=new double[r][c];
                for(int t=0;t<r;t++) {
                      for(int j=0;j<c;j++) {
                        matrix[t][j]=Double.parseDouble(stringmatrix[t][j]);
                    }
                }
                //set reduced matrix = input matrix
                reducedmatrix=new double[r][c];
                for(int a=0;a<r;a++) {
                      for(int b=0;b<c;b++) {
                        reducedmatrix[a][b]=matrix[a][b];
                    }
                }
    diagonal=Math.min(reducedmatrix[0].length,reducedmatrix.length);
    sc1.close();
    if(debug){System.out.println("(debug) reduced matrix after constructor:");for(int a=0;a<r;a++){for(int b=0;b<c;b++){System.out.print(reducedmatrix[a][b]+" ");}System.out.println("");}System.out.println("");}//print reducedmatrix for debug
    }
    catch(FileNotFoundException e){
        System.out.println("error: file not found");
    }              
}
   
    //METHODS
    public void printoriginal() {
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++)  {
                  System.out.print(" " +  matrix[i][j]);
              }
            System.out.println("");  
        }    
    }
    public void print() {
        for (int i=0; i<reducedmatrix.length; i++) {
            for (int j=0; j<reducedmatrix[0].length; j++)  {
                  System.out.print(" " +  reducedmatrix[i][j]);
              }
            System.out.println("");  
        }    
    }
    public int rank() {
        int r=0;
        for(int i=0; i<reducedmatrix.length; i++) {
            for(int j=0; i<reducedmatrix[0].length;j++) {
                if(matrix[i][j]!=0)
                {
                    r++;
                    break;
                }
            }
        }
        return r;
     }
    public void findDeterminant() {
            if(reducedmatrix.length==reducedmatrix[0].length) {//check if it is a squared matrix
           double r=1;
           int i=0;
           for(i=0;i<diagonal;i++) {
               r*=reducedmatrix[i][i];
               }
           determinant= s*r;
           System.out.println("determinant : "+determinant+"\n\n");
            }
       else if(reducedmatrix.length!=reducedmatrix[0].length) {
              System.out.println("\n ERROR: determinant exists only for squared matrices \n");
        }
    }
    public void gauss() {
        int k=0,i=0;
       
       
        //if first element is not 0---------------------------------------------------
        if(reducedmatrix[0][0]!=0) {//compiler stops here while using file constructor
             for(k=0;k<diagonal;k++){
                 for(i=k+1;i<reducedmatrix.length;i++){
                     double x=reducedmatrix[i][k]/reducedmatrix[k][k];
                     for(int a=0; a<reducedmatrix[0].length; a++) {
                     reducedmatrix[i][a]-=x*reducedmatrix[k][a];
                     }  
                 }
             }
        }
       
       
        //if first element is 0-------------------------------------------------------
        else if (reducedmatrix[0][0]==0){
            if(debug==true)System.out.println("element [0][0] was 0");
            int index=0;
           
            //SEARCH
            //find a row such that its first element is not 0
            for(int a=0;a<reducedmatrix.length;a++) {
                if(reducedmatrix[a][0]!=0) {
                index=a;        
                break;
                }
                if(a==reducedmatrix.length-1) System.out.println("error: the whole first column is 0");
            }
           
            //SWAP
            double[] temp= new double[reducedmatrix[0].length];
            for(int a=0;a<reducedmatrix[0].length;a++) {
                temp[a]=reducedmatrix[0][a];      
            }
            for(int a=0;a<reducedmatrix[0].length;a++) {
                reducedmatrix[0][a]=reducedmatrix[index][a];
            }
            for(int a=0;a<reducedmatrix[0].length;a++) {
                reducedmatrix[index][a]=temp[a];  
            }
           
            if(debug==true){
            System.out.println("after swap matrix is: ");
            for (int a=0; a<reducedmatrix.length; a++) {
                  for (int b=0; b<reducedmatrix[0].length; b++) {
                      System.out.print(" " +  reducedmatrix[a][b]);
                  }
                System.out.println("");  
            }
            }
           
            //REDUCTION
            for(k=0;k<diagonal;k++){
                 for(i=k+1;i<reducedmatrix.length;i++){
                     double x=reducedmatrix[i][k]/reducedmatrix[k][k];
                     for(int a=0; a<reducedmatrix[0].length; a++) {
                     reducedmatrix[i][a]-=x*reducedmatrix[k][a];
                     }  
                 }
             }
        s=-1; //determinant sign changes
        }
        if(debug){System.out.println("(debug) reduced matrix after gauss:");for(int a=0;a<reducedmatrix.length;a++){for(int b=0;b<reducedmatrix[0].length;b++){System.out.print(reducedmatrix[a][b]+" ");}System.out.println("");}System.out.println("");}//print reducedmatrix for debug  
    }
}