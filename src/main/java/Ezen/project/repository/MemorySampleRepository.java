package Ezen.project.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Ezen.project.domain.SampleDomain;

public class MemorySampleRepository implements SampleRepository {

    private Map<Long, SampleDomain> store = new HashMap<>();
    private Long sequence = 0l;

    @Override
    public SampleDomain sampleSave(SampleDomain sampleDomain) {
        sampleDomain.setSampleFiledId(++sequence);
        store.put(sampleDomain.getSampleFiledId(), sampleDomain);
        return sampleDomain;
    }

    @Override
    public List<SampleDomain> findByAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<SampleDomain> findById(Long sampleFiledId) {
        return Optional.ofNullable(store.get(sampleFiledId));
    }

    @Override
    public Optional<SampleDomain> update(SampleDomain sampleDomain) {
        SampleDomain updateSample = new SampleDomain();
        updateSample.setSampleFiledId(sampleDomain.getSampleFiledId());
        store.put(updateSample.getSampleFiledId(), sampleDomain);
        return Optional.ofNullable(sampleDomain);
    }

    @Override
    public Long Delete(Long sampleFiledId) {
        store.remove(sampleFiledId);
        return sampleFiledId;

    }

}
