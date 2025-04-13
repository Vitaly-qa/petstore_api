package models.user;

import lombok.*;

@Getter
@Setter
@Builder
public class UserLogin {
    private String username;
    private String password;


    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}