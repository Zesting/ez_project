package Ezen.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Ezen.project.repository.*;
import Ezen.project.service.SampleService;

@Configuration
public class SpringConfig {

    @Bean
    public SampleRepository sampleRepository() {
        return new MemorySampleRepository();
    }

    @Bean
    public SampleService sampleService() {
        return new SampleService(sampleRepository());
    }

}
