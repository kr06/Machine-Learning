import java.io.FileInputStream;
import java.util.*;
/**
 * This program will read the data file into ArrayLists and perform 
 * gradient descent to determine Theta_0 (t0) and Theta_1 (t1). The 
 * algorithm will iterate based on the user specified value, 
 * which is different looping until convergence.
 * 
 * @author (Koushik Pernati) 
 * @version (2.14.2016)
 */
public class GradientDescent
{
    /**
     * This is the main function that calculates the gradient descent.
     */
    public static void main (String args[]) {
        try{
            //Arraylist to store the x and y values
            ArrayList<Double> xValues = new ArrayList<Double>();
            ArrayList<Double> yValues = new ArrayList<Double>();

            //Scanner to read in the name of file and values need
            Scanner in = new Scanner(System.in);
            
            //Reads in and stores the name of the input file
            System.out.print("Input file: ");
            String fileName = in.next();
            
            //Reads in and stores the t0
            System.out.print("t0 guess: ");
            double tZero = Double.parseDouble(in.next());
            
            //Reads in and stores the t1
            System.out.print("t1 guess: ");
            double tOne = Double.parseDouble(in.next());
            
            //Reads in and stores the learning rate (a)
            System.out.print("learning rater (a): ");
            double a = Double.parseDouble(in.next());
            
            //Reads in and stores the number of iterations
            System.out.print("Iterations: ");
            int t = Integer.parseInt(in.next());

            //Call fileinputstream to read in the data of a file
            FileInputStream inputFile = new FileInputStream(fileName);
            Scanner inFile = new Scanner(inputFile);
            int m = 0;
            
            //Stroes the x and y values into the arraylists created
            while(inFile.hasNextDouble()){
                double x = inFile.nextDouble();
                xValues.add(x);
                double y = inFile.nextDouble();
                yValues.add(y);
                m++;
            }

            //Does the actual gradient descent formula
            for (int iter = 0; iter < t; iter++){ 
                double sum0 = 0, sum1 = 0;
                for (int i = 0; i < m; i++){
                    double x = xValues.get(i);
                    double y = yValues.get(i);
                    sum0 = sum0 + ((tZero+tOne*x)-y);
                    sum1 = sum1 + ((tZero+tOne*x)-y)*x;
                }
                tZero = tZero - (a/m)*sum0;
                tOne = tOne - (a/m)*sum1;
            }
            
            //Prints t0 and t1
            System.out.println("t0: " +tZero+ "  t1: " +tOne);
            
            //Closes out the scanners so nothing else is read
            in.close();
            inFile.close();
        }
        catch(Exception e) {
            //Prints any exceptions caught
            System.out.println(e.getMessage());
        }
    }
}