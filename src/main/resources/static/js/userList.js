$(document).on("click", "#userListBtn", function () {
  $.ajax({
    url: "/userlist", //요청 url
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      console.log("ajax 성공! HTML->", data);
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