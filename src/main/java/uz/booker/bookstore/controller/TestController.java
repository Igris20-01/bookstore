package uz.booker.bookstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Here is your resource");
    }

}
