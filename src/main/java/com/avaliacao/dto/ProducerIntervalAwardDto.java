package com.avaliacao.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProducerIntervalAwardDto {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}