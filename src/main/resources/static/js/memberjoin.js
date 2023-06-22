     // submit 유효성검사를 위한 체크
     let idchk = false;
     let idconfirmchk = false;
     let passwordchk = false;
     let passwordconfirmchk = false;
     let namechk = false;
     let agechk = false;
     let phonechk = false;
     let emailchk = false;
     let emailconfirmchk = false;

     // 이메일 인증번호 발송
     $("#emcheck").click("click")
     const emailCofirmFunction = function () {
         $.ajax({
             type: "POST",
             url: "/emailconfirm",
             data: {
                 "email": $("#userEmail").val()
             },
             success: function (data) {
                 alert("해당 이메일로 인증번호 발송이 완료되었습니다.");
                 console.log("data : " + data);
                 emailFunc(data, $("#emailConfirm"), $("#emailConfirmTxt"));
             }
         });
     }


     // 아이디 중복확인,정규식확인
     $("#idcheck").click("keyup", function () {
         // 아이디 정규식
         const regExp = /[a-z]+[a-z0-9]{5,12}/g;

         if (!regExp.test($("#userId").val())) {
             $("#userIdConfirmTxt").html("사용할 수 없는 아이디입니다.");
             $("#userIdConfirmTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             idchk = false;
         } else {

             // 아이디 중복검사
             $.ajax({
                 type: "POST",
                 url: "/userIdCheck",
                 data: { "userId": $("#userId").val() },
                 success: function (data) {
                     if (data == 1) {                 // 중복인 경우
                         $("#userIdConfirmTxt").html("이미 존재하는 아이디입니다.");
                         $("#userIdConfirmTxt").css({
                             "color": "#FF0000",
                             "font-weight": "bold",
                             "font-size": "14px"
                         });
                         idconfirmchk = false;
                     } else {                        // 중복이 아닐 경우
                         $("#userIdConfirmTxt").html("사용 가능한 아이디입니다.");
                         $("#userIdConfirmTxt").css({
                             "color": "#00FF2B",
                             "font-weight": "bold",
                             "font-size": "14px"
                         });
                         idconfirmchk = true;
                     }
                 }
             });
             idchk = true;
         }
     });

     // 비밀번호 정규식
     $("#upw").on("keyup", function () {
         // 비밀번호 정규식 (영문자1개이상 숫자1개이상 특수문자 ~!@#$%^&* 중에 1개이상 8자에서20자까지)
         const regExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*])[a-zA-Z\d~!@#$%^&*]{8,20}$/;
         if ($("#upw").val() != $("#upwcheck").val()) {
             $("#upwcTxt").html("비밀번호가 일치하지 않습니다.");
             $("#upwcTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             passwordconfirmchk = false;
         }
         passwordconfirmchk = false;
         if (!regExp.test($("#upw").val())) {
             $("#upwTxt").html("하나이상의 영문자,숫자,특수문자를 포함해야합니다.");
             $("#upwTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             passwordchk = false;
         } else {
             $("#upwTxt").html("사용가능한 비밀번호입니다.");
             $("#upwTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             passwordchk = true;
         }
     });
     // 비밀번호 재확인 값비교
     $("#upwcheck").on("keyup", function () {
         if ($("#upw").val() != $("#upwcheck").val()) {
             $("#upwcTxt").html("비밀번호가 일치하지 않습니다.");
             $("#upwcTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             passwordconfirmchk = false;
         } else {
             $("#upwcTxt").html("비밀번호가 일치합니다.");
             $("#upwcTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             passwordconfirmchk = true;
         }
     });

     //이름 유효성검사
     $("#uname").on("keyup", function () {
         // 이름 정규식
         const regExp = /^[가-힣]{2,4}$/;
         if (!regExp.test($("#uname").val())) {
             $("#unameTxt").html("형식에 맞게 입력해주세요.");
             $("#unameTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             namechk = false;
         } else {
             $("#unameTxt").html("Good!!")
             $("#unameTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             namechk = true;
         }
     });
     // 생년월일 정규식
     $("#uage").on("keyup", function () {
         // 생년월일 정규식
         const regExp = /^[12][190][0-9]{6}$/;
         if (!regExp.test($("#uage").val())) {
             $("#uageTxt").html("생년월일 형식을 확인해주세요(YYYY.MM.DD)");
             $("#uageTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             agechk = false;
         } else {
             $("#uageTxt").html("Good!!");
             $("#uageTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             agechk = true;
         }
     });
     // 전화번호 정규식
     $("#uphone").on("keyup", function () {
         // 핸드폰 정규식
         const regExp = /[0][1][01]\d{4}\d{4}$/;
         if (!regExp.test($("#uphone").val())) {
             $("#uphoneTxt").html("띄어쓰기나 '-'를 생략한 형식으로 입력해주세요.");
             $("#uphoneTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             phonechk = false;
         } else {
             $("#uphoneTxt").html("Good!!");
             $("#uphoneTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             phonechk = true;
         }
     });
     // 이메일 정규식
     $("#userEmail").on("keyup", function () {
         const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
         if (!regExp.test($("userEmail").val())) {
             $("#eamilTxt").html("이메일 형식이 틀립니다");
             $("#emailTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             emailchk = false;
         } else {
             $("#eamilTxt").html("Good!!");
             $("#eamilTxt").css({
                 "color": "#00FF2B",
                 "font-weight": "bold",
                 "font-size": "14px"
             });
             emailchk = true;
         }
     }
     );

     // 이메일 인증번호 확인 함수
     function emailFunc(data, emailConfirm, emailConfirmTxt) {
         emailConfirm.on("keyup", function () {
             if (data != emailConfirm.val()) {
                 emailConfirmTxt.html("<span id='echeckTxt'>인증번호가 틀렸습니다.</sapn>");
                 $("#echeckTxt").css({
                     "color": "#FF0000",
                     "font-weight": "bold",
                     "font-size": "14px"
                 });
                 emailconfirmchk = false;
             } else {
                 emailConfirmTxt.html("<span id='echeckTxt'>인증번호 일치</sapn>");
                 $("#echeckTxt").css({
                     "color": "#00FF2B",
                     "font-weight": "bold",
                     "font-size": "14px"
                 });
                 emailconfirmchk = true;
             }
         });
     }

     // 이메일 정규식확인 함수

     // submit 유효성검사 함수
     function submitCheck() {
         if (!idchk) {
             $("#submitTxt").html("아이디를 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!idconfirmchk) {
             $("#submitTxt").html("아이디 중복체크를 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!passwordchk) {
             $("#submitTxt").html("비밀번호를 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!passwordconfirmchk) {
             $("#submitTxt").html("비밀번호 재입력을 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!namechk) {
             $("#submitTxt").html("이름을 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!agechk) {
             $("#submitTxt").html("생년월일을 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!phonechk) {
             $("#submitTxt").html("전화번호를 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!emailchk) {
             $("#submitTxt").html("이메일을 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         if (!emailconfirmchk) {
             $("#submitTxt").html("이메일 인증번호를 확인하세요");
             $("#submitTxt").css({
                 "color": "#FF0000",
                 "font-weight": "bold",
                 "font-size": "18px"
             })
             return false;
         }
         $("#submitTxt").html("");

         alert("회원가입이 완료되었습니다!")
     }