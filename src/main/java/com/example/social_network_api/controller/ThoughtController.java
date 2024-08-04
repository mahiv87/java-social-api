package com.example.social_network_api.controller;

import com.example.social_network_api.model.Thought;
import com.example.social_network_api.repository.ReactionRepository;
import com.example.social_network_api.repository.ThoughtRepository;
import com.example.social_network_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/thoughts")
public class ThoughtController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThoughtRepository thoughtRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @GetMapping
    public List<Thought> getThoughts() {
        return thoughtRepository.findAll();
    }

    @GetMapping("/{thoughtId}")
    public ResponseEntity<Thought> getSingleThought(@PathVariable String thoughtId) {
        return thoughtRepository.findById(thoughtId)
                .map(thought -> ResponseEntity.ok().body(thought))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No thought with that Id found..."));
    }

    @PostMapping
    public Thought createThought(@RequestBody Thought thought) {
        return thoughtRepository.save(thought);
    }

}
