package com.example.dockerexcercise;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Controller
public class SpringHomeController  {
    @PostMapping("/webhook")
    public ResponseEntity<String> sendWebhook(@RequestParam("username") String username) {
        RestTemplate restTemplate = new RestTemplate();

        // Webhook URL 설정
        String webhookUrl = "https://discord.com/api/webhooks/1090549186040184913/_nVKm8TfNkMjjZDz2lgNqbVQBmciLcgii6trO7nXIEz7LAtMHVRBXkSVwoSd-nYRGxzU";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LocalDate date = LocalDate.now();
        // Webhook 메시지 생성
        String payload = "{\"username\": \"" + username + "\", \"content\": \""+date+"\"}";

        // 요청 본문과 헤더를 포함한 HTTP 엔티티 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        // POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, requestEntity, String.class);

        return responseEntity;
    }
}
