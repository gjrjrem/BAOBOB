/**
 * 
 */

var typeNum = '0';	//선택한 버튼 번호 영화좌석state
var typeImg = '';	//선택한 버튼 이미지 영화 좌석 state이미지
var seatRow = ["A","B","C","D","E","F","G","H","I","J","k","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];


//type에 따라 버튼 이미지 설정
function spaceType(type) {
	typeNum = type;	//선택한 버튼 번호 저장
	switch(typeNum) {
	case '5': typeImg = 'theater_couple.png'; break;
	case '4': typeImg = 'theater_premium.png'; break;
	case '3': typeImg = 'theater_normal.png'; break;
	case '2': typeImg = 'theater_out.png'; break;
	case '1': typeImg = 'theater_in.png'; break;
	case '0': typeImg = 'theater_blank.png'; break;
	}
}

// 상영관상세 선택시 좌석정보 가져오기
function getSeatInfo(col, row, state){
	var seat = "<input type='button' style='background-color:lightgray; width:"+col*20+"px;' value='SCREEN'> <br><br>";
	for(var i = 0; i<row; i++){
		for(var j = 1; j<=col; j++){
			
			
			seat += "<button class='t_spaceBtn' type='button' style='margin:1px 1px; height:20; width:20;' id='"+seatRow[i]+j+"' name='seat"+seatRow[i]+j+"' value='"+state[(i)*col-1+j]+"'>" +
						"<img id='img"+seatRow[i]+j+"' style='width:20; height:20;' src='/baobob/resources/images/phc/icon/theater_blank.png' onclick=\"seatChange('"+seatRow[i]+j+"')\" >" +
					"</button>";
			
		}
		
		seat += "<br>";
	}
	var theaterSeat = document.getElementById("theaterSeat");
	theaterSeat.innerHTML = seat;
	
	for(var i = 0; i<row; i++){
		for(var j = 1; j<=col; j++){
			var stateImg = '';
			
			switch(document.getElementById(seatRow[i]+j).value) {
			case '5': stateImg = 'theater_couple.png'; break;
			case '4': stateImg = 'theater_premium.png'; break;
			case '3': stateImg = 'theater_normal.png'; break;
			case '2': stateImg = 'theater_out.png'; break;
			case '1': stateImg = 'theater_in.png'; break;
			case '0': stateImg = 'theater_blank.png'; break;
			}
			
			var spaceImg = document.getElementById("img"+seatRow[i]+j);
			spaceImg.src = "/baobob/resources/images/phc/icon/" + stateImg;
			
		}
	}
}

// 상영관 좌석 생성
function seatSet(){
	var row = document.hostTheaterAddForm.theater_row.value;
	var col = document.hostTheaterAddForm.theater_col.value;
//		alert("행 : " + row + "칸 , 열 : " + col + "칸" );
	var seat = "<input type='button' style='background-color:lightgray; width:"+col*20+"px;' value='SCREEN'> <br><br>";
	
	for(var i = 0; i<row; i++){
		for(var j = 1; j<=col; j++){
			seat += "<button class='t_spaceBtn' type='button' style='margin:1px 1px; height:20; width:20;' id='"+seatRow[i]+j+"' name='seat"+seatRow[i]+j+"' value='0'>" +
						"<img id='img"+seatRow[i]+j+"' style='width:20; height:20;' src='/baobob/resources/images/phc/icon/theater_blank.png' onclick=\"seatChange('"+seatRow[i]+j+"')\" >" +
					"</button>";
		}
		seat += "<br>";
	}
	
	var theaterSet = document.getElementById("theaterSet");
	theaterSet.innerHTML = seat;
}

// 상영관 좌석 state 변경
function seatChange(seat){
	var seatChange = document.getElementById(seat);
	if(typeImg != '') { //아이콘 선택했을 경우, 선택한 버튼의 설정 변경
		//선택한 버튼의 이미지 src 변경
		var spaceImg = document.getElementById("img"+seat); //선택한 버튼의 img
		spaceImg.src = "/baobob/resources/images/phc/icon/" + typeImg;
		document.getElementById(seat).value=typeNum;
		

	} else {
		alert('아이콘을 선택해주세요.');
	}
	
}

// 상영관 생성 DB
function hostTheaterAdd(){
	var item = document.querySelectorAll('.t_spaceBtn'); //배열판의 버튼들
	var theater_index = document.getElementById('theater_index').value;
	var col = document.getElementById('col').value;
	var row = document.getElementById('row').value;

	var array = new Array();
	item.forEach(function(space) {
		array.push(space.value);
	});
	
	var state = array.join(',');
	alert("state : " + state);
	
	var param = 'theater_index=' + theater_index + '&'
				+ 'col=' + col + '&'
				+ 'row=' + row + '&'
				+ 'state=' + state;
				
	window.location="hostTheaterAddPro?"+param;
}


// 상영관 수정 DB
function hostTheaterMod(theater_index, col, row){
	var item = document.querySelectorAll('.t_spaceBtn'); //배열판의 버튼들

	var array = new Array();
	item.forEach(function(space) {
		array.push(space.value);
	});
	
	var state = array.join(',');
	alert("state : " + state);
	
	var param = 'theater_index=' + theater_index + '&'
				+ 'col=' + col + '&'
				+ 'row=' + row + '&'
				+ 'state=' + state;
				
	window.location="hostTheaterModPro?"+param;
}

// 선택한 스케줄 시간에 따른 상영 가능한 상영관 찾기
function checkPosTheater(){
	var date = document.getElementById("datepicker").value;
	var time = document.getElementById("timepicker").value;
	
	window.location="checkPosTheater?schedule_startDate="+date+"&schedule_startTime="+time;
}
