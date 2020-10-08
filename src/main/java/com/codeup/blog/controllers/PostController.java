package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//Use dependency injection to use an instance of this new Posts interface.
    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    //    @RequestMapping(path = "/post", method = RequestMethod.GET)
    @GetMapping("/post")
//    @ResponseBody
    public String post(Model model) {
        List<Post>postList = postRepo.findAll();
        model.addAttribute("postList",postList);
        return "posts/index";
    }

    @RequestMapping(path = "/post/show/{id}", method = RequestMethod.GET)
    public String postId(@PathVariable long id, Model model) {
//        Post post = postRepo.getOne(id);
        model.addAttribute("post", postRepo.getOne(id));
        return "posts/show";
    }

    @RequestMapping(path = "/post/create", method = RequestMethod.GET)
//    @ResponseBody
    public String postCreateGet( Model model) {
//        List<Post> postList = new ArrayList<>();
//        Post post1 = new Post("First Post", "This is the first post");
//        Post post2 = new Post("Second Post", "This is the second post");
//        post1.setId(1);
//        post2.setId(2);
//        postList.add(post1);
//        postList.add(post2);
//        model.addAttribute("postList",postList);
        return "posts/index" ;
    }
//    @RequestMapping(path = "/post/create", method = RequestMethod.POST)
    @PostMapping("/post/create")
//    @ResponseBody
    public String postCreatePost( ) {
        return "create a new post" ;
    }

    @PostMapping("/post/{id}/delete")
    public String postDeletePost(@PathVariable long id){
        postRepo.delete(postRepo.getOne(id));
        return "redirect:/post";
//        redirect to the url not HTML
    }
    @PostMapping("/post/{id}/edit")
    public String postEditPost(@PathVariable long id,@RequestParam (name="title") String title, @RequestParam (name="body") String body){
        Post post = postRepo.getOne(id);
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/post";

    }
    @GetMapping("/post/{id}/edit")
    public String postEditPostForm(@PathVariable long id, Model model){
        model.addAttribute("post",postRepo.getOne(id));
        return "posts/edit";
    }

}
