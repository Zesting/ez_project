// $(document).ready(function() {
//     // 클릭 이벤트 처리
//     $('a').click(function(event) {
//       event.preventDefault(); // 기본 동작 방지
  
//       // 클릭한 링크의 href 속성값을 가져옴
//       var url = $(this).attr('href');
  
//       // AJAX 요청 생성
//       $.ajax({
//         url: url,
//         type: 'GET',
//         success: function(response) {
//           // 요청 성공 시 화면 업데이트
//           $('#roomListContainer').html(response);
//         },
//         error: function() {
//           // 요청 실패 시 에러 처리
//           console.error('AJAX 요청 실패');
//         }
//       });
//     });
//   });