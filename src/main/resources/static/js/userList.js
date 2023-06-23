//user 리스트 조회
$(document).on("click", "#userListBtn", function () {
  $.ajax({
    url: "/userlist", //요청 url
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      console.log("ajax 성공! HTML-> 리스트");
      $("#adminPageContainer").slideUp();
      $("#roomListContainer").html(data); //여기 roomListContainer는 준희가 만든 컨테이너박스에 데이터를 준다.
      $("#roomModifyContainer").slideUp();
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


  //user id별 detail조회
  $(document).on("click", "#detailBtn", function () {
    let userId = $(this).closest("tr").find("#userId").val(); // 선택한 행의 userId 값을 가져옴
    $.ajax({
      url: "/user/" + userId,
      method: "GET",
      dataType: "HTML",
      success: function (data) {
        console.log("ajax 성공! HTML-> 조회");
        console.log("userId", userId);
        $("#roomModifyContainer").html(data);//roomModifyContainer에 붙이기위함.
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

$("#deleteBtn").on("click", function () {
  if (confirm("회원탈퇴를 진행하시겠습니까?") == true) {
    alert("회원 탈퇴처리가 완료되었습니다.")
  } else {
    return;
  }
})