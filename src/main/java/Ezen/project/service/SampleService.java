package Ezen.project.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import Ezen.project.domain.SampleDomain;
import Ezen.project.repository.SampleRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    public Long sampleJoin(SampleDomain sampleDomain) {
        sampleRepository.sampleSave(sampleDomain);
        return sampleDomain.getSampleFiledId();
    }

    public List<SampleDomain> findByAll() {
        return sampleRepository.findByAll();

    }

}
