window.onload = function() {
   
	const tbody =  document.querySelectorAll("tbody tr");

	tbody.forEach((e)=>{
		e.addEventListener("click",(event)=>{
			const bno = event.target.parentNode.children[0].innerText;
			console.log(bno);
	
			const noticeDetail = document.querySelectorAll(".noticeDetail");
			
			const selectModal =  document.getElementById("noticeDetail_"+ bno);

			

			$(selectModal).css("display", "block"); // 모달창을 보이게 함
			$("#noticeDetailContent_" + bno).innerHTML = "${ modal.notiContent }";
					
			

			// 모달창 닫기 버튼 클릭 시
			$(".close").click(function() {
				$("#noticeDetail_"+ bno).css("display", "none"); // 모달창을 숨김
			});
			
			
		} )
		
	})



















    
}