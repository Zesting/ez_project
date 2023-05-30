package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.SampleDomain;

public interface SampleRepository {
    SampleDomain sampleSave(SampleDomain sampleDomain);

    List<SampleDomain> findByAll();

    Optional<SampleDomain> findById(Long sampleFiledId);

    Optional<SampleDomain> update(SampleDomain sampleDomain);

    Long Delete(Long sampleFiledId);

}
