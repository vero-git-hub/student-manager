package org.example.studentmanager.controller;

import org.example.studentmanager.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author vero-git-hub
 **/
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void accessProtectedResourceWithoutToken_ShouldReturn401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void accessProtectedResourceWithValidToken_ShouldReturn200() throws Exception {
        String validJwtToken = customGenerateToken();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                        .header("Authorization", validJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String customGenerateToken() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");
        return "Bearer " + jwtUtil.generateToken(userDetails);
    }

    @Test
    public void accessProtectedResourceWithInvalidToken_ShouldReturn401() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");
        String validJwtToken = jwtUtil.generateToken(userDetails);
        String invalidJwtToken = "Bearer " + validJwtToken.substring(0, validJwtToken.length() - 10) + "invalid-part";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                        .header("Authorization", invalidJwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
