package Ezen.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.domain.NoticeDomain;
import Ezen.project.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    
    //작성 기능
    public Long save(NoticeDTO noticeDTO) {
        NoticeDomain noticeDomain = NoticeDomain.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeDomain);
        return noticeDomain.getNoticeId();
    }

    //공지 테이블 모든 데이터 값 찾는 기능
    public List<NoticeDTO> findAll() {
        List<NoticeDomain> noticeDomainList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for(NoticeDomain noticeDomain: noticeDomainList){
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeDomain));
        }
        return noticeDTOList;
    }

    //아이디 값만 찾는 기능 (상세보기)
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

    //수정
    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeDomain noticeDomain = NoticeDomain.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeDomain);
        return findById(noticeDTO.getId());
    }

    //삭제 기능
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public Page<NoticeDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 10; //한 페이지에 보여줄 글 개수
        // 한 페이지당 10개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<NoticeDomain> noticeDomains = 
            noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "noticeId")));
        
        System.out.println("noticeDomains.getContent() = " + noticeDomains.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("noticeDomains.getTotalElements() = " + noticeDomains.getTotalElements()); // 전체 글갯수
        System.out.println("noticeDomains.getNumber() = " + noticeDomains.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("noticeDomains.getTotalPages() = " + noticeDomains.getTotalPages()); // 전체 페이지 갯수
        System.out.println("noticeDomains.getSize() = " + noticeDomains.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("noticeDomains.hasPrevious() = " + noticeDomains.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("noticeDomains.isFirst() = " + noticeDomains.isFirst()); // 첫 페이지 여부
        System.out.println("noticeDomains.isLast() = " + noticeDomains.isLast()); // 마지막 페이지 여부
        
        // 목록 : id, writer, title, cteatedTime
        Page<NoticeDTO> noticeDTOS = noticeDomains.map(
            notice -> new NoticeDTO(notice.getNoticeId(), 
                                    notice.getUserId(), 
                                    notice.getNoticeTitle(), 
                                    notice.getNoticeWriteDate()));
        return noticeDTOS;
    }
    
}
