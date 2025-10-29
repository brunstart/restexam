package org.example.restexam.controller;

import org.example.restexam.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController // Controller + ResponseBody : 요청이 들어오면 view로 만드는게 아니고 "데이터를 클라이언트에게 반환"
public class HelloController {
    @GetMapping("/hi")
    public String hi() {
        return "hi rest controller";
    }

    @GetMapping("/user")
    public User user() {
        return new User(1L, "carami", "carami@nate.com");   // JSON 형태의 문자열이 넘어감, HttpMessageConverter가 객체를 JSON로 변환해서 응답
    }

    @GetMapping("/greeting")
    public Map<String, String> greet(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name) {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}
