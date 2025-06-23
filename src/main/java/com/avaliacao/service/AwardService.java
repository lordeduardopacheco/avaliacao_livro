package com.avaliacao.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.avaliacao.dto.AwardResultDto;
import com.avaliacao.dto.ProducerIntervalAwardDto;
import com.avaliacao.model.Movie;
import com.avaliacao.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwardService {
    private final MovieRepository movieRepository;

    public AwardResultDto getAwardWinnersInterval() {
        List<Movie> winnersByYear = movieRepository.findByWinnerTrueOrderByYearAsc();
                
        Map<String, List<Integer>> producerWinners = new HashMap<>();

        for (Movie movie : winnersByYear) {
        	Arrays.stream(movie.getProducers().split(",| and ")).forEach(producer -> {
        	    producerWinners
                .computeIfAbsent(producer.trim(), key -> new ArrayList<>())
                .add(movie.getYear());
        	});
        }
        
        List<ProducerIntervalAwardDto> listProducerIntervalAwardDto = new ArrayList<ProducerIntervalAwardDto>();
        producerWinners.forEach((producer, years) -> {
            years = years.stream().sorted().collect(Collectors.toList());
            for (int i = 1; i < years.size(); i++) {
                int previous = years.get(i - 1);
                int following = years.get(i);
                int interval = following - previous;

                listProducerIntervalAwardDto.add(
                    ProducerIntervalAwardDto.builder()
                        .producer(producer)
                        .previousWin(previous)
                        .followingWin(following)
                        .interval(interval)
                        .build()
                );
            }
        });
        
        int minInterval = listProducerIntervalAwardDto.stream()
        	    .mapToInt(ProducerIntervalAwardDto::getInterval)
        	    .min()
        	    .orElse(0);

        int maxInterval = listProducerIntervalAwardDto.stream()
        	    .mapToInt(ProducerIntervalAwardDto::getInterval)
        	    .max()
        	    .orElse(0);

        List<ProducerIntervalAwardDto> minList = listProducerIntervalAwardDto.stream()
            .filter(i -> i.getInterval() == minInterval)
            .collect(Collectors.toList());

        List<ProducerIntervalAwardDto> maxList = listProducerIntervalAwardDto.stream()
            .filter(i -> i.getInterval() == maxInterval)
            .collect(Collectors.toList());

        return new AwardResultDto(minList, maxList);
    }
}