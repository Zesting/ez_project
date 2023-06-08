package Ezen.project.service;

import org.springframework.stereotype.Service;

import Ezen.project.domain.NoticeDomain;
import Ezen.project.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    public void save(NoticeDomain noticeDomain) {
        noticeRepository.save(noticeDomain);
    }
    
}
