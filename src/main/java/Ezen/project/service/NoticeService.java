package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.domain.NoticeDomain;
import Ezen.project.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    

    public void save(NoticeDTO noticeDTO) {
        NoticeDomain noticeDomain = NoticeDomain.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeDomain);
    }


    public List<NoticeDTO> findAll() {
        List<NoticeDomain> noticeDomainList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for(NoticeDomain noticeDomain: noticeDomainList){
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeDomain));
        }
        return noticeDTOList;
    }


    public NoticeDTO findById(Long id) {
        Optional<NoticeDomain> optionalNoticeDomain = noticeRepository.findById(id);
        if(optionalNoticeDomain.isPresent()){
            NoticeDomain noticeDomain = optionalNoticeDomain.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeDomain);
            return noticeDTO;
        } else {
            return null;
        }
    }


    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeDomain noticeDomain = NoticeDomain.toUpdateDomain(noticeDTO);
        noticeRepository.save(noticeDomain);
        return findById(noticeDTO.getId());
    }


    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
    
}
