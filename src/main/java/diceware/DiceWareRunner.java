package diceware;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.StringJoiner;

public class DiceWareRunner {

    private static final String PASS_FILE = "pass";

    public static void main(String[] args) {

        WordListReader reader = new WordListReader();

        Map<Integer, String> dictionary = reader.readWordList();

        int complexity = 6;

        for (int i = 0; i < 10; i++) {

            String password = getPassword(dictionary, complexity);
            System.out.println("Your password is: " + password);
            saveToFile(password);

        }
    }

    public static String getPassword(Map<Integer, String> dictionary,
                                     int numOfWords) {

        StringJoiner joiner = new StringJoiner("-", "{", "}");

        for (int i = 0; i < numOfWords; i++) {
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


//    private static void collExamples(){
    //        ArrayList list = new ArrayList();
//
//        list.add(1);
//        list.add(3);
//        list.add(8);
//        list.add(" ");
//        list.add('a');
//
//        Integer integer = 8;
//
//        for (Object o : list) {
//            System.out.println(o);
//        }
//
//        ArrayList<Integer> integers = new ArrayList<>();
//
//        integers.add(3);
//        integers.add(3);
//        integers.add(3);
//        integers.add(3);
//        integers.add(3);
//        integers.add(3);
//
//        System.out.println("size is " + integers.size());
//
//        Map<Integer, String> dictionary = new HashMap<>();
//
//        dictionary.put(6565, "wipe");
//
//        System.out.println("dictionary size: " + dictionary.size());
//
//        String s = dictionary.get(6565);
//
//        System.out.println(s);
//
//        HashMap.SimpleEntry<Integer, String> pair = new AbstractMap.SimpleEntry<>(2 , "Value");
//
//        System.out.println(pair.getKey());
//        System.out.println(pair.getValue());
//
//        Integer integerVal;
//        Double doubleVal;
//        Float floatVal;
//        Long longVal;
//        Boolean boolVal;
//        Short shortVal;
//        Character charVal;
//        Byte byteVal;
//
//        int i = Integer.parseInt("6565");
//
//        integerVal = 6;
//
//        System.out.println(Objects.hashCode(integerVal));
//
//        integerVal = 7;
//
//        System.out.println(Objects.hashCode(integerVal));

//    }
}

