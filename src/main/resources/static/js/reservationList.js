// 예약 리스트 폼(ajax)

$(document).on("click", "#reservationListForm", function () {
  $.ajax({
    url: "/Reservations",
    method: "GET",
    dataType: "HTML",
    success: function (data) {
      console.log("ajax 성공!");
      $("#adminPageContainer").slideUp();
      $("#roomListContainer").html(data);
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

  $(document).on("click", "#modifyMoveBtn", function () {
    let reservationId = $(this)
      .closest("tr")
      .find("td[name = 'reservationId']")
      .text();
    console.log("reservationId ->", reservationId);
    $(document).ready(function () {
      $.ajax({
        url: "/Reservations/modify/" + reservationId,
        method: "GET",
        dataType: "HTML",
        success: function (data) {
          console.log("무브 성공");
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
        },
      });
    });
    $(document).on("click", "#modifySubmit", function () {
      let reservationId = document.getElementById("updateReservationId").value;

      const params = {
        reservationId: reservationId,
        roomId: document.getElementById("updateReservationRoomId").value,
        userId: document.getElementById("updateReservationUserId").value,
        reservationDate: document.getElementById("updateReservationDate").value,
        checkIn: document.getElementById("updateReservationCheckIn").value,
        checkOut: document.getElementById("updateReservationCheckOut").value,
        finalPrice: document.getElementById("updateReservationFinalPrice")
          .value,
      };
      console.log("params : ", params);

      ajax("/Reservations/modify/" + reservationId, "PUT", params);
    });
  });

  $(document).on("click", "#deleteSubmit", function () {
    console.log("deleteSubmit Start");

    let reservationId = $(this)
      .closest("tr")
      .find("td[name='reservationId']")
      .text();
    console.log("params ->", reservationId);
    ajax("/Reservations/delete/" + reservationId, "DELETE", reservationId);
  });
});

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
