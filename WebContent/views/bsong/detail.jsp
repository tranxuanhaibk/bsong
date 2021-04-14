<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/bsong/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  	Song song = (Song) request.getAttribute("song");
  	if (song != null){
  %>
    <div class="article">
      <h1><%=song.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDateCreate() %>. Lượt xem: <%=song.getCounter()%></p>
      <a href=""><img src="<%=request.getContextPath() %>/uploads/<%=song.getPicture() %>" width="450" height="300" alt="" class="userpic" /></a>
      <div class="vnecontent">
       <%=song.getDescription() %>
       <h3> --Lời bài hát--- </h3>
       <%=song.getDetail() %>
      </div>
    </div>
    <%} %>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      @SuppressWarnings("uncheck")
      List<Song> relatedSongs = (List<Song>) request.getAttribute("relatedSongs");
      if (relatedSongs != null && relatedSongs.size() > 0){
    	  for (Song item : relatedSongs){
    		  String urlDetails = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
      %>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath() %>/uploads/<%=item.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%=urlDetails%>"><%=item.getName() %></a></h2>
        <p><%=item.getDescription() %></p>
        
      </div>
      <%}} else { %>
      <div class="article">
     Chưa có bài hát liên quan !
     </div>
     <%} %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/bsong/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/bsong/inc/footer.jsp" %>
