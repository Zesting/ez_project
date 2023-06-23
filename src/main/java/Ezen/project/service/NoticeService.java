package Ezen.project.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Ezen.project.DTO.NoticeDTO;
import Ezen.project.domain.NoticeDomain;
import Ezen.project.domain.NoticeFileDomain;
import Ezen.project.repository.NoticeFileRepository;
import Ezen.project.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    
    //작성 기능
    public Long save(NoticeDTO noticeDTO) throws IllegalStateException, IOException {
        // 파일 첨부 여부에 따라 로직 분리
            //첨부 파일 없을 때
        if(noticeDTO.getNoticeFile().isEmpty()){
            NoticeDomain noticeDomain = NoticeDomain.toSaveEntity(noticeDTO);
            noticeRepository.save(noticeDomain);
            return noticeDomain.getNoticeId();
            //첨부 파일 있을 때
        } else {
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름 생성
                // 내사진.jpg => 283758923523_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. notice 테이블에 해당 데이터 save 처리
                7. notice 파일 테이블에 해당 데이터 save 처리
             */

                MultipartFile noticeFile = noticeDTO.getNoticeFile(); //1
                String originalFileName = noticeFile.getOriginalFilename(); //2
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName; //3
                String savePath = System.getProperty("user.dir")+"/src/main/resources/static/images/" + storedFileName; // C:/notic_img/235235_내사진.jpg //4
                noticeFile.transferTo(new File(savePath)); //5

                NoticeDomain noticeDomain = NoticeDomain.toSaveFileEntity(noticeDTO);
                Long saveId = noticeRepository.save(noticeDomain).getNoticeId();
                NoticeDomain notice = noticeRepository.findById(saveId).get();

                NoticeFileDomain noticeFileDomain = NoticeFileDomain.toNoticeFileEntity(notice, originalFileName, storedFileName);
                noticeFileRepository.save(noticeFileDomain);
                
            

            return noticeDomain.getNoticeId();
        }
        
    }

    //공지 테이블 모든 데이터 값 찾는 기능
    @Transactional
    public List<NoticeDTO> findAll() {
        // List<NoticeDomain> noticeDomainList = noticeRepository.findAll();
        List<NoticeDomain> noticeDomainList = noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeWriteDate"));
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for(NoticeDomain noticeDomain: noticeDomainList){
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeDomain));
        }
        return noticeDTOList;
    }

    //아이디 값만 찾는 기능 (상세보기)
    @Transactional
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
    public Long update(NoticeDTO noticeDTO) throws IllegalStateException, IOException {


        if(noticeDTO.getNoticeFile().isEmpty()){
            NoticeDomain noticeDomain = NoticeDomain.toUpdateEntity(noticeDTO);
            noticeRepository.save(noticeDomain);
        return noticeDomain.getNoticeId();

        } else {
           

            MultipartFile noticeFile = noticeDTO.getNoticeFile(); //1
                String originalFileName = noticeFile.getOriginalFilename(); //2
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName; //3
                String savePath = System.getProperty("user.dir")+"/src/main/resources/static/images/" + storedFileName; // C:/notic_img/235235_내사진.jpg //4
                noticeFile.transferTo(new File(savePath)); //5

                NoticeDomain noticeDomain = NoticeDomain.toSaveFileEntity(noticeDTO);
                Long saveId = noticeRepository.save(noticeDomain).getNoticeId();
                NoticeDomain notice = noticeRepository.findById(saveId).get();

                NoticeFileDomain noticeFileDomain = NoticeFileDomain.toNoticeFileEntity(notice, originalFileName, storedFileName);
                noticeFileRepository.save(noticeFileDomain);

                return noticeDomain.getNoticeId();
        }
        
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

        if (page < 0) {
                page = 0;
            }

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
