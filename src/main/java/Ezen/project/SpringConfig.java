package Ezen.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Ezen.project.repository.*;

@Configuration
public class SpringConfig {

    @Bean
    public SampleRepository sampleRepository() {
        return new MemorySampleRepository();
    }

}
