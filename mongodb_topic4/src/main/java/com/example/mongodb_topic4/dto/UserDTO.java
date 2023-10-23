package com.example.mongodb_topic4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String _id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @Override
    public String toString() {
        return "UserDTO{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
