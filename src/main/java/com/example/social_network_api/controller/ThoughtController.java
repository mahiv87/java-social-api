package com.example.social_network_api.controller;

import com.example.social_network_api.model.Reaction;
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

    @PutMapping("/{thoughtId}")
    public ResponseEntity<Thought> updateThought(@PathVariable String thoughtId, @RequestBody Thought thoughtDetails) {
        return thoughtRepository.findById(thoughtId)
                .map(thought -> {
                    thought.setThoughtText(thoughtDetails.getThoughtText());
                    thought.setUsername(thoughtDetails.getUsername());

                    Thought updatedThought = thoughtRepository.save(thought);
                    return ResponseEntity.ok().body(updatedThought);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No thought with that Id found..."));
    }

    @DeleteMapping("/{thoughtId}")
    public void deleteThought(@PathVariable String thoughtId) {
        thoughtRepository.deleteById(thoughtId);
    }

    @PutMapping("/{thoughtId}/reactions")
    public ResponseEntity<Thought> addReaction(@PathVariable String thoughtId, @RequestBody Reaction reactionDetails) {
        return thoughtRepository.findById(thoughtId)
                .map(thought -> {
                    thought.getReactions().add(reactionDetails);

                    Thought updatedThought = thoughtRepository.save(thought);
                    return ResponseEntity.ok().body(updatedThought);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No thought with that Id found..."));
    }

    @DeleteMapping("/{thoughtId}/reactions")
    public ResponseEntity<Thought> deleteReaction(@PathVariable String thoughtId, @RequestBody String reactionId) {
        return thoughtRepository.findById(thoughtId)
                .map(thought -> {
                    List<Reaction> updatedReactions = thought.getReactions().stream()
                            .filter(reaction -> reaction.getReactionId().equals(reactionId))
                            .toList();

                    thought.setReactions(updatedReactions);

                    Thought updatedThought = thoughtRepository.save(thought);
                    return ResponseEntity.ok().body(updatedThought);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No thought with that Id found..."));
    }

}
