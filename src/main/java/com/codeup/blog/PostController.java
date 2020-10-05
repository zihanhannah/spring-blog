package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
//    @RequestMapping(path = "/post", method = RequestMethod.GET)
    @GetMapping("/post")
    @ResponseBody
    public String post() {
        return "This is the posts index page";
    }
    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postId(@PathVariable int id) {
        return "view an individual post under id " + id;
    }
    @RequestMapping(path = "/post/create", method = RequestMethod.GET)
    @ResponseBody
    public String postCreateGet( ) {
        return "view the form for creating a post" ;
    }
//    @RequestMapping(path = "/post/create", method = RequestMethod.POST)
    @PostMapping("/post/create")
    @ResponseBody
    public String postCreatePost( ) {
        return "create a new post" ;
    }

}
