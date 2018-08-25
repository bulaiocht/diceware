package diceware;

import java.util.Random;

public class DiceThrower {

    private static final int ROLLS = 4;

    private static final int SIDES = 6;

    public static int throwDice(){

        Random random = new Random();

        int result = 0;

        for (int i = 0; i < ROLLS; i++) {

            int num = random.nextInt(SIDES) + 1;
            result += num * Math.pow(10, ROLLS - i - 1);

        }
        return result;
    }

}
