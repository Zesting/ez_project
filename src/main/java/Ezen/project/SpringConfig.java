package Ezen.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Ezen.project.repository.JpaSampleRepository;
import Ezen.project.repository.SampleRepository;
import Ezen.project.service.SampleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    @Autowired
    private EntityManager em;

    @Bean
    public SampleService sampleService() {
        return new SampleService(sampleRepository());
    }

    @Bean
    public SampleRepository sampleRepository() {
        return new JpaSampleRepository(em);
    }


}
