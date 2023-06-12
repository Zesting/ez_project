package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Ezen.project.DTO.WeddingDTO;
import Ezen.project.domain.WeddingDomain;
import Ezen.project.repository.WeddingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeddingService {
    private final WeddingRepository weddingRepository;

    //작성 기능
	public Long save(WeddingDTO weddingDTO) {
        WeddingDomain weddingDomain = WeddingDomain.toSaveEntity(weddingDTO);
        weddingRepository.save(weddingDomain);
		return weddingDomain.getWeddingId();
	}

    public List<WeddingDTO> findAll() {
        List<WeddingDomain> weddingDomainList = weddingRepository.findAll();
        List<WeddingDTO> weddingDTOList = new ArrayList<>();
        for(WeddingDomain weddingDomain: weddingDomainList){
            weddingDTOList.add(WeddingDTO.toWeddingDTO(weddingDomain));
        }
        return weddingDTOList;
    }

    public WeddingDTO findById(Long id) {
        Optional<WeddingDomain> optionalNoticeDomain = weddingRepository.findById(id);
        if(optionalNoticeDomain.isPresent()){
            WeddingDomain weddingDomain = optionalNoticeDomain.get();
            WeddingDTO weddingDTO = WeddingDTO.toWeddingDTO(weddingDomain);
            return weddingDTO;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        weddingRepository.deleteById(id);
    }


}
