package Ezen.project.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import Ezen.project.DTO.KakaoPayApprovalVO;
import Ezen.project.DTO.KakaoPayCancelResponseVO;
import Ezen.project.DTO.KakaoPayReadyVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor
@Transactional
public class KakaopayService {

  private static final String HOST = "https://kapi.kakao.com";
  private KakaoPayReadyVO kakaoPayReadyVO;
  private KakaoPayApprovalVO kakaoPayApprovalVO;
  private KakaoPayCancelResponseVO kakaoPayCancelResponseVO;

  // ...............................결제 준비.............................................
  public String kakaoPayReady() {
    RestTemplate restTemplate = new RestTemplate();

    // 서버로 요청할 Header
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "KakaoAK " + "416234c21f220c45b3ea64f69ce4e775"); // admin키 넣어줘야한다.
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

    // 서버로 요청할 Body
    // Body에는 결제로 지정할 데이터를 넣는다.
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("cid", "TC0ONETIME"); // 테스트코드 넣어주고 실제 결제하려면 제휴Id를 넣어주면 된다.
    params.add("partner_order_id", "1001");
    params.add("partner_user_id", "gorany");
    params.add("item_name", "갤럭시S9");
    params.add("quantity", "1");
    params.add("total_amount", "2100");
    params.add("tax_free_amount", "100");
    params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
    params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
    params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

    HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

    try {
      // 포스트 방식으로 아래 주소로 보낸다. 요청이 성공되면 kakaopayreadyVO로 응답정보를 보내준다.
      kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

      log.info("" + kakaoPayReadyVO);

      // 결제가 완료되면 해당주소로 가게끔 한다.
      return kakaoPayReadyVO.getNext_redirect_pc_url();

    } catch (RestClientException e) {
      System.out.println("결제실패?????????");
      e.printStackTrace();
    } catch (URISyntaxException e) {
      System.out.println("결제실패//////////////");

      e.printStackTrace();
    }
    return "/pay";
  }

  // ...............................결제 승인
  // 요청.............................................

  public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

    log.info("KakaoPayInfoVO............................................");
    log.info("-----------------------------");

    RestTemplate restTemplate = new RestTemplate();

    // 서버로 요청할 Header
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "KakaoAK " + "416234c21f220c45b3ea64f69ce4e775");
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

    // 서버로 요청할 Body
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("cid", "TC0ONETIME");
    params.add("tid", kakaoPayReadyVO.getTid());
    params.add("partner_order_id", "1001");
    params.add("partner_user_id", "gorany");
    params.add("pg_token", pg_token); // 결제 승인 요청을 인증하는 토큰
    params.add("total_amount", "2100");

    HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

    try {
      kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
          KakaoPayApprovalVO.class);
      log.info("" + kakaoPayApprovalVO);

      return kakaoPayApprovalVO;

    } catch (RestClientException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return null;

  }

  // ...............................결제 취소 요청.............................................

  public KakaoPayCancelResponseVO kakaoPayCancel(Integer amount, String tid) {

    log.info("KakaoPayCancelVO............................................");
    log.info("-----------------------------");

    RestTemplate restTemplate = new RestTemplate();

    // 서버로 요청할 Header
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "KakaoAK " + "416234c21f220c45b3ea64f69ce4e775");
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

    // 서버로 요청할 Body
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("cid", "TC0ONETIME"); // 가맹점 코드
    params.add("tid", tid); // 결제 고유 번호
    params.add("cancel_amount", String.valueOf(amount)); // 취소 금액 String.valueOf(amount)
    params.add("cancel_tax_free_amount", "0"); // 취소 비과세 금액

    // 파라메타, 헤더
    HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

    try {
      kakaoPayCancelResponseVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayCancelResponseVO.class);
      log.info("" + kakaoPayCancelResponseVO);

      return kakaoPayCancelResponseVO;

    } catch (RestClientException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return null;

  }
}
