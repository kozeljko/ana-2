package si.kozelj.ana;

import si.kozelj.ana.algorithms.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("More args");
        }

        AlgorithmTypes type = AlgorithmTypes.getType(args[0]);

        String fileName = "ss5.txt";
        Double epsilon = 0.5;

        List<Integer> values;
        try {
            values = Files.lines(Paths.get("resources/inputs/".concat(fileName))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can't read shit");
        }

        Integer n = values.remove(0);
        Integer k = values.remove(0);

        SubsetSolver solver = switch (type) {
            case DYN -> new DynSolver();
            case EXH -> new BreadthSolver();
            case GREEDY -> new GreedySolver();
            case FPTAS -> new FPTASSolver(epsilon);
        };

        Instant start = Instant.now();
        int result = solver.getResult(values, k);
        Instant end = Instant.now();

        System.out.println(String.format("n: %s, k: %s", n, k));
        System.out.println("Result is:");
        System.out.println(result);
        System.out.println("Time: " + Duration.between(start, end).toString());
    }
}
