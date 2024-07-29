package com.example.social_network_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document(collection = "thoughts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thought {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field(name = "thoughtText")
    private String thoughtText;

    @Field(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field(name = "username")
    private String username;

    @DBRef
    private List<Reaction> reactions;

    public int getReactionCount() {
        return reactions != null ? reactions.size() : 0;
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' hh:mm a");
        return createdAt.format(formatter);
    }
}
