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
        return em.createQuery("select m from SampleDomain m ", SampleDomain.class).getResultList();
    }

    @Override
    public Optional<SampleDomain> findById(Long sampleFiledId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<SampleDomain> update(SampleDomain sampleDomain) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Long Delete(Long sampleFiledId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

}
