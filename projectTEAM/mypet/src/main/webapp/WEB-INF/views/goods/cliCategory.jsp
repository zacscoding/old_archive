<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<td align="center" width="15%" valign="top" rowspan="8">
		
		<ul class="nav navbar-nav nav-main">
		<c:forEach items="${categoryList}" var="animalVO">
			<li><font size=4><b>${animalVO.animal_name }</b></font></li><br><br>
			<c:forEach items="${animalVO.cateList }" var="categoryVO">
				<li><a href="/goods/cliProductList?cateType=${categoryVO.category_no }">${categoryVO.cate_name }</a></li><br><br>
			</c:forEach>
			<br>
			
		</c:forEach>
		</ul>
		</td>