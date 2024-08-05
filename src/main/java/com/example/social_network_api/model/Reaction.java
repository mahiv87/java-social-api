package com.example.social_network_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collection = "reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {

    @MongoId(FieldType.OBJECT_ID)
    private String reactionId;

    @Field("reactionBody")
    private String reactionBody;

    @Field("username")
    private String username;

    @Field("createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' hh:mm a");
        return createdAt.format(formatter);
    }

}
