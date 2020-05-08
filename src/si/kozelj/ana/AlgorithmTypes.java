package si.kozelj.ana;

public enum AlgorithmTypes {
    DYN, EXH, GREEDY, FPTAS;

    public static AlgorithmTypes getType(String typeString) {
        AlgorithmTypes[] values = values();
        for (AlgorithmTypes type : values) {
            if (type.name().equalsIgnoreCase(typeString)) {
                return type;
            }
        }

        throw new RuntimeException("The fuck you send.");
    }
}
