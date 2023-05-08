<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/order/orderDetail.css">
</head>
<body>

	<div class="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        
        
        <main>

            <div class="order-detail-area">

                <div class="bold order-detail-title">주문상세</div>

                <div class="horizontal-border"></div>

                <div class="project-info">
                    <img src="" alt=프로젝트 홍보 이미지" class="project-info-img">
                    <div class="project-info-text">
                        <div class="project-title bold">프로젝트명</div>
                        <div class="seller-info horizontal-alignment">
                            <div class="bold ">판매자</div>
                            <div class="regular ">freelancer001</div>
                        </div>
                        <div class="date-info horizontal-alignment">
                            <div class="purchase-date horizontal-alignment">
                                <div class="bold ">구매날짜</div>
                                <div class="regular ">2022-12-30</div>
                            </div>
                            <div class="dead-line-date horizontal-alignment">
                                <div class="bold ">판매날짜</div>
                                <div class="regular ">2023-08-16</div>
                            </div>
                            <div class="date-progress horizontal-alignment">
                                <div class="bold ">진행률</div>
                                <div class="regular ">65%</div>
                            </div>
                        </div>
                        <div class="total-payment-amount horizontal-alignment">
                            <div>총 결제 금액</div>
                            <div>₩ 3,300,000</div>
                        </div>
                    </div>
                </div>

                <div class="bold sub-title">최근 메시지</div>
                <div class="recent-message">
                    <img src="" alt="프로필사진" class="message-profile-img">
                    <div class="recent-message-content">

                    </div>
                    <button class="bold message-check-btn">확인하기</button>
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
                            <div class="regular receipt-item">기초적인 프로젝트</div>
                            <div class="regular bold receipt-quantity align-center">1</div>
                            <div class="regular receipt-day align-center">300일</div>
                            <div class="regular receipt-price align-end">₩ 3,000,000</div>
                        </div>
                        <div class="table-body-elem horizontal-alignment">
                            <div class="regular receipt-item">기본적인 옵션</div>
                            <div class="regular bold receipt-quantity align-center">3</div>
                            <div class="regular receipt-day align-center">30일</div>
                            <div class="regular receipt-price align-end">₩ 300,000</div>
                        </div>
                    </div>
                    <div class="receipt-table-footer horizontal-alignment">
                        <div class="total-pay horizontal-alignment">
                            <div class="bold ">총 결제금액</div>
                            <div class="bold ">₩ 3,300,000</div>
                        </div>
                    </div>
                </div>

                <div class="payment-method">
                    <div class="bold ">결제수단</div>
                </div>

                <div class="btn-area horizontal-alignment">
                    <button class="regular back-btn">주문 목록 보기</button>
                </div>

            </div>

        </main>


		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>