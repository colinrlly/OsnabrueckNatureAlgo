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

    static int weights[];
    static int values[];
    static int limit;
    static int solutionSize;
    static int solutionDef[];

    public static void finalPrint(int[] solution, int value) {
        int localBestWeight = 0;

        for (int i = 0; i < solution.length; i++) {
            localBestWeight += solution[i] * weights[solutionDef[i]];
        }

        System.out.println("------------------");
        System.out.println("final weight " + localBestWeight);
        System.out.println("final value " + value);
        System.out.println("final solution " + Arrays.toString(solution));
    }

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

        return(records);
    }

    public static void main(String[] args) {
        List<List<Integer>> records = parseCSV("cable_12_100.csv");

        System.out.println(records);
        // int solution[] = { 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 };
        // int neighborhood[][] = new int[solutionSize][solutionSize];
        // int localBestValue = 0;
        // int neighborWeight = 0;
        // int neighborValue = 0;
        // int neighborhoodData[][] = new int[solutionSize][2]; // [2] is [weight,
        // value]
        // boolean solutionIsBest = false;

        // for (int i = 0; i < solution.length; i++) {
        // localBestValue += solution[i] * values[solutionDef[i]];
        // }

        // while (!solutionIsBest) {
        // System.out.println("solution was not best");

        // for (int i = 0; i < solution.length; i++) {
        // neighborhood[i] = solution.clone();
        // neighborhood[i][i] = neighborhood[i][i] == 1 ? 0 : 1;

        // neighborWeight = 0;
        // neighborValue = 0;
        // for (int j = 0; j < neighborhood[i].length; j++) {
        // neighborWeight += neighborhood[i][j] * weights[solutionDef[j]];
        // neighborValue += neighborhood[i][j] * values[solutionDef[j]];
        // neighborhoodData[i][0] = neighborWeight;
        // neighborhoodData[i][1] = neighborValue;
        // }
        // }

        // solutionIsBest = true;
        // for (int i = 0; i < solution.length; i++) {
        // if (neighborhoodData[i][0] <= limit && neighborhoodData[i][1] >
        // localBestValue) {
        // solution = neighborhood[i].clone();
        // localBestValue = neighborhoodData[i][1];
        // solutionIsBest = false;
        // System.out.println(Arrays.toString(solution));
        // }
        // }
        // }

        // finalPrint(solution, localBestValue);
    }
}
