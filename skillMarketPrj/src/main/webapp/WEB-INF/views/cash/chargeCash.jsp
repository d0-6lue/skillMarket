<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/cash/charge.css">
<script defer src="${root}/static/js/cash/charge.js"></script>
</head>
<body>

    <div class="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>

        <main>

            <div class="charge-title bold">
                캐치 충전하기
            </div>
            
            <form action="${root}/cash/charge" method="post" class="charge-form">
            <div class="horizontal-border"></div>
            
            <div class="holiding-point-bar">

                <div class="holidng-point-title regular">
                    보유중인 포인트
                </div>

                <div class="holding-point-unit">
                    <div class="holding-point regular">
                        ${userCash.userCashMoney}
                    </div>
                    <div class="unit regular">
                        sp
                    </div>
                </div>

            </div>

            <div class="charge-point-bar">

                <div class="charge-point-title regular">
                    충전할 포인트
                </div>

                <div class="charge-point-unit">
                    <input type="num" name="amount" class="charge-point regular"></input>
                    <div class="unit regular">
                        sp
                    </div>
                </div>

            </div>

            <div class="horizontal-border"></div>

            <div class="result-point-bar">

                <div class="result-point-title regular">
                    충전 후 포인트
                </div>

                <div class="result-point-unit">
                    <div class="result-point regular">

                    </div>
                    <div class="unit regular">
                        sp
                    </div>
                </div>

            </div>
            

            <div class="purchase-method-title">
                결제수단
            </div>

            <div class="method-radio-div">
                <input type="radio" name="purchase-method-radio" id="credit-card" value="1" checked>
                <input type="radio" name="purchase-method-radio" id="account-transfer" value="2">
                <input type="radio" name="purchase-method-radio" id="mobile-phone-payment" value="3">
            </div>

            <div class="purchase-method-head">

                <div class="credit-card-box">
                    <label for="credit-card" class="purchase-method-elem elem-active regular">신용카드</label>
                </div>
                <div class="account-transfer-box">
                    <label for="account-transfer" class="purchase-method-elem regular">계좌이체</label>
                </div>
                <div class="mobile-phone-payment">
                    <label for="mobile-phone-payment" class="purchase-method-elem regular">휴대폰 결제</label>
                </div>

            </div>

            <div class="purchase-method-body">

                <div class="purchase-detail-area"></div>

            </div>

            <button class="charge-btn bold">충전하기</button>

            </form>

        </main>

        <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>