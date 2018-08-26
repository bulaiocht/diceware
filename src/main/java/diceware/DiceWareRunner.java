package diceware;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class DiceWareRunner {

    private static final String PASS_FILE = "pass";
    private static final String DELIM = "-";
    private static final Integer COMPL = 6;
    private static final String DELIMITERS = "[^a-zA-Z0-9]";

    public static void main(String[] args) {

        Map<String, String> argMap = analyzeArgs(args);
        WordListReader reader = new WordListReader();
        Map<Integer, String> dictionary = reader.readWordList();

        for (int i = 0; i < 10; i++) {
            String password = getPassword(dictionary, argMap);
            System.out.println("Your password is: " + password);
            saveToFile(password);
        }
    }

    private static Map<String, String> analyzeArgs(String[] args) {
        Map<String, String> argMap = new HashMap<>();
        if (args.length != 2){
            argMap.put("delimiter", DELIM);
            argMap.put("complexity", COMPL.toString());
        } else {
            String delimiter;
            if (!args[0].matches(DELIMITERS)){
                delimiter = DELIM;
            } else delimiter = args[0];
            Integer complexity;
            try {
                complexity = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Unknown complexity." +
                        " Falling back to default: " + COMPL);
                complexity = COMPL;
            }
            argMap.put("delimiter", delimiter);
            argMap.put("complexity", complexity.toString());
        }
        return argMap;
    }



    public static String getPassword(Map<Integer, String> dictionary,
                                     Map<String, String> arguments) {
        String delimiter = arguments.get("delimiter");
        int complexity = Integer.parseInt(arguments.get("complexity"));

        StringJoiner joiner = new StringJoiner(delimiter, "{", "}");
        for (int i = 0; i < complexity; i++) {
            int key = DiceThrower.throwDice();
            String value = dictionary.get(key);
            joiner.add(value);
        }

        return joiner.toString();
    }



    public static void saveToFile(String password) {

        Path path = Paths.get(PASS_FILE);
        try {
            //Files.deleteIfExists(path);
            boolean exists = Files.exists(path);

            if (!exists) {
                Files.createFile(path);
            }

            Files.write(path,
                    password.concat("\n").getBytes(),
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

