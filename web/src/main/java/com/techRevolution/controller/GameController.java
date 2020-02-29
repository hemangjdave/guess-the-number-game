package com.techRevolution.controller;

import com.techRevolution.service.GameService;
import com.techRevolution.util.AttributeNames;
import com.techRevolution.util.GameMapping;
import com.techRevolution.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(GameMapping.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE , gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE , gameService.getResultMessage());
        log.info("Model is :-- {}" , model);

        if(gameService.isGameOver()){
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMapping.PLAY)
    public String processMessage(@RequestParam int guess){
        log.info("guess is:----{}" , guess );
        gameService.checkGuess(guess);
        return GameMapping.REDIRECT_PLAY;
    }

    @GetMapping(GameMapping.RESTART)
    public String restart(){
        gameService.reset();
        return GameMapping.REDIRECT_PLAY;
    }
}
