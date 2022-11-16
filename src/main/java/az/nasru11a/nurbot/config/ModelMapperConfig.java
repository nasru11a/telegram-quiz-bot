package az.nasru11a.nurbot.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new org.modelmapper.ModelMapper();
    }

}
