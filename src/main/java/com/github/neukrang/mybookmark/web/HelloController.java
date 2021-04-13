package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.web.dto.HelloResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public String helloDto(Model model) {
        List<HelloResponseDto> data = new LinkedList<>();
        data.add(HelloResponseDto.builder()
                .stringData("a")
                .numberData(1)
                .build());
        data.add(HelloResponseDto.builder()
                .stringData("b")
                .numberData(2)
                .build());
        data.add(HelloResponseDto.builder()
                .stringData("c")
                .numberData(3)
                .build());
        model.addAttribute("data", data);
        return "hello-dto";
    }
}
