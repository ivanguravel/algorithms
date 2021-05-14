package org.ivzh.binary.search;


import java.util.concurrent.ThreadLocalRandom;

public class GuessNumberGame {


    private int guessMe;

    public GuessNumberGame() {
        guessMe = ThreadLocalRandom.current().nextInt(0, 20_000);
    }

    public static void main(String[] args) {
        GuessNumberGame guessNumberGame = new GuessNumberGame();
        guessNumberGame.guessAction();
    }

    public void guessAction() {
        int low = 0;
        int hight = 20_000;
        while (low < hight) {
            int middle = (hight + low) / 2;
            int guessAttempt = guess(middle);
            if (guessAttempt == 0) {
                return;
            } else if (guessAttempt > 0) {
                low = middle + 1;
            } else {
                hight = middle;
            }
        }
    }

    public int guess(int val) {
        int compare = Integer.compare(guessMe, val);
        if (compare == 0) {
            System.out.printf("Guessed! = %d\n", val);
        } else if (compare < 0) {
            System.out.println("Less!");
        } else {
            System.out.println("More!");
        }
        return compare;
    }

}
