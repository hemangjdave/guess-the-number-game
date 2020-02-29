package com.techRevolution.console;

import com.techRevolution.Game;
import com.techRevolution.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Slf4j
public class ConsoleNumberGuess{//} implements ApplicationListener<ApplicationEvent> {

    private final Game game;

    private final MessageGenerator messageGenerator;

    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //    This method should be  @Override
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//
//    }

    //this is alternate way to listen to an event.
    @EventListener(ApplicationEvent.class)
    public void start() {
        log.info("application is ready to use.");

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();

            scanner.nextLine();

            game.setGuess(guess);
            game.check();

            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play Again y/n ?");

                String playagain = scanner.nextLine().trim();
                if("n".equalsIgnoreCase(playagain)){
                    break;
                }
                game.reset();
            }


        }

        log.info("Exiting start() event method.");
    }


}
