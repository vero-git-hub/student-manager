package org.example.studentmanager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author vero-git-hub
 **/
public class AuthRequest {

    @NotNull
    @Size(min = 4, max = 20)
    @Schema(description = "Username of the user", example = "testuser")
    private String username;

    @NotNull
    @Size(min = 8)
    @Schema(description = "Password of the user", example = "P@ssw0rd!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}