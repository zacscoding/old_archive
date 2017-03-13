<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<br/><br/><br/><br/>

<link rel="stylesheet" type="text/css" href="../style.css">

</script>
<tr>
	<td colspan="5" align="center">
		<table class="outline" width="99%">
		<caption align="top">${orderVO.order_date}</caption>
			<tr bgcolor="Yellow">
				<th>번호</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>결제금액</th>
				<th>결제일</th>
			</tr>
			<c:forEach items="${orderList}" var="orderVO" varStatus="status">
				<td>${status.index+1}</td>
				<td>${orderVO.product_no_fk}</td>
				<td><a href='/admin/goods/productRead${pageMaker.makeSearch(pageMaker.cri.page)
					}&product_no=${productVO.product_no }'>${productVO.product_name }</a></td>
				<td>${orderVO.price}</td>
				<td>${orderVO.totalPrice}$</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${orderVO.order_date}" /></td>
				<td>
			</tr>
			</c:forEach>
		</table>
	</td>
</tr>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>