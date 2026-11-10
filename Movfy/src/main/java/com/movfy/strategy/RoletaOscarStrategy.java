package com.movfy.strategy;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RoletaOscarStrategy implements RoletaStrategy {

   
    private final List<Long> oscarIds = Arrays.asList(872585L, 496243L, 98L, 597L, 122L);

    @Override
    public Long sortearFilmeId() {
        Random random = new Random();
        int index = random.nextInt(oscarIds.size());
        return oscarIds.get(index);
    }
}