package Ezen.project.service;

import Ezen.project.domain.SampleDomain;
import Ezen.project.repository.SampleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    //
    public Long sampleJoin(SampleDomain sampleDomain) {
        sampleRepository.sampleSave(sampleDomain);
        return sampleDomain.getSampleFiledId();
    }

}
