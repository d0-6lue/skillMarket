<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/order/orderDetail.css">
<script defer src="${root}/static/js/order/orderDetail.js"></script>
</head>
<body>

	<div class="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        
        <main>
        
        <script type="text/javascript">
        
        </script>

            <div class="order-detail-area">

                <div class="bold order-detail-title">주문상세</div>

                <div class="horizontal-border"></div>

                <div class="project-info">
                    ${quotationVo.estimateThumbnail}
                    <!-- <img src="${root}/static/img/thumbnail/${quotationVo.estimateThumbnail}" alt=프로젝트 홍보 이미지" class="project-info-img"> -->
                    <div class="project-info-text">
                        <div class="project-title bold">${quotationVo.estimateTitle }</div>
                        <div class="seller-info horizontal-alignment">
                            <div class="bold ">판매자</div>
                            <div class="regular ">${quotationVo.memberNo }</div>
                            <script>
					            const sellerNick = '${quotationVo.memberNo}';
					        </script>
                        </div>
                        <div class="date-info horizontal-alignment">
                            <div class="purchase-date horizontal-alignment">
                                <div class="bold ">구매날짜</div>
                                <div class="regular start-day">${quotationVo.quotationEnrollDate }</div>
                            </div>
                            <div class="dead-line-date horizontal-alignment">
                                <div class="bold ">마감날짜</div>
                                <div class="regular deadline"></div>
                                
                                <c:set var="startDay" value="${quotationVo.quotationEnrollDate }" scope="session" />
			                	<script>
			                        let startDay = '<%=(String)session.getAttribute("startDay")%>';
			                        
	                                startDay = new Date(startDay);
	                                deadlineTime = startDay.getTime() + ( '${quotationVo.quotationPeriod}' * 24 * 60 * 60 * 1000 );
	                                deadline = new Date(deadlineTime);
	
	                                deadlineStr = deadline.getFullYear() + "-" + (deadline.getMonth() + 1) + "-" + deadline.getDate();
	                                
	                                const deadlineDiv = document.querySelector(".deadline");
	                                deadlineDiv.innerText = deadlineStr;
                                </script>
                                
                            </div>
                            <div class="date-progress horizontal-alignment">
                                <div class="bold ">진행률</div>
                                <div class="regular progress-rate"></div>
                            </div>
                        </div>
                        <div class="total-payment-amount horizontal-alignment">
                            <div>총 결제 금액</div>
                            <div>${quotationVo.quotationPrice}</div>
                            
                            <c:set var="price" value="${quotationVo.quotationPrice }" scope="session" />
		                	<script>
		                        var price = '<%=(String)session.getAttribute("price")%>';
		                        
		                        price = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
                                
                                document.querySelector(".total-payment-amount").innerText = "₩ " + price;
                            </script>
                            
                        </div>
                    </div>
                </div>

                <div class="bold sub-title">최근 메시지</div>
                <div class="recent-message">
                    <img src="${root}/static/img/profile/${quotationVo.memberProfile}" alt="프로필사진" class="message-profile-img">
                    <div class="recent-message-area">
                        <div class="recent-message-content">${lastChatContent}</div>
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
                            <div class="regular receipt-item">${quotationVo.estimateTitle }</div>
                            <div class="regular bold receipt-quantity align-center">1</div>
                            <div class="regular receipt-day align-center">${quotationVo.estimatePeriod }일</div>
                            <div class="regular receipt-price receipt-project-price align-end"></div>
                            
                            <c:set var="projectPrice" value="${quotationVo.estimatePrice }" scope="session" />
		                	<script>
		                        var projectPrice = '<%=(String)session.getAttribute("projectPrice")%>';
		                        
                                projectPrice = projectPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
                                
                                document.querySelector(".receipt-project-price").innerText = "₩ " + projectPrice;
                            </script>
                                
                        </div>
                        
                        <c:forEach var="option" items="${optionVoList }">
                        	<div class="table-body-elem horizontal-alignment">
	                        	<div class="regular receipt-item">${option.estimateOptionName }</div>
	                            <div class="regular bold receipt-quantity align-center">${option.quotationOptionQuantity }</div>
	                            <div class="regular receipt-day align-center">${option.estimateOptionPeriod * option.quotationOptionQuantity }일</div>
	                            <div class="regular receipt-price align-end">₩ ${option.estimateOptionPrice * option.quotationOptionQuantity }</div>
	                        </div>
                        </c:forEach>
                        
                    </div>
                    <div class="receipt-table-footer horizontal-alignment">
                        <div class="total-pay horizontal-alignment">
                            <div class="bold ">총 결제금액</div>
                            <div class="bold total-price ">₩ 3,300,000</div>
                            <c:set var="price" value="${quotationVo.quotationPrice }" scope="session" />
		                	<script>
		                        var price = '<%=(String)session.getAttribute("price")%>';
		                        
		                        price = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
                                
                                document.querySelector(".total-price").innerText = "₩ " + price;
                            </script>
                        </div>
                    </div>
                </div>

                <div class="payment-method">
                    <div class="bold ">결제수단</div>
                </div>

                <div class="btn-area">
                    <button class="regular back-btn">주문 목록 보기</button>
                </div>

            </div>

        </main>


		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>