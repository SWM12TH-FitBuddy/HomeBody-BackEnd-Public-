package tech.homebody.homebodyuser.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.homebody.homebodyuser.HomeBodyUserApplicationTests;
import tech.homebody.homebodyuser.dto.security.User;

@RestController
@RequestMapping("private")
public class PrivateEndPoint extends HomeBodyUserApplicationTests {

    @GetMapping("user-details")
    @Test
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

}
