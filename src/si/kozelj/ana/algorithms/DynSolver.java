package si.kozelj.ana.algorithms;

import java.util.Arrays;
import java.util.List;

public class DynSolver implements SubsetSolver{

    @Override
    public int getResult(List<Integer> values, Integer k) {
        int valuesCount = values.size();

        int[][] table = new int[valuesCount + 1][];
        for (int i = 0; i < table.length; i++) {
            table[i] = new int[k + 1];
            table[i][0] = 0;
        }

        Arrays.fill(table[0], 0);

        for (int i = 1; i < table.length; i++) {
            int[] row = table[i];

            for (int j = 1; j < row.length; j++) {
                int firstValue = table[i - 1][j];

                int value = values.get(i - 1);
                if (j - value >= 0) {
                    int secondValue = table[i - 1][j - value] + value;
                    table[i][j] = Math.max(firstValue, secondValue);
                } else {
                    table[i][j] = firstValue;
                }
            }
        }

        //printTable(table);

        return table[valuesCount][k];
    }

    private void printTable(int[][] table) {
        StringBuilder output = new StringBuilder();
        for (int[] intArray : table) {
            output.append(Arrays.toString(intArray));
            output.append('\n');
        }

        System.out.println(output);
    }
}
