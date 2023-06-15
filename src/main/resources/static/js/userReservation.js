$(document).on("click", "#postBtn", function () {
  console.log("choiceDate Post 시작");
  const checkDate = {
    checkIn: document.getElementById("checkIn").value,
    checkOut: document.getElementById("checkOut").value,
  };
  console.log("checkDate ->", checkDate);
  $.ajax({
    url: "/Reservations/SelectDate",
    method: "POST",
    data: JSON.stringify(checkDate),
    contentType: "application/json; charset=UTF-8", // 컨텐츠 타입 설정
    dataType: "JSON",
    success: function (data) {
      console.log("ajax 성공! JSON :", data);
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

  // Get {/Reservations/SelectRoom}
  $.ajax({
    url: "/Reservations/SelectRoom",
    method: "GET",
    dataType: "HTML",
    success: function (data) {
      console.log("ajax 성공! HTML->", data);

      $("#bookableRoomList").html(data);
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

  //Post {/Reservations/SelectRoom}

  $(document).on("click", "#choiceRoom", function () {
    console.log("choiceRoom Post 시작");
    let roomId = document.getElementById("choiceRoomId").value;
    console.log("roomId ->", roomId);
    const params = {
      roomId: document.getElementById("choiceRoomId").value,
      roomName: document.getElementById("choiceRoomName").value,
      roomPrice: document.getElementById("choiceRoomPrice").value,
      roomType: document.getElementById("choiceRoomType").value,
      roomDetailInfo: document.getElementById("choiceRoomDetailInfo").value,
    };
    console.log("Params :", params);

    $.ajax({
      url: "/Reservations/SelectRoom",
      method: "POST",
      dataType: "JSON",
      contentType: "application/json; charset=UTF-8",
      data: JSON.stringify(params), // 폼 데이터 직렬화하여 전송
      success: function (data) {
        console.log("modifyForm Post Ajax 성공!", data);
        location.href = "/payment";
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
  });
});

$(document).ready(function () {
  $(".input-daterange").datepicker({
    format: "yyyy-mm-dd",
    todayHighlight: true,
    startDate: "0d",
  });
});
