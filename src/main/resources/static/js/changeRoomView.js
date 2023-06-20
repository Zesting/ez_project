$(document).on("click", "#changeMercuryBtn", function (event) {
  console.log("click event 시작");
  event.preventDefault();
  ajax("/Rooms/room_MercuryView");
});

$(document).on("click", "#changeMarsBtn", function (event) {
  console.log("click event 시작");
  event.preventDefault();
  ajax("/Rooms/room_MarsView");
});

$(document).on("click", "#changeEarthBtn", function (event) {
  console.log("click event 시작");
  event.preventDefault();
  ajax("/Rooms/room_EarthView");
});

$(document).on("click", "#changeJupiterBtn", function (event) {
  console.log("click event 시작");
  event.preventDefault();
  ajax("/Rooms/room_JupiterView");
});

function ajax(url) {
  console.log("GET Ajax", url);
  $.ajax({
    url: url,
    method: "GET",
    dataType: "HTML",
    success: function (data) {
      $("#customSection").html(data);
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
}
