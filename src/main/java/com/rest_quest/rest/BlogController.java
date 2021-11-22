package com.rest_quest.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

    @RestController
    public class BlogController {


        @Autowired
        BlogRepository blogRepository;

        @GetMapping("/blog")
        public List<Blog> index(){
            return blogRepository.findAll();
        }

        @GetMapping("/blog/{id}")
        public Blog show(@PathVariable String id){
            int blogId = Integer.parseInt(id);
            Optional<Blog> b = blogRepository.findById(blogId);
            Blog blog = b.get();
            return blog;
        }

        @PostMapping("/blog/search")
        public List<Blog> search(@RequestBody Map<String, String> body){
            String searchTerm = body.get("text");
            return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        }

        @PostMapping("/blog")
        public Blog create(@RequestBody Blog blog){
            return blogRepository.save(blog);
        }

        @PutMapping("/blog/{id}")
        public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
            int blogId = Integer.parseInt(id);
            // getting blog
            Optional<Blog> b = blogRepository.findById(blogId);
            Blog blog = b.get();
            blog.setTitle(body.get("title"));
            blog.setContent(body.get("content"));
            return blogRepository.save(blog);
        }

        @RequestMapping(value = "/blog/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
        @ResponseBody
        public boolean delete(@PathVariable String id){
            int blogId = Integer.parseInt(id);
            blogRepository.deleteById(blogId);
            return true;
        }

    }
