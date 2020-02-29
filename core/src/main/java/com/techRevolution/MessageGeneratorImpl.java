package com.techRevolution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class MessageGeneratorImpl implements MessageGenerator {

    private final Game game;

    public MessageGeneratorImpl(@Autowired Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init(){
        log.info("initialized MessageGenerator instance.");
    }

    @PreDestroy
    public void destroy(){
        log.info("destroying MessageGenerator instance.");
    }

    @Override
    public String getMainMessage() {
        return "Number is between" + game.getSmallest()
                + " and " + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return "You guessed it. The number was :--" +game.getNumber();
        }
        else if(game.isGameLost()){
            return "You lost it.The number was:--" + game.getNumber();
        }
        else if(!game.isValidNumberRange()){
            return "The number is out of range.";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        }
        else{
            String direction = "Lower";

            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }

            return direction+"! you have " + game.getRemainingGuesses() + " guesses left.";

        }

    }
}
