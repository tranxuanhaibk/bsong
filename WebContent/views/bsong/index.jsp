<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/bsong/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
	List<Song> songs = (List<Song>) request.getAttribute("songList");
	if (songs != null && songs.size() > 0){
		int i = 0;
		for (Song item : songs){
  			i++;
  			String urlDetails = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
  %>
    <div class="article">
      <h2><a href="<%=urlDetails %>" title="<%=item.getName() %>"><%=item.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=item.getDateCreate() %>. Lượt xem: <%=item.getCounter() %> <a href="#" class="com"><span><%=item.getId() %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/uploads/<%=item.getPicture() %>" width="350" height="200" alt="<%=item.getName() %>" class="fl" /></div>
      <div class="post_content">
        <p><%=item.getDescription() %></p>
        <p class="spec"><a href="<%=urlDetails %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
<%}} else { %>
<div class="article">
Chưa có bài viết nào !
</div>
<%} %>
<%
     int numberOfPages = (Integer) request.getAttribute("numberOfPages");
    
     int currentPage = (Integer) request.getAttribute("currentPage");
     if (songs != null && songs.size() > 0){
%>
    <p class="pages">
    <% 
    	if (currentPage != 1) {
    %>
    <a href="<%=request.getContextPath()%>/trang-chu/page-<%=currentPage - 1%>" >&laquo;</a>
   	<%} %>
    <%
    for (int i = 1; i <= numberOfPages; i++){
    	if (currentPage == i){
    %>
    <small>Trang <%=i %> của <%=numberOfPages %></small>
    <span><%=i %></span>
    <%} else { %>
    <a href="<%=request.getContextPath()%>/trang-chu/page-<%=i %>"><%=i %></a>
    <%}}%>
    <% 
    	if (currentPage != numberOfPages) {
    %>
    <a href="<%=request.getContextPath()%>/trang-chu/page-<%=currentPage + 1 %>" aria-label="Tiếp theo">&raquo;</a></p>
    <%}} %>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/bsong/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
document.getElementById("home").classList.add('active-menu');
</script>
<%@ include file="/templates/bsong/inc/footer.jsp" %>
