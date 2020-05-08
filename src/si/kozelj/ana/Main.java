package si.kozelj.ana;

import si.kozelj.ana.algorithms.SubsetSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("More args");
        }

        AlgorithmTypes type = AlgorithmTypes.getType(args[0]);
        String fileName = args[1].contains(".txt") ? args[1] : args[1].concat(".txt");

        List<Integer> values;
        try {
            values = Files.lines(Paths.get("resources/inputs/".concat(fileName))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can't read shit");
        }

        SubsetSolver solver = switch (type) {
            case DYN -> null;
            default -> throw new UnsupportedOperationException("Not implemented, boi");
        };

        System.out.println("Result is:");
        System.out.println(solver.getResult(values));
    }
}
