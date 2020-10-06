package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
//    @RequestMapping(path = "/post", method = RequestMethod.GET)
    @GetMapping("/post")
    @ResponseBody
    public String post() {
        return "This is the posts index page";
    }

    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    public String postId(@PathVariable int id, Model model) {
        Post post = new Post("First Post", "This is the first post");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/post/create", method = RequestMethod.GET)
//    @ResponseBody
    public String postCreateGet( Model model) {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post("First Post", "This is the first post");
        Post post2 = new Post("Second Post", "This is the second post");
        postList.add(post1);
        postList.add(post2);
        model.addAttribute("postList",postList);
        return "posts/index" ;
    }
//    @RequestMapping(path = "/post/create", method = RequestMethod.POST)
    @PostMapping("/post/create")
    @ResponseBody
    public String postCreatePost( ) {
        return "create a new post" ;
    }

}
