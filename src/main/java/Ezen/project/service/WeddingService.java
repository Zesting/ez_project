package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //findAll
    public List<WeddingDTO> findAll() {
        // List<WeddingDomain> weddingDomainList = weddingRepository.findAll();
        List<WeddingDomain> weddingDomainList = weddingRepository.findAll(Sort.by(Sort.Direction.DESC, "weddingWriteDate"));
        List<WeddingDTO> weddingDTOList = new ArrayList<>();
        for(WeddingDomain weddingDomain: weddingDomainList){
            weddingDTOList.add(WeddingDTO.toWeddingDTO(weddingDomain));
        }
        return weddingDTOList;
    }

    //findById
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

    //delete
    public void delete(Long id) {
        weddingRepository.deleteById(id);
    }

    //페이징
    public Page<WeddingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 10;

        if(page <0){
            page = 0;
        }

        Page<WeddingDomain> weddingDomians = 
            weddingRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "weddingId")));
        
        Page<WeddingDTO> weddingDTOs = weddingDomians.map(
            wedding -> new WeddingDTO(wedding.getWeddingId(), 
                                      wedding.getUserId(),
                                      wedding.getWeddingTitle(),
                                      wedding.getWeddingWriteDate()));
        return weddingDTOs;

    }


}
