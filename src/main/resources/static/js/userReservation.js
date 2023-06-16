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

  //Post {/Reservations/SelectRoom}

  $(document).on("click", "#choiceRoom", function () {
    console.log("choiceRoom Post 시작");
    var $row = $(this).closest("tr");
    var roomId = $row.find("#choiceRoomId").val();
    var roomName = $row.find("#choiceRoomName").val();
    var roomPrice = $row.find("#choiceRoomPrice").val();
    var roomType = $row.find("#choiceRoomType").val();
    var roomDetailInfo = $row.find("#choiceRoomDetailInfo").val();
    const params = {
      roomId: roomId,
      roomName: roomName,
      roomPrice: roomPrice,
      roomType: roomType,
      roomDetailInfo: roomDetailInfo,
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
