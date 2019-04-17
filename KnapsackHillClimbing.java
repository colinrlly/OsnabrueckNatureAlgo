import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class KnapsackHillClimbing {

    // static int weights[] = { 15, 25, 40 };
    // static int values[] = { 30, 40, 60 };
    // static int limit = 100;
    // static int solutionSize = 13;
    // static int solutionDef[] = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2 };

    final static String dataPath = "data/cable_12_100.csv";
    final static int limit = 100;
    static int solutionSize = 12;

    // static int weights[];
    // static int values[];
    static List<List<Integer>> data;
    static int solutionDef[];

    /***
     * Prints the final values at the end of running this program.
     * 
     * @param solution - Array representing the items in the Knapsack.
     * @param value    - Value of the items in the Knapsack.
     */
    public static void finalPrint(int[] solution, int value, List<List<Integer>> data) {
        int localBestWeight = 0;

        for (int i = 0; i < solution.length; i++) {
            localBestWeight += solution[i] * data.get(i).get(1);
        }

        System.out.println("------------------");
        System.out.println("final data " + data);
        System.out.println("final weight " + localBestWeight);
        System.out.println("final value " + value);
        System.out.println("final solution " + Arrays.toString(solution));
    }

    /***
     * Returns a 2D array representing the data held in a csv file.
     * 
     * Column 1: Value; Column 2: Weight.
     * 
     * @param path - Path of the csv file to load data from.
     * @return 2D arrayList representing the data held in the csv file.
     */
    public static List<List<Integer>> parseCSV(String path) {
        List<List<Integer>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] sValues = line.split(",");

                Integer i1 = Integer.parseInt(sValues[0]);
                Integer i2 = Integer.parseInt(sValues[1]);
                Integer[] newRecord = { i1, i2 };

                records.add(Arrays.asList(newRecord));
            }
        } catch (IOException e) {
            System.out.println("IOException thrown when trying to parse csv" + e);
        }

        return (records);
    }

    public static void printNeighborhood(int[][] neighborhood, int[][] neighborhoodData) {
        System.out.println("neighborhood");

        for (int i = 0; i < neighborhood[0].length; i++) {
            System.out.println("n " + Arrays.toString(neighborhood[i]));
            System.out.println("d " + Arrays.toString(neighborhoodData[i]));
        }
    }

    public static void main(String[] args) {
        data = parseCSV(dataPath);

        // Initialize variables
        int solution[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int neighborhood[][] = new int[solutionSize][solutionSize];
        int localBestValue = 0;
        int neighborWeight = 0;
        int neighborValue = 0;
        int neighborhoodData[][] = new int[solutionSize][2]; // [2] is [value, weight]
        boolean solutionIsBest = false;

        // Get the initial "localBestValue" - the value of the initial solution
        for (int i = 0; i < solution.length; i++) {
            localBestValue += solution[i] * data.get(i).get(0);
        }

        while (!solutionIsBest) {
            System.out.println(Arrays.toString(solution) + " solution was not best");

            // Loop through all neighbors.
            for (int i = 0; i < solution.length; i++) {
                // Flip 1 bit for every solution in the neighborhood.
                neighborhood[i] = solution.clone();
                neighborhood[i][i] = neighborhood[i][i] == 1 ? 0 : 1;

                // Get the value and weight of all the neighbors.
                neighborWeight = 0;
                neighborValue = 0;
                for (int j = 0; j < neighborhood[i].length; j++) {
                    neighborWeight += neighborhood[i][j] * data.get(j).get(1);
                    neighborValue += neighborhood[i][j] * data.get(j).get(0);
                }
                neighborhoodData[i][0] = neighborValue;
                neighborhoodData[i][1] = neighborWeight;
            }

            printNeighborhood(neighborhood, neighborhoodData);

            // See if any of the neighbors are better than the current solution.
            solutionIsBest = true;
            for (int i = 0; i < solution.length; i++) {
                if (neighborhoodData[i][1] <= limit && neighborhoodData[i][0] > localBestValue) {
                    solution = neighborhood[i].clone();
                    localBestValue = neighborhoodData[i][0];
                    solutionIsBest = false;
                }
            }
        }

        finalPrint(solution, localBestValue, data);
    }
}
