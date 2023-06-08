package Ezen.project.repository;

import java.util.List;
import java.util.Optional;

import Ezen.project.domain.SampleDomain;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaSampleRepository implements SampleRepository {

    private final EntityManager em;

    @Override
    public SampleDomain sampleSave(SampleDomain sampleDomain) {
        em.persist(sampleDomain);
        return sampleDomain;
    }

    @Override
    public List<SampleDomain> findByAll() {
        String jpql = "select m from SampleDomain m";
        return em.createQuery(jpql, SampleDomain.class).getResultList();
    }

    @Override
    public Optional<SampleDomain> findById(Long sampleFiledId) {
        return Optional.empty();
    }

    @Override
    public Optional<SampleDomain> update(SampleDomain sampleDomain) {
        return Optional.empty();
    }

    @Override
    public Long Delete(Long sampleFiledId) {
        return null;
    }

}
