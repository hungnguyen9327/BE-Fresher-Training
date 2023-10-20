package com.example.mongodb_topic4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String username;
    private String email;
    private String password;
    private String status;

    @Field("created_at")
    @CreatedDate
    private OffsetDateTime createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @Version
    private Integer version;
}
