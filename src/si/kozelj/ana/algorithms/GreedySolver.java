package si.kozelj.ana.algorithms;

import java.util.Comparator;
import java.util.List;

public class GreedySolver implements SubsetSolver{

    @Override
    public int getResult(List<Integer> values, Integer k) {
        values.sort(Comparator.reverseOrder());

        int sum = 0;
        for (Integer value : values) {
            if (value <= k - sum) {
                sum += value;
            }
        }

        return sum;
    }
}
