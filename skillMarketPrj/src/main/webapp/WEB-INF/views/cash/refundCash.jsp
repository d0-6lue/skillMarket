<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/cash/refundCash.css">
<script defer src="${root}/static/js/cash/refundCash.js"></script>
</head>
<body>

	<div class="wrap">

		<%@ include file="/WEB-INF/views/common/header.jsp" %>

		<main>

			<div class="refund-title bold">
				캐시 환불하기
			</div>

			<div class="horizontal-border"></div>

			<form action="${root}/cash/refund" method="post" class="refund-form">

				<div class="holding-point-bar">
					<div class="holding-point regular">
						현재 보유중인 캐시
					</div>
					<div class="member-holding-point">
						<span class="point regular">
							${userCash.userCashMoney}
						</span>
						<span class="unit regular">
							sp
						</span>
					</div>
				</div>

				<div class="refund-point-bar">
					<div class="refund-point regular">
						환불 받을 포인트
					</div>
					<div class="input-refund-point">
						<span class="input-number">
							<input name="amount" class="user-input input-number" type="number" value="0" min="0">
						</span>
						<span class="unit regular">
							sp
						</span>
					</div>
				</div>

				<div class="horizontal-border2"></div>

				<div class="result-point-bar">
					<div class="result-point">
						환불 후 포인트
					</div>
					<div class="refund-result-point">
						<div class="result-number">

						</div>
						<div class="unit">
							sp
						</div>
					</div>
				</div>


				<div class="horizontal-border"></div>

				<div class="refund-accound-text bold">
					환불 받을 계좌
				</div>

				<div class="horizontal-border2"></div>

				<div class="refund-account-bar">
					<div class="refund-bank">
						<img class="bank-icon" src="${root}/static/png/bankIcon/${userCash.memberBank}.png" alt="은행 아이콘">
						<span class="bank-name">${userCash.memberBank}</span>
					</div>
					<div class="refund-account-info">
						<span class="account-number">${userCash.memberAccount}</span>
						<span class="account-holder">
							아무개
						</span>
					</div>
				</div>


				<div class="refund-caution">
					<a href="">환불 시 주의사항</a>
				</div>
				<div class="refund-terms">
					<a href="">환불 약관 보기</a>
				</div>

				<button class="refund-btn bold">환불하기</button>

			</form>

		</main>



		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

	</div>

</body>
</html>