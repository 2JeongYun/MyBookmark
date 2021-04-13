package com.github.neukrang.mybookmark.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelloResponseDto {

    private String stringData;
    private int numberData;

    @Builder
    public HelloResponseDto(String stringData, int numberData) {
        this.stringData = stringData;
        this.numberData = numberData;
    }
}
