/**
 * 
 */

var delChk = "번 메뉴를 삭제하시겠습니까?";
var delChk2 = "님의 직원 정보를 삭제하시겠습니까?";
var delChk3 = "의 정보를 삭제하시겠습니까?\n삭제한 정보는 다시 되돌릴 수 없습니다.";
var addChk = " 님을 직원으로 등록하시겠습니까?";
var numChk = "이 입력란은 숫자만 입력 가능합니다.";

// 메뉴 & 직원 삭제 확인
function delCheck(index, chk) {
	if (chk == 'menu') {
		var del = confirm(index + delChk);	// confirm - 대화상자(alert와는 다르게 참 거짓을 리턴함)
		if (del) {
			window.location = 'hostMenuDel?index=' + index;
		}
	} else if (chk == 'employee') {
		var del = confirm(index + delChk2);
		if (del) {
			window.location = 'hostEmployeeDel?id=' + index;
		}
	} else {
		var del = confirm(chk + delChk3);
		if (del) {
			window.location = 'hostRestaurantDel?index=' + index;
		}
	}
}

// 직원 등록 확인
function addCheck(index) {
	var add = confirm(index + addChk);

	if (add) {
		return;
	} else {
		return false;
	}
}

// 숫자만 입력 받기
function onlyNumber(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105)
			|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39) {
		return;
	} else {
		alert(numChk);
		return false;
	}
}

// 숫자가 아닐 시 입력 삭제
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39) {
		return;
	} else {
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
}

var typeNum = '0'; // 선택한 버튼 번호
var typeImg = ''; // 선택한 버튼 이미지

// type에 따라 버튼 이미지 설정
function spaceType(type) {
	typeNum = type; // 선택한 버튼 번호 저장
	switch (typeNum) {
	case '3':
		typeImg = 'table_use.jpg';
		break;
	case '2':
		typeImg = 'icon_enter.png';
		break;
	case '1':
		typeImg = 'table.jpg';
		break;
	case '0':
		typeImg = 'icon_tmp.png';
		break;
	}
}

// 가로, 세로 변할 경우 배열판 설정
function spaceDivChange() {
	var widthX = document.getElementById('widthX').value;
	var heightY = document.getElementById('heightY').value;

	var spaceDiv = document.getElementById('spaceDiv'); // 출력 위치

	var space = '';
	for (var y = 0; y < heightY; y += 1) {
		space += '<div class="p_div">';
		for (var x = 0; x < widthX; x += 1) {
			var location = x + '-' + y;
			var imgId = 'img' + location;
			var btnId = 'btn' + location; // 버튼 구별하기 위한 id

			// 배열판의 각 버튼
			space += '<button class="p_spaceBtn p_btn" ' + 
					'value="0" ' + 'id="' + btnId + '" ' + 
					'onclick="spaceBtnChange(&#39;' + location +'&#39;)">' + 
					'<img class="p_img space_img" ' + 'id="' + imgId + '" ' + 
					'src="/baobob/resources/images/chg/icon_tmp.png">' + '</button>';
			
			spaceDiv.innerHTML = space;
		}
		space += '</div>';
	}
}

// 배열판의 선택한 버튼 설정
function spaceBtnChange(location) {
	if (typeImg != '') { // 아이콘 선택했을 경우, 선택한 버튼의 설정 변경
		// 선택한 버튼의 이미지 src 변경
		var imgId = 'img' + location; // 선택한 버튼의 id
		var spaceImg = document.getElementById(imgId); // 선택한 버튼의 img
		spaceImg.src = '/baobob/resources/images/chg/' + typeImg;

		// 선택한 버튼의 value 변경(DB에 아이콘index 넣기 위한 값)
		var btnId = 'btn' + location;
		var spaceBtn = document.getElementById(btnId);
		spaceBtn.value = typeNum;
	} else {
		alert('아이콘을 선택해주세요.');
	}
}

// 매장 추가(정보 설정 AJAX)
function spaceTypeChange() {
	var item = document.querySelectorAll('.p_spaceBtn'); // 배열판의 버튼들

	// 매장 정보
	var x = document.getElementById('widthX').value; // col
	var y = document.getElementById('heightY').value; // row
	var name = document.getElementById('name').value; // name
	var tel = document.getElementById('tel').value; // tel

	// 배열판 버튼들의 아이콘 index 배열
	var array = new Array();
	item.forEach(function(space) {
		array.push(space.value);
	});
	var info = array.join(',');

	window.location = 'hostRestaurantAddPro?info=' + info + '&col=' + x + '&row=' + y + '&name=' + name + '&tel=' + tel;
}

// 매장 수정(정보 설정 AJAX)
function spaceTypeChange2(index) {
	var item = document.querySelectorAll('.p_spaceBtn'); // 배열판의 버튼들
	
	// 매장 정보
	var x = document.getElementById('widthX').value; // col
	var y = document.getElementById('heightY').value; // row
	
	// 배열판 버튼들의 아이콘 index 배열
	var array = new Array();
	item.forEach(function(space) {
		array.push(space.value);
	});
	var info = array.join(',');
	if (document.getElementById('name') && document.getElementById('tel')) {
		var name = document.getElementById('name').value; // name
		var tel = document.getElementById('tel').value; // tel
		window.location = 'hostRestaurantModPro?info=' + info + '&col=' + x + '&row=' + y + '&name=' + name + '&tel=' + tel + '&index=' + index;
	} else {
		var date = document.getElementById('datepicker').value;
		var time = document.getElementById('timepicker').value;
		window.location = 'hostReservAddPro?info=' + info + '&col=' + x + '&row=' + y + '&index=' + index + '&date=' + date + '&time=' + time;
	}
}

// 설정한 배열판이 있을 경우
function spaceBody(info, col, row) {
	var arr = info.split(',');

	var space = '';
	for (var y = 0; y < row; y += 1) {
		space += '<div class="p_div">';
		for (var x = 0; x < col; x += 1) {
			var index = x + (y * col);
			console.log(index + '번째 : ' + arr[index] + '/{' + x + ',' + y + '}');

			var location = x + '-' + y;
			var imgId = 'img' + location;
			var btnId = 'btn' + location;

			spaceType(arr[index]);

			space += '<button class="p_spaceBtn p_btn" ' + 'value="' + 
					arr[index] + '" ' + 'id="' + btnId + '" ' + 
					'onclick="spaceBtnChange(&#39;' + location + '&#39;)">' + 
					'<img class="p_img space_img" ' + 'id="' + imgId + '" ' + 
					'src="/baobob/resources/images/chg/' + typeImg + '">' + '</button>';
		}
		space += '</div>';
	}
	var spaceDiv = document.getElementById('spaceDiv');
	spaceDiv.innerHTML = space;
	typeImg = '';
}

// 선택된 날짜에 있는 예약 조회
function hostReservList() {
	var date = document.getElementById('datepicker').value;

	window.location = 'hostReservList?date=' + date + '&index=1';

}

// 선택한 식당, 날짜, 시간에 예약이 가능한 테이블 조회
function checkPosRestaurant() {
	var date = document.getElementById('datepicker').value;
	var time = document.getElementById('timepicker').value;

	if (document.getElementById('datepicker').value == "") {
		alert('날짜를 골라주세요!');
		return false;
	} else if (document.getElementById('timepicker').value == "") {
		alert('시간을 골라주세요!');
		return false;
	} else {
		window.location = 'checkPosRestaurant?date=' + date + '&time=' + time + '&index=1';
	}
}

// 선택한 식당, 날짜, 시간에 예약이 가능한 테이블 조회
function checkPosRestaurant2() {
	var date = document.getElementById('datepicker').value;

	if (document.getElementById('datepicker').value == "") {
		alert('날짜를 골라주세요!');
		return false;
	} else {
		window.location = 'checkPosRestaurant2?date=' + date + '&time=' + '&index=1';
	}
}

// 선택한 날짜에 있는 모든 예약 조회
function reservView() {
	var date = document.getElementById("datepicker").value;
	
	if (date == "") {
		alert("날짜를 선택하세요");
		document.getElementById("datepicker").focus();
		return false;
	}

	window.location = "reservView?date=" + date + "&index=1";
}