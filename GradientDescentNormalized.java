import java.io.FileInputStream;
import java.util.*;
/**
 * This program will read the data file into ArrayLists and perform normalized
 * gradient descent to determine the thetas. The 
 * algorithm will iterate until convergence.
 * 
 * @author (Koushik Pernati and Jordan Cartwright) 
 * @version (2.14.2016)
 */
public class GradientDescentNormalized
{
    /**
     * This is the main function that calculates the gradient descent.
     */
    public static void main (String args[]) {
        try{
            //Arraylist to store the x and y values
            ArrayList<ArrayList<Double>> xValues = new ArrayList<ArrayList<Double>>();
            ArrayList<Double> yValues = new ArrayList<Double>();

            //Scanner to read in the name of file and values need
            Scanner in = new Scanner(System.in);

            //Reads in and stores the name of the input file
            System.out.print("Input file: ");
            String fileName = in.next();

            System.out.print("Starting: alpha = ");
            double a = Double.parseDouble(in.next());
            System.out.println();

            //Call fileinputstream to read in the data of a file
            FileInputStream inputFile = new FileInputStream(fileName);
            Scanner inFile = new Scanner(inputFile);

            int m = 0;
            int n = m;
            //Iteration set to 5000 but can be any value
            int t = 5000;

            double prevError = 0.0;
            double curError = 0.0;            

            boolean restart = true;

            System.out.println("Initial Error: " + prevError);

            //Stroes the x and y values into the arraylists created
            while(inFile.hasNext()){
                m++;
                String line = inFile.nextLine();
                StringTokenizer ex = new StringTokenizer(line, ",");
                n = ex.countTokens();
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(1.0);
                for(int i = 0; i < n-1; i++) {
                    temp.add(Double.parseDouble(ex.nextToken()));
                }
                yValues.add(Double.parseDouble(ex.nextToken()));
                xValues.add(temp);
            }

            //Feature Scaling
            for(int j = 1; j < n; j++){
                double maxX = -(Integer.MAX_VALUE);
                double minX = (Integer.MAX_VALUE);
                double xSum = 0;
                double range = 0;
                double mean = 0;
                for(int i = 0; i < m; i++){
                    double x = xValues.get(i).get(j);
                    if(x>maxX) maxX = x;
                    if(x<minX) minX = x;
                    range = maxX-minX;
                    xSum += x;
                }
                mean = xSum/m;

                //Sets the normalized values into arraylist
                for(int i = 0; i < m; i++){
                    double x = xValues.get(i).get(j);
                    x = (x-mean)/range;
                    xValues.get(i).set(j, x);
                }
            }

            ArrayList<Double> thetas  = new ArrayList<Double>();

            int iter = 0;
            int finalIter = 0;


            //Loop until the difference between the errors is very small
            while(restart){
                restart = false;
                prevError = Double.MAX_VALUE;
                
                //Create new set of thetas
                thetas = new ArrayList<Double>();
                for(int i = 0; i < n; i++){
                    thetas.add(0.0);
                }

                //Does the actual gradient descent formula
                for (iter = 0; iter < t; iter++){ 
                    ArrayList<Double> sums = new ArrayList<Double>();
                    double error = 0.0;
                    for(int j = 0; j < n; j++){
                        sums.add(0.0);
                    }

                    //Loop over training examples
                    for (int i = 0; i < m; i++){
                        double y = yValues.get(i);

                        //Loop over all n dimensions
                        for(int j = 0; j < n; j++){
                            //Calculate hypothesis
                            double h = 0;
                            for(int k = 0; k < n; k++){
                                h = h + thetas.get(k) * xValues.get(i).get(k);
                            }
                            //                          SLOPE
                            double sum = (h-y) * xValues.get(i).get(j);
                            sums.set(j, sums.get(j) + sum);
                        }

                        double h = 0;
                        for(int k = 0; k < n; k++){
                            h = h + thetas.get(k) * xValues.get(i).get(k);
                        }
                        error = error + Math.pow(h-y, 2);
                    }
                    
                    //Gets the new error
                    curError = error/(m*2);

                    //Updates thetas for each iteration
                    for(int j = 0; j < n; j++){
                        thetas.set(j, thetas.get(j) - (a/m)*sums.get(j));
                    }

                    //Conditions for if the algorithm should restart
                    if(curError == Double.NaN || curError > prevError){
                        restart = true;
                        a = a/1.2;
                        break;
                    }
                    
                    //Condition to no need to restart
                    if(((prevError - curError) < 0.001)){
                        finalIter = iter;
                        restart = false;
                        break;
                    }
                    
                    prevError = curError;
                }

                System.out.println("Starting: alpha = " + a);;
                System.out.println("Current Error: " + curError);
                System.out.println();
            }
            System.out.println("Ending iteration: " + finalIter);

            //Prints all final thetas and results
            for(int i = 0; i < thetas.size(); i++){
                System.out.print("t" +i+ ": " +thetas.get(i)+ "\t");
            }
            System.out.println();
            System.out.println("Final aplha: " + a);
            System.out.println("Final Error: " + curError);
            System.out.println();

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