$(function(){
	
	var $non = $(".non");
	var $checkHead = $(".hchk1"); // thead에서 체크박스인 input을 찾는다.
	var $checkBody = $(".seat1"); // tbody에서 체크박스인 input을 찾는다.

	
	/* 전체선택 */
	$checkHead.click(function(){
		var $bodyPutCk = $checkHead.is(":checked");

		if ( $bodyPutCk == true ) {
			//$lastActive.addClass("active");
			$checkBody.attr("checked", true);
			$checkBody.prop("checked", true);
		}else {
			//$lastActive.removeClass("active");
			$checkBody.attr("checked", false);
			$checkBody.prop("checked", false);
		}
	});

	/* 하위 전체 선택시 전체버튼 선택 */
	$checkBody.click(function(){
		var tdInput_Length = $checkBody.length; // td 에 있는 input 갯수
		var tdInput_Check_Length = $("#checkBoard tbody td input[type='checkbox']:checked").length; // td 에 있는 선택된 input 갯수

		console.log(tdInput_Length);
		console.log(tdInput_Check_Length);

		if ( tdInput_Length == tdInput_Check_Length ) {
			//$checkHead.addClass("active");
			$checkHead.attr("checked", true);
			$checkHead.prop("checked", true);
		}else {
			//$checkHead.removeClass("active");
			$checkHead.attr("checked", false);
			$checkHead.prop("checked", false);
		}
	});
	
	
	var $non = $(".non");
	

})