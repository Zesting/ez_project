window.onload = function () {
  buildCalendar();
}; // 웹 페이지가 로드되면 buildCalendar 실행

let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화
let today = new Date(); // 페이지를 로드한 날짜를 저장
today.setHours(0, 0, 0, 0); // 비교 편의를 위해 today의 시간을 초기화

let checkInDateInput;
let checkOutDateInput;

let checkInDate = null; // checkIn 날짜를 저장할 변수
let checkOutDate = null; // checkOut 날짜를 저장할 변수

// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
function buildCalendar() {
  checkInDateInput = document.getElementById("checkIn");
  checkOutDateInput = document.getElementById("checkOut");
  let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
  let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0); // 이번달 마지막날

  let tbody_Calendar = document.querySelector("#checkInCalendar > tbody");
  document.getElementById("calYear").innerText = nowMonth.getFullYear(); // 연도 숫자 갱신
  document.getElementById("calMonth").innerText = leftPad(
    nowMonth.getMonth() + 1
  ); // 월 숫자 갱신

  while (tbody_Calendar.rows.length > 0) {
    // 이전 출력결과가 남아있는 경우 초기화
    tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
  }

  let nowRow = tbody_Calendar.insertRow(); // 첫번째 행 추가

  for (let j = 0; j < firstDate.getDay(); j++) {
    // 이번달 1일의 요일만큼
    let nowColumn = nowRow.insertCell(); // 열 추가
  }

  for (
    let nowDay = firstDate;
    nowDay <= lastDate;
    nowDay.setDate(nowDay.getDate() + 1)
  ) {
    // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복

    let nowColumn = nowRow.insertCell(); // 새 열을 추가하고
    /* newDiv.innerHTML = leftPad(nowDay.getDate());
    nowColumn.appendChild(newDiv); */
    nowColumn.innerText = leftPad(nowDay.getDate()); // 추가한 열에 날짜 입력

    if (nowDay.getDay() == 0) {
      // 일요일인 경우 글자색 빨강으로
      nowColumn.style.color = "#DC143C";
    }
    if (nowDay.getDay() == 6) {
      // 토요일인 경우 글자색 파랑으로 하고
      nowColumn.style.color = "#0000CD";
      nowRow = tbody_Calendar.insertRow(); // 새로운 행 추가
    }

    if (nowDay < today) {
      // 지난날인 경우
      nowColumn.className = "pastDay";
    } else if (
      nowDay.getFullYear() == today.getFullYear() &&
      nowDay.getMonth() == today.getMonth() &&
      nowDay.getDate() == today.getDate()
    ) {
      // 오늘인 경우
      nowColumn.className = "today";
      nowColumn.onclick = function () {
        checkDate(this);
      };
    } else {
      // 미래인 경우
      nowColumn.className = "futureDay";
      nowColumn.onclick = function () {
        checkDate(this);
      };
    }
    /* nowColumn.onclick = function () {
      choiceDate(this);
    }; */
  }

  for (let i = 0; i < tbody_Calendar.rows.length; i++) {
    let row = tbody_Calendar.rows[i];
    for (let j = 0; j < row.cells.length; j++) {
      let cell = row.cells[j];
      cell.onclick = function () {
        checkDate(this);
        updateSelectedDates();
      };
    }
  }
}

// 이전 날짜 클릭 검증
function checkLastDay(selectedDate) {
  if (selectedDate.getFullYear() < today.getFullYear()) {
    alert(
      "체크인 & 체크아웃 날짜를 이미 지난 날짜로 선택할 수 없습니다. 다시 선택해 주시길 바랍니다."
    );
    selectedDate = null;
    selectedDate.classList.remove("choiceDay");
    return;
  }
  if (selectedDate.getMonth() < today.getMonth()) {
    alert(
      "체크인 & 체크아웃 날짜를 이미 지난 날짜로 선택할 수 없습니다. 다시 선택해 주시길 바랍니다."
    );
    selectedDate = null;
    selectedDate.classList.remove("choiceDay");
    return;
  }

  if (
    selectedDate.getMonth() <= today.getMonth() &&
    selectedDate.getDate() < today.getDate()
  ) {
    alert(
      "체크인 & 체크아웃 날짜를 이미 지난 날짜로 선택할 수 없습니다. 다시 선택해 주시길 바랍니다."
    );
    selectedDate = null;
    selectedDate.classList.remove("choiceDay");
    return;
  }
}

// checkIn, checkOut 동일 검증
function checkEqualDay(selectedDate1, selectedDate2) {
  if (selectedDate1.getDate() === selectedDate2.getDate()) {
    alert(
      "체크인 날짜와 체크아웃 날짜를 동일하게 선택할 수 없습니다. 다시 선택해주세요."
    );
    selectedDate1 = null;
    selectedDate2 = null;
    selectedDate1.classList.remove("choiceDay");
    selectedDate2.classList.remove("choiceDay");
    return;
  }
}

/* 집 or 학원(목)에서 수정할 것 */
// 3개만 검증 체크인 체크아웃 월이 같을 때,  체크아웃 월이 클 때
function checkMinus(selectedDate1, selectedDate2) {
  if (selectedDate1.getMonth() == selectedDate2.getMonth()) {
    if (selectedDate2.getDate() - selectedDate1.getDate() > 2) {
      /* alert(selectedDate2.getDate() - selectedDate1.getDate()); */
      alert(
        " 숙박 기간을 2일 이상 선택할 수 없습니다.(최대 2일 가능)\n 체크아웃 날짜를 다시 선택해 주세요.\n (체크인 날짜 + 2일까지 선택 가능) \n 3일 이상 숙박을 원하시는 회원은 아래의 번호로 문의부탁드립니다. \n TEL : 032-123-1234"
      );
      selectedDate1 = null;
      selectedDate2 = null;
      selectedDate1.classList.remove("choiceCheckIn");
      selectedDate2.classList.remove("choiceCheckOut");
      return;
    }
  } else if (
    selectedDate1.getMonth() < selectedDate2.getMonth() &&
    selectedDate1.getDate() <= selectedDate2.getDate()
  ) {
    alert("잘못된 날짜 선택입니다. 숙박 기간을 다시 선택해주세요.");
    selectedDate1 = null;
    selectedDate2 = null;
    selectedDate1.classList.remove("choiceCheckIn");
    selectedDate2.classList.remove("choiceCheckOut");
    checkInDate = null;
  } else if (
    selectedDate1.getMonth() < selectedDate2.getMonth() &&
    selectedDate1.getDate() > selectedDate2.getDate()
  ) {
    let totalDate = selectedDate2.getDate() + selectedDate1.getDate();
    if (totalDate - selectedDate1.getDate() > 2) {
      alert(
        " 숙박 기간을 2일 이상 선택할 수 없습니다.(최대 2일 가능)\n 체크아웃 날짜를 다시 선택해 주세요.\n (체크인 날짜 + 2일까지 선택 가능) \n 3일 이상 숙박을 원하시는 회원은 아래의 번호로 문의부탁드립니다. \n TEL : 032-123-1234"
      );
      selectedDate1 = null;
      selectedDate2 = null;
      selectedDate1.classList.remove("choiceCheckIn");
      selectedDate2.classList.remove("choiceCheckOut");

      return;
    }
  }

  /* if (selectedDate2.getDate() <= 2) {
    let totalDate = selectedDate2.getDate() + selectedDate1.getDate();
    if (totalDate - selectedDate1.getDate() > 2) {
      alert(totalDate);
      alert(
        " 숙박 기간을 2일 이상 선택할 수 없습니다.(최대 2일 가능)\n 체크아웃 날짜를 다시 선택해 주세요.\n (체크인 날짜 + 2일까지 선택 가능) \n 3일 이상 숙박을 원하시는 회원은 아래의 번호로 문의부탁드립니다. \n TEL : 032-123-1234"
      );
      selectedDate1 = null;
      selectedDate2 = null;
      selectedDate1.classList.remove("choiceCheckIn");
      selectedDate2.classList.remove("choiceCheckOut");
      return;
    }
  } else if (selectedDate1.getDate() >= 29 && selectedDate2.getDate() <= 2) {
    let totalDate = selectedDate1.getDate() + selectedDate1.getDate();
    alert("else if" + totalDate);
    if (totalDate - selectedDate1.getDate() > 2) {
      alert(totalDate);
      alert(
        " 숙박 기간을 2일 이상 선택할 수 없습니다.(최대 2일 가능)\n 체크아웃 날짜를 다시 선택해 주세요.\n (체크인 날짜 + 2일까지 선택 가능) \n 3일 이상 숙박을 원하시는 회원은 아래의 번호로 문의부탁드립니다. \n TEL : 032-123-1234"
      );
      selectedDate1 = null;
      selectedDate2 = null;
      selectedDate1.classList.remove("choiceCheckIn");
      selectedDate2.classList.remove("choiceCheckOut");
      return;
    }
  } else if (selectedDate1.getDate() < 29 && selectedDate2.getDate() <= 2) {
    if (selectedDate2.getDate() - selectedDate1.getDate() > 2) {
        alert(selectedDate2.getDate() - selectedDate1.getDate()); 
      alert(
        " 숙박 기간을 2일 이상 선택할 수 없습니다.(최대 2일 가능)\n 체크아웃 날짜를 다시 선택해 주세요.\n (체크인 날짜 + 2일까지 선택 가능) \n 3일 이상 숙박을 원하시는 회원은 아래의 번호로 문의부탁드립니다. \n TEL : 032-123-1234"
      );
      selectedDate1 = null;
      selectedDate2 = null;
      selectedDate1.classList.remove("choiceCheckIn");
      selectedDate2.classList.remove("choiceCheckOut");
      return;
    }
  } */
}

function checkDate(checkColumn) {
  // 체크인 날짜만 선택된 경우,
  if (checkInDate === null) {
    let selectCheckIn = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      checkColumn.innerText
    );
    checkLastDay(selectCheckIn);
    checkInDate = selectCheckIn;
    checkColumn.classList.add("choiceCheckIn");
  } else if (checkInDate === checkColumn) {
    alert(
      "체크인 날짜가 중복 선택되었습니다. 체크인 날짜를 다시 선택해 주세요."
    );
    checkInDate = null;
    checkColumn.classList.remove("choiceCheckIn");
  } else if (checkInDate !== checkColumn && checkOutDate === null) {
    const selectCheckOut = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      checkColumn.innerText
    );
    checkLastDay(selectCheckOut);
    checkEqualDay(checkInDate, selectCheckOut);
    checkMinus(checkInDate, selectCheckOut);

    if (selectCheckOut <= 2) {
    }

    if (selectCheckOut < checkInDate) {
      alert(
        "체크 아웃 날짜를 체크 인 날짜 이전으로 선택할 수 없습니다. \n 새로고침 이후 다시 체크인을 선택해 주시길 바랍니다."
      );
      checkInDate = null;
      checkOutDate = null;
      let checkInCells = document.querySelectorAll(".choiceCheckIn");
      let checkOutCells = document.querySelectorAll(".choiceCheckOut");

      checkInCells.forEach(cell => {
        cell.classList.remove("choiceCheckIn");
      });

      checkOutCells.forEach(cell => {
        cell.classList.remove("choiceCheckOut");
      });
    } else {
      checkOutDate = selectCheckOut;
      checkColumn.classList.add("choiceCheckOut");
    }
  } else if (checkColumn === checkOutDate) {
    alert(
      "체크아웃 날짜가 중복 선택되었습니다. 체크인 날짜를 다시 선택해 주세요."
    );
    checkOutDate = null;
    checkColumn.classList.remove("choiceCheckOut");
  } else {
    checkInDate = null;
    checkOutDate = null;
    let checkInCells = document.querySelectorAll(".choiceCheckIn");
    let checkOutCells = document.querySelectorAll(".choiceCheckOut");

    checkInCells.forEach(cell => {
      cell.classList.remove("choiceCheckIn");
    });

    checkOutCells.forEach(cell => {
      cell.classList.remove("choiceCheckOut");
    });
    // 현재 선택된 날짜에 "choiceDay" class 추가
    let selectedDate = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      checkColumn.innerText
    );
    checkLastDay(selectedDate);
    checkInDate = selectedDate;
    checkColumn.classList.add("choiceCheckIn");
  }
  updateSelectedDates();
}

/* // 날짜 선택
function choiceDate(nowColumn) {
  if (checkInDate === null) {
    // checkIn 날짜 선택
    let selectedDate = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      nowColumn.innerText
    );
    checkLastDay(selectedDate);
    checkInDate = selectedDate;
    nowColumn.classList.add("choiceCheckIn"); // 선택된 날짜에 "choiceCheckIn" class 추가
  } else if (checkOutDate === null && nowColumn !== checkInDate) {
    // checkOut 날짜 선택
    const selectedDate = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      nowColumn.innerText
    );
    checkLastDay(selectedDate);
    checkEqualDay(checkInDate, selectedDate);
    checkMinus(checkInDate, selectedDate);
    checkMinus(selectedDate, checkInDate);

    if (selectedDate < checkInDate) {
      // 선택된 날짜가 checkInDate보다 이전인 경우
      checkOutDate = checkInDate; // checkOutDate에 checkInDate 저장
      checkInDate = selectedDate;
    } else {
      // 선택된 날짜가 checkInDate보다 이후인 경우
      checkOutDate = selectedDate;
    }

    nowColumn.classList.add("choiceCheckOut"); // 선택된 날짜에 "choiceDay" class 추가
  } else if (nowColumn === checkOutDate) {
    // 이미 선택된 checkOut 날짜를 다시 클릭한 경우, 선택 해제
    checkOutDate = null;
    nowColumn.classList.remove("choiceCheckOut"); // 선택 해제된 날짜에서 "choiceDay" class 제거
  } else {
    // 이미 checkIn과 checkOut이 선택되어 있을 경우, 모든 날짜 초기화
    checkInDate = null;
    checkOutDate = null;
    let checkInCells = document.querySelectorAll(".choiceCheckIn");
    let checkOutCells = document.querySelectorAll(".choiceCheckOut");

    checkInCells.forEach(cell => {
      cell.classList.remove("choiceCheckIn");
    });

    checkOutCells.forEach(cell => {
      cell.classList.remove("choiceCheckOut");
    });
    // 현재 선택된 날짜에 "choiceDay" class 추가
    let selectedDate = new Date(
      nowMonth.getFullYear(),
      nowMonth.getMonth(),
      nowColumn.innerText
    );
    checkLastDay(selectedDate);
    checkInDate = selectedDate;
    nowColumn.classList.add("choiceCheckIn");
  }

  updateSelectedDates();
} */

// 선택된 checkIn과 checkOut 날짜 표시 업데이트
function updateSelectedDates() {
  if (checkInDate !== null) {
    checkInDateInput.value = formatDate(checkInDate);
  } else {
    checkInDateInput.value = "";
  }
  if (checkOutDate !== null) {
    checkOutDateInput.value = formatDate(checkOutDate);
  } else {
    checkOutDateInput.value = "";
  }
}

// 날짜 형식 포맷팅 (yyyy-mm-dd)
function formatDate(date) {
  let year = date.getFullYear();
  let month = String(date.getMonth() + 1).padStart(2, "0");
  let day = String(date.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
}

// 이전달 버튼 클릭
function prevCalendar() {
  nowMonth = new Date(
    nowMonth.getFullYear(),
    nowMonth.getMonth() - 1,
    nowMonth.getDate()
  ); // 현재 달을 1 감소
  buildCalendar(); // 달력 다시 생성
}
// 다음달 버튼 클릭
function nextCalendar() {
  nowMonth = new Date(
    nowMonth.getFullYear(),
    nowMonth.getMonth() + 1,
    nowMonth.getDate()
  ); // 현재 달을 1 증가
  buildCalendar(); // 달력 다시 생성
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
  if (value < 10) {
    value = "0" + value;
    return value;
  }
  return value;
}
