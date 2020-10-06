package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceNum(@PathVariable String n, Model model){
        model.addAttribute("n",n);
        int number = (int) (Math.random() * (6) + 1);
        String num = String.valueOf(number);
        model.addAttribute("num",num);
        return "roll-dice";
    }


}
