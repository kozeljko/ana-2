package si.kozelj.ana.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FPTASSolver implements SubsetSolver {
    private final Double epsilon;

    public FPTASSolver(Double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public int getResult(List<Integer> values, Integer k) {
        List<Integer> currentRow = List.of(0);
        Double delta = epsilon / (2 * values.size());
        for (Integer value : values) {
            List<Integer> untrimmed = Stream.concat(currentRow.stream(), currentRow.stream().map(o -> o + value)).distinct().sorted().collect(Collectors.toList());
            currentRow = trim(untrimmed, delta);
            currentRow.removeIf(o -> o > k);
        }

        return currentRow.get(currentRow.size() - 1);
    }

    private List<Integer> trim(List<Integer> list, Double delta) {
        if (list.size() <= 1) {
            return list;
        }

        List<Integer> result = new ArrayList<>();
        result.add(list.get(0));

        Integer lastValue = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Integer value = list.get(i);
            if (value > lastValue * (1 + delta)) {
                lastValue = value;
                result.add(lastValue);
            }
        }

        return result;
    }
}
