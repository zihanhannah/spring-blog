package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String rollDiceForm(@RequestParam(name = "number") int number, Model model){
        int numberRolled = (int) (Math.random() * 6 + 1);
        String message = "you selected " + number + " and the number rolled was " + numberRolled + ".";
        if( number == numberRolled){
            message += " You Won!";
        } else{
            message += " Well, you lose!";
        }
        model.addAttribute("message", message);
        return "roll-dice";
    }


//    @GetMapping("/roll-dice/{n}")
//    public String rollDiceNum(@PathVariable String n, Model model){
//        model.addAttribute("n",n);
//        int number = (int) (Math.random() * (6) + 1);
//        String num = String.valueOf(number);
//        model.addAttribute("num",num);
//        return "roll-dice";
//    }


}
