package ru.vtb.rama.swagerrest.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandartObjectConverter {

    @Bean(name = "standardModelMapper")
    public ModelMapper defaultObjectMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        return mapper;
    }
}
