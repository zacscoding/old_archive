<!-- bootstrap source by : http://bootsnipp.com/snippets/featured/portfolio-gallery-with-filtering-category -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<label class="control-label">Select File</label>
<input id="input-4" name="input4[]" type="file" multiple class="file-loading">


<script>
$(document).on('ready', function() {
    $("#input-4").fileinput({showCaption: false});
});
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>