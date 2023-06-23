//결제 리스트 조회
$(document).on("click", "#paymentListBtn", function () {//paymentListBtn
  $.ajax({
    url: "/paymentList", //요청 url>> 불러올 리스트 
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      console.log("ajax 성공! HTML-> 리스트");
      $("#adminPageContainer").slideUp();
      $("#roomListContainer").html(data); //여기 roomListContainer는 준희가 만든 컨테이너박스에 데이터를 준다.
      $("#roomModifyContainer").slideUp();//다른 페이지 이동하면 접힘.
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

  //조회 버튼 클릭 > 결제id 조회 불러오기
  $(document).on("click", "#paymentDetailBtn", function () {
    let id = $(this).closest("tr").find("#payId").val();
    $.ajax({
      url: "/adminPaymentInfo/" + id,
      method: "GET",
      dataType: "HTML",
      success: function (data) {
        console.log("ajax 성공! HTML-> 조회");
        console.log("id 값은?"+id);
        $("#roomModifyContainer").html(data);
        $("#roomModifyContainer").slideDown();
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
      }
    });
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