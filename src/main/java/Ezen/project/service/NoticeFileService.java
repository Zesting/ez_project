package Ezen.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import Ezen.project.domain.NoticeFileDomain;
import Ezen.project.repository.NoticeFileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeFileService {

    private final NoticeFileRepository noticeFileRepository;

    //findById
    @Transactional
    public NoticeFileDomain findById(Long id) {
        Optional<NoticeFileDomain> optionalNoticeFileDomain = noticeFileRepository.findById(id);
        if(optionalNoticeFileDomain.isPresent()){
           return optionalNoticeFileDomain.get();
        } else 
            return null;
        
    }
    
}
