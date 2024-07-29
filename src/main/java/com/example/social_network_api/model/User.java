package com.example.social_network_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field(name = "username")
    private String username;

    @Field(name = "email")
    private String email;

    @DBRef
    private List<Thought> thoughts;

    @DBRef
    private List<User> friends;

    public int getFriendCount() {
        return friends != null ? friends.size() : 0;
    }

}
