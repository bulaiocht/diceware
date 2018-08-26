package diceware;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordListReader {

    private Map<Integer, String> dictionary = new HashMap<>();

    public Map<Integer, String> readWordList() {

        InputStream inputStream = getClass().getResourceAsStream("/wordlist");

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(inputStream));

            boolean running = true;

            while (running) {

                String line = reader.readLine();

                if (line != null) {

                    String[] split = line.split("\t");

                    int key = Integer.parseInt(split[0]);
                    String value = split[1];

                    dictionary.put(key, value);
                } else running = false;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }
}
