package com.techRevolution;

public interface Game {

    int getNumber();

    int getGuess();

    int getBiggest();

    int getSmallest();

    void setGuess(int guess);

    int getRemainingGuesses();

    int getGuessCount();

    void reset();

    void check();

    boolean isValidNumberRange();

    boolean isGameWon();

    boolean isGameLost();

}
