package com.tts.TechTalentsSouth.TechTalentTwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import javax.validation.Valid;

import com.tts.TechTalentsSouth.TechTalentTwitter.model.Tweet;
import com.tts.TechTalentsSouth.TechTalentTwitter.model.User;
import com.tts.TechTalentsSouth.TechTalentTwitter.service.TweetService;
import com.tts.TechTalentsSouth.TechTalentTwitter.service.UserService;



@Controller
public class TweetController {
	  @Autowired
	    private UserService userService;
		
	    @Autowired
	    private TweetService tweetService;
	
@GetMapping(value= {"/tweets", "/"})
public String getFeed(Model model){
    List tweets = (List) tweetService.findAll();
    model.addAttribute("tweetList", tweets);
    return "feed";
}
@GetMapping(value = "/tweets/new")
public String getTweetForm(Model model) {
    model.addAttribute("tweet", new Tweet());
    return "newTweet";
}
@PostMapping(value = "/tweets")
public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model) {
    User user = userService.getLoggedInUser();
    if (!bindingResult.hasErrors()) {
        tweet.setUser(user);
        tweetService.save(tweet);
        model.addAttribute("successMessage", "Tweet successfully created!");
        model.addAttribute("tweet", new Tweet());
    }
    return "newTweet";
}}