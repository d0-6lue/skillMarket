<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/purchase/purchaseCompleted.css">
<script defer src="${root}/static/js/purchase/purchaseCompleted.js"></script>
</head>
<body>

    <div class="wrap">

		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		
		<main>
		
			<div class="purchase-completed-area">

                <div class="regular result-msg">
                    구매가 완료 되었습니다.
                    <br>
                    이용해 주셔서 감사합니다.
                </div>


                <div class="receipt">
                    <div class="receipt-table-head horizontal-alignment">
                        <div class="bold receipt-item">항목</div>
                        <div class="bold receipt-quantity align-center">수량</div>
                        <div class="bold receipt-day align-center">작업일</div>
                        <div class="bold receipt-price align-center">금액</div>
                    </div>
                    <div class="receipt-table-body ">
                        <div class="table-body-elem horizontal-alignment">
                            <div class="regular receipt-item">${quotationVo.estimateTitle }</div>
                            <div class="regular bold receipt-quantity align-center">1</div>
                            <div class="regular receipt-day align-center">${quotationVo.estimatePeriod }</div>
                            <div class="regular receipt-price align-end">${quotationVo.estimatePrice }</div>
                        </div>
                        <c:forEach var="option" items="${quotationOptionList}">
                            <div class="table-body-elem horizontal-alignment">
                                <div class="regular receipt-item">${option.estimateOptionName }</div>
                                <div class="regular bold receipt-quantity align-center">${option.quotationOptionQuantity }</div>
                                <div class="regular receipt-day align-center">${option.estimateOptionPeriod * option.quotationOptionQuantity } 일</div>
                                <div class="regular receipt-price align-end">₩ ${option.estimateOptionPrice * option.quotationOptionQuantity }</div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="receipt-table-footer horizontal-alignment">
                        <div class="total-pay horizontal-alignment">
                            <div class="bold ">총 결제금액</div>
                            <div class="bold ">₩ ${quotationVo.quotationPrice }</div>
                        </div>
                    </div>
                </div>
    
                <div class="payment-method">
                    <div class="bold ">결제수단</div>
                </div>
    
                <div class="btn-area">
                    <a href="${root}/order/detail?no=${quotationVo.quotationNo}">
                        <button class="regular order-detail-btn">주문 상세 보기</button>
                    </a>
                </div>

            </div>

		</main>
		
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
        
    </div>

</body>
</html>