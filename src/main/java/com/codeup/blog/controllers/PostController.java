package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final UserRepository userRepo;
//Use dependency injection to use an instance of this new Posts interface.
    private final PostRepository postRepo;
    private final EmailService emailService;

    public PostController(UserRepository userRepo, PostRepository postRepo, EmailService emailService) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.emailService = emailService;
    }

    //    @RequestMapping(path = "/post", method = RequestMethod.GET)
    @GetMapping("/posts")
//    @ResponseBody
    public String post(Model model) {
        List<Post>postList = postRepo.findAll();
        model.addAttribute("postList",postList);
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postId(@PathVariable long id, Model model) {
       Post post = postRepo.getOne(id);
//       model.addAttribute("post", postRepo.getOne(id));
        if (post.getUser() == null) {
            List <User> users = userRepo.findAll();
            post.setUser(users.get(0));
        }
        model.addAttribute("post", post);
        return "posts/show";
    }
//
//    @RequestMapping(path = "/post/create", method = RequestMethod.GET)
////    @ResponseBody
//    public String postCreateGet( Model model) {
////        List<Post> postList = new ArrayList<>();
////        Post post1 = new Post("First Post", "This is the first post");
////        Post post2 = new Post("Second Post", "This is the second post");
////        post1.setId(1);id
////        post2.setId(2);
////        postList.add(post1);
////        postList.add(post2);
////        model.addAttribute("postList",postList);
//        model.addAttribute("post", new Post());
//        return "posts/index" ;
//    }

    @GetMapping("/posts/create")
    public String showCreatePost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
//    @ResponseBody
    public String postCreatePost(@ModelAttribute Post post ) {
        if(post.getId()==0){
            post.setUser(userRepo.findAll().get(0));
            emailService.prepareAndSend(post,"Created Post: " + post.getTitle(),
                    post.getTitle() + "\n\n" + post.getBody());
       }

            // Send email for an edit
        else {
                post.setUser(postRepo.getOne(post.getId()).getUser()); // Get the user from the database
                emailService.prepareAndSend(post,
                        "Edited Post: " + post.getTitle(),
                        post.getTitle() + "\n\n" + post.getBody());
            }
            postRepo.save(post);
            return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/posts/{id}/delete")
    public String postDeletePost(@PathVariable long id){
        Post post = postRepo.getPostById(id);
        post.setUser(postRepo.getPostById(post.getId()).getUser()); // Get the user from the database

        // send email for a post delete
        emailService.prepareAndSend(post,
                "Created Post: " + post.getTitle(),
                post.getTitle() + "\n\n" + post.getBody());
        postRepo.delete(post);
        return "redirect:/posts";
    }
//    @PostMapping("/post/{id}/edit")
//    public String postEditPost(@PathVariable long id,@RequestParam (name="title") String title, @RequestParam (name="body") String body){
//        Post post = postRepo.getOne(id);
//        post.setTitle(title);
//        post.setBody(body);
//        postRepo.save(post);
//        return "redirect:/posts";
//
//    }
//    @GetMapping("/post/{id}/edit")
//    public String postEditPostForm(@PathVariable long id, Model model){
//        model.addAttribute("post",postRepo.getOne(id));
//        return "posts/edit";
//    }
    @GetMapping("/posts/{id}/edit")
    public String editAd(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "posts/create";
    }
}
