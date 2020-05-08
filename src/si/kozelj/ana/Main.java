package si.kozelj.ana;

import si.kozelj.ana.algorithms.BreadthSolver;
import si.kozelj.ana.algorithms.DynSolver;
import si.kozelj.ana.algorithms.SubsetSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("More args");
        }

        AlgorithmTypes type = AlgorithmTypes.getType(args[0]);
        Integer k = Integer.valueOf(args[1]);
        String fileName = args[2].contains(".txt") ? args[2] : args[2].concat(".txt");

        List<Integer> values;
        try {
            values = Files.lines(Paths.get("resources/inputs/".concat(fileName))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can't read shit");
        }

        SubsetSolver solver = switch (type) {
            case DYN -> new DynSolver();
            case EXH -> new BreadthSolver();
            default -> throw new UnsupportedOperationException("Not implemented, boi");
        };

        int result = solver.getResult(values, k);

        System.out.println("Result is:");
        System.out.println(result);
    }
}
