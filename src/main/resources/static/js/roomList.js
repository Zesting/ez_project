// 룸 리스트 폼 View(ajax)

$(document).on("click", "#listForm", function () {
  // jQuery에서 제공하는 함수로, HTML 문서의 로딩이 완료되었을 때 실행되는 이벤트 핸들러
  $.ajax({
    url: "/Rooms", // 요청 url
    method: "GET", // 방식
    dataType: "HTML", // 데이터 타입
    success: function (data) {
      console.log("ajax 성공! HTML->", data);

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

  // 수정 버튼 클릭 이벤트 처리
  $(document).on("click", "#modifyMoveBtn", function () {
    //문서 객체인 'document'에 클릭 이벤트 등록
    // (선택자 :.modifyMoveBtn) <- 클릭 이벤트를 처리할 대상 요소
    console.log("modifyMoveBtn Start");
    let roomId = $(this).closest("tr").find("td[name='roomId']").text();
    // 상위 <tr> 요소에서 td[name='roomId']를 찾아 텍스트 가져옴.
    // location.href = "/Rooms/modify/" + roomId;
    // 버튼 폼 불러오는 ajax
    console.log("roomId -> ", roomId);
    $(document).ready(function () {
      $.ajax({
        url: "/Rooms/modify/" + roomId,
        method: "GET",
        dataType: "HTML",
        success: function (data) {
          console.log("modify Ajax 성공 data ->", data);
          console.log(data);
          // html 변수를 DOM에 삽입하는 코드
          $("#roomModifyContainer").html(data);
        },
        function(request, error) {
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

    // 수정 버튼 클릭 이벤트 처리
    $(document).on("click", "#modifyRoomSubmit", function () {
      let roomId = document.getElementById("updateRoomId").value;
      console.log("roomId ->", roomId);
      const params = {
        roomId: document.getElementById("updateRoomId").value,
        roomName: document.getElementById("updateRoomName").value,
        roomPrice: document.getElementById("updateRoomPrice").value,
        roomType: document.getElementById("updateRoomType").value,
        roomDetailInfo: document.getElementById("updateRoomDetailInfo").value,
        roomImagePath: document.getElementById("updateRoomImagePath").value,
      };
      console.log("params", params);

      ajax("/Rooms/modify/" + roomId, "PUT", params);
    });
  });

  // 삭제 버튼 클릭 이벤트 처리
  $(document).on("click", "#deleteRoomBtn", function () {
    console.log("deleteRoom Start");

    let roomId = $(this).closest("tr").find("td[name='roomId']").text();
    console.log("params", roomId);
    ajax("/Rooms/delete/" + roomId, "DELETE", roomId);
  });
});

/* =========================================================================================== */
// 룸 생성 GetMapping, PostMapping

$(document).on("click", "#createForm", function () {
  $.ajax({
    url: "/Rooms/add",
    method: "GET",
    dataType: "HTML",
    success: function (data) {
      console.log("ajax 성공! data ->", data);
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

  $(document).on("click", "#postBtn", function () {
    console.log("RoomAdd start");
    const params = {
      roomName: document.getElementById("roomName").value,
      roomPrice: document.getElementById("roomPrice").value,
      roomType: $("input:radio[name=roomType]:checked").val(),
      roomDetailInfo: document.getElementById("roomDetailInfo").value,
      roomImagePath: document.getElementById("roomImagePath").value,
    };
    ajax("/Rooms/add", "POST", params);
  });
});

/* =========================================================================================== */
/** ajax 기본 메서드(POST, PUT, DELETE) */
/** ajax(url, method, data)  */
function ajax(url, method, data) {
  console.log("Put Ajax", url, data);
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
