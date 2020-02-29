package com.techRevolution;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Getter
@Slf4j
public class GameImpl implements Game {

    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;
    private int number;

    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean isValidNumberRange = true;

    // setter methods.
    @Setter
    private int guess;
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    // ##### INIT METHODS ####

    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        number = numberGenerator.next();
        biggest = numberGenerator.getMaxNumber();
        remainingGuesses = guessCount;
        log.debug("============= Generated random number is:-- {} =============" ,number);

    }

    @PreDestroy
    public void preDestroy(){
        log.info("In game pre-destroy method...");
    }

    // ##### public methods #####
    @Override
    public void check() {
        checkValidNumberRange();

        if(isValidNumberRange){
            if(guess > number){
                biggest = guess - 1;
            }
            if (guess < number){
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==

    private void checkValidNumberRange(){
        isValidNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
