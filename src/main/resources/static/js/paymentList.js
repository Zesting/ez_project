//결제 리스트 조회
$(document).on("click", "#paymentListBtn", function () {
  $.ajax({
    url: "/paymentList", //요청 url>> 불러올 리스트 
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      // console.log("ajax 성공! HTML->", data);
      $("#roomListContainer").html(data); //여기 roomListContainer는 준희가 만든 컨테이너박스에 데이터를 준다.
    },
    error: function (request, error) {
      console.log(
        "code:" +
          request.status +
          "\n" +
          "message:" +
          request.responseText +
          "\n" +
          "error:" +
          error
      );
    },
  });
});

//결제 삭제 요청 >> 다시 만들기 
$(document).on("click", `#'deleteBtn('+[[${paymentList.id}]]+')'`, function () {
  $.ajax({
    url: "/adminPage", //요청 url>> 불러올 리스트 
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      // console.log("ajax 성공! HTML->", data);
      // location.reload();
      $("#roomListContainer").html(data); //여기 roomListContainer는 준희가 만든 컨테이너박스에 데이터를 준다.
    },
    error: function (request, error) {
      console.log(
        "code:" +
          request.status +
          "\n" +
          "message:" +
          request.responseText +
          "\n" +
          "error:" +
          error
      );
    },
  });
});

/* =========================================================================================== */
/** ajax 기본 메서드(POST, PUT, DELETE) */
/** ajax(url, method, data)  */
function ajax(url, method, data) {
  // console.log("Put Ajax", url, data);
  $.ajax({
    url: url,
    method: method,
    dataType: "JSON",
    contentType: "application/json; charset=UTF-8",
    data: JSON.stringify(data), // 폼 데이터 직렬화하여 전송
    success: function (data, alert) {
      console.log("modifyForm Post Ajax 성공!", data);
      location.reload();
      window.alert("성공");
    },
    error: function (request, error) {
      console.log(
        "code:" +
          request.status +
          "\n" +
          "message:" +
          request.responseText +
          "\n" +
          "error:" +
          error
      );
      window.alert("실패");
    },
  });
}