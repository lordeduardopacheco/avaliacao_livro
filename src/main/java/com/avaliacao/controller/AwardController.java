package com.avaliacao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.avaliacao.dto.AwardResultDto;
import com.avaliacao.service.AwardService;

@RestController
@RequestMapping("/api/awards")
@RequiredArgsConstructor
public class AwardController {
    private final AwardService awardService;

    @GetMapping("/intervals")
    public AwardResultDto getAwardIntervals() {
        return awardService.getAwardWinnersInterval();
    }
}