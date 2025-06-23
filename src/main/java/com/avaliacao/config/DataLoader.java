package com.avaliacao.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.avaliacao.model.Movie;
import com.avaliacao.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
        		getClass().getClassLoader().getResourceAsStream("movielist.csv")))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                movieRepository.save(Movie.builder()
                        .year(Integer.parseInt(fields[0]))
                        .title(fields[1])
                        .studios(fields[2])
                        .producers(fields[3])
                        .winner(fields.length > 4 && "yes".equalsIgnoreCase(fields[4].trim()))
                        .build());
            }
        }catch(Exception ex) {
          ex.printStackTrace();
        }
    }
}
