package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class PostController {
    @RequestMapping(path = "/post", method = RequestMethod.GET)
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
    @RequestMapping(path = "/post/create", method = RequestMethod.POST)
    @ResponseBody
    public String postCreatePost( ) {
        return "create a new post" ;
    }

}
