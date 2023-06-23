//noticeList 조회
$(document).on("click", "#noticeListBtn", function () {//noticeListBtn
  $.ajax({
    url: "/noticeList", //요청 url>> 불러올 리스트 
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      console.log("ajax 성공! HTML->리스트 ", data);
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

  $(document).on("click", "#noticeDetailBtn", function () {
    let noticeId = $(this).closest("tr").find("#noticeId").val();
    $.ajax({
      url: "/notice/"+noticeId,//'/notice/{id}'
      method: "GET",
      dataType: "HTML",
      success: function (data) {
      console.log("ajax 성공! HTML->조회 ",data);
      console.log("noticeId : "+noticeId);
        $("#roomModifyContainer").html(data);
      },
      error: function (request, error) {
        console.log(
          console.log(
            "code:" +
            request.status +
            "\n" +
            "message:" +
            request.responseText +
            "\n" +
            "error:" +
            error
          )
        )
      }
    });
  });//detail 조회

});//List 조회
