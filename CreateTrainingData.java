import java.util.*;
import java.io.*;
/**
 * This will create a file that will contain lines of data where 
 * x ranges from 0 to m-1 and y = t0 + x*t1 + rand.nextDouble()*r-r/2. 
 * y is a linear function of x with a random value added to it.
 * 
 * @author (Koushik Pernati) 
 * @version (2.14.2016)
 */
public class CreateTrainingData
{
    /**
     * This is the main function that creates the training data.
     */
    public static void main (String[] args){
        // Create a Scanner to take input values
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Gets filename so the training data can be created and stored here
        System.out.print("Filename: ");
        String fileName = in.next(); 

        // Gets the number of samples (m)
        System.out.print("Number of examples (m): ");
        double m = Double.parseDouble(in.next());

        // Gets the starting theta
        System.out.print("Theta_0 (t0): ");
        double tZero = Double.parseDouble(in.next());

        // Gets the second theta
        System.out.print("Theta_1 (t1): ");
        double tOne = Double.parseDouble(in.next());

        // Gets the randomization factor (r)
        System.out.print("Randomization factor (r): ");
        double r = Double.parseDouble(in.next());

        try{
            // Creates the PrintWriter to start writing the training data to the file
            PrintWriter out = new PrintWriter(fileName,"UTF-8");

            // Writes the training data out to the file using the formula 
            // y = t0 + x*t1 + rand.nextDouble()*r-r/2
            //forloop to get x ranging from 0 to m-1
            for(int x = 0; x < m; x++){
                // y is the linear function of i with a random value added to it
                double y = tZero + (x*tOne) + (rand.nextDouble()*r-r/2);
                out.println(x+ "\t" +y);
            }

            //Close off the scanner and printwriter
            in.close();
            out.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
