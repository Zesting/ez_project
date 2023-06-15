package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Ezen.project.DTO.WeddingCommentDTO;
import Ezen.project.domain.WeddingCommentEntity;
import Ezen.project.domain.WeddingDomain;
import Ezen.project.repository.WeddingCommentRepository;
import Ezen.project.repository.WeddingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeddingCommentService {    

    private final WeddingCommentRepository weddingCommentRepository;
    private final WeddingRepository weddingRepository;

    public Long save(WeddingCommentDTO weddingCommentDTO) {
        /* 부모 엔티티(WeddingDomain) 조회 */
        Optional<WeddingDomain> optionalWeddingDomain = weddingRepository.findById(weddingCommentDTO.getWeddingId());
        if(optionalWeddingDomain.isPresent()){
            WeddingDomain weddingDomain = optionalWeddingDomain.get();
            WeddingCommentEntity weddingCommentEntity = WeddingCommentEntity.toSaveEntity(weddingCommentDTO, weddingDomain);
            return weddingCommentRepository.save(weddingCommentEntity).getId();
        } else {
            return null;
        }
        

    }

    public List<WeddingCommentDTO> findAll(Long weddingId) {
        // select * from weddingComment where weddingId = ? order by id desc;
        WeddingDomain weddingDomain = weddingRepository.findById(weddingId).get();
        List<WeddingCommentEntity> weddingCommentEntityList = weddingCommentRepository.findAllByWeddingDomainOrderByIdDesc(weddingDomain);
        /* EntityList -> DTOList */
        List<WeddingCommentDTO> weddingCommentDTOList = new ArrayList<>();
        for(WeddingCommentEntity weddingCommentEntity: weddingCommentEntityList){
            WeddingCommentDTO weddingCommentDTO = WeddingCommentDTO.toWeddingCommentDTO(weddingCommentEntity, weddingId);
            weddingCommentDTOList.add(weddingCommentDTO);
        }
        return weddingCommentDTOList;
    }


    
}
