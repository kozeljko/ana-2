package si.kozelj.ana.algorithms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreadthSolver implements SubsetSolver {

    @Override
    public int getResult(List<Integer> values, Integer k) {
        List<Integer> currentRow = List.of(0);
        for (Integer value : values) {
            currentRow = Stream.concat(currentRow.stream(), currentRow.stream().map(o -> o + value)).distinct().sorted().takeWhile(o -> o <= k).collect(Collectors.toList());
        }

        return currentRow.get(currentRow.size() - 1);
    }
}
