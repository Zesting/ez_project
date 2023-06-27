//noticeList 조회
$(document).on("click", "#noticeListBtn", function () {//noticeListBtn
  $.ajax({
    url: "/noticeList", //요청 url>> 불러올 리스트 
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      // console.log("ajax 성공! HTML->리스트 ", data);
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
  //비동기 조회를 하려했으나 플래그먼트 부분을 
  // $(document).on("click", "#noticeDetailBtn", function () {
  //   let noticeId = $(this).closest("tr").find("#noticeId").val();
  //   $.ajax({
  //     url: "/notice/"+noticeId,//'/notice/{id}'
  //     method: "GET",
  //     dataType: "HTML",
  //     success: function (data) {
  //     console.log("ajax 성공! HTML->조회 ",data);
  //     console.log("noticeId : "+noticeId);
  //       $("#roomModifyContainer").html(data);
  //       $("#roomModifyContainer").slideDown();
  //     },
  //     error: function (request, error) {
  //       console.log(
  //         console.log(
  //           "code:" +
  //           request.status +
  //           "\n" +
  //           "message:" +
  //           request.responseText +
  //           "\n" +
  //           "error:" +
  //           error
  //         )
  //       )
  //     }
  //   });
  // });//detail 조회
});//List 조회
  //공지사항 등록
$(document).on("click", "#noticeForm", function () {
  $.ajax({
    url: "/adminNoticeSave",
    method: "GET",
    dataType: "HTML",
    success: function (data) {
      $("#adminPageContainer").slideUp();
      $("#roomModifyContainer").slideUp();
      $("#roomListContainer").html(data);
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

  $(document).on("click", "#noticePostBtn", function () {
    // console.log("RoomAdd start");
    const params = {
      userId: document.getElementById("userId").value,
      title: document.getElementById("noticeTitle").value,
      content: document.getElementById("noticeContent").value,
      roomDnoticeFileetailInfo: document.getElementById("noticeFile").value
    };
    ajax("/notice/save", "POST", params);
  });
});
