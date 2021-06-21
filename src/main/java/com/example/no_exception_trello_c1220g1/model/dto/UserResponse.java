package com.example.no_exception_trello_c1220g1.model.dto;

import com.example.no_exception_trello_c1220g1.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    @Id
    private Long id;
    private String username;
    private String avatar;
    private String email;

    public static UserResponse build(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUserName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .build();
    }
}
