package tech.homebody.homebodyuser.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.homebody.homebodyuser.HomeBodyUserApplicationTests;


@RestController
@RequestMapping("public")
public class PublicEndPoints extends HomeBodyUserApplicationTests {

    @GetMapping("test")
    @Test
    ResponseEntity<String> getPublic() {

        return ResponseEntity.ok("OK");
    }
}
