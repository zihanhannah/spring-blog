package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MathController{
    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1,@PathVariable  int number2) {
//        return number1 +" + "+ number2 + " = " + (number1 + number2) + "!";
        return String.format("%d + %d = %d !", number1, number2, number1+number2);
    }
}

//
//class AddController {
//    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
//    @ResponseBody
//    public String add(@PathVariable int number1,@PathVariable  int number2) {
//        return number1 +" + "+ number2 + " = " + (number1 + number2) + "!";
//    }
//}
@Controller
class SubtractController {
    @RequestMapping(path = "/subtract/{number2}/from/{number1}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int number1,@PathVariable  int number2) {
        return number1 +" - "+ number2 + " = " + (number1 - number2) + "!";
    }
}
@Controller
class MultiplyController {
    @RequestMapping(path = "/multiply/{number2}/and/{number1}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int number1,@PathVariable  int number2) {
        return number1 +" * "+ number2 + " = " + (number1 * number2) + "!";
    }
}
@Controller
class DivideController {
    @RequestMapping(path = "/divide/{number1}/by/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int number1,@PathVariable  int number2) {
        return number1 +" / "+ number2 + " = " + (number1 / number2) + "!";
    }
}