package com.avaliacao.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AwardResultDto {
    private List<ProducerIntervalAwardDto> min;
    private List<ProducerIntervalAwardDto> max;
}
