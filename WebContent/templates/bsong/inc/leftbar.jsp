<%@page import="utils.StringUtil"%>
<%@page import="models.Song"%>
<%@page import="dao.SongDAO"%>
<%@page import="java.util.List"%>
<%@page import="dao.CatDAO"%>
<%@page import="models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="get" action="<%=request.getContextPath() %>/home">
    <span>
    <input class="editbox_search" value="<%if(request.getParameter("sname") != null) out.print(request.getParameter("sname")); %>" name="sname" id="editbox_search" maxlength="80" placeholder="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/bsong/images/search.jpg" class="button_search" type="image" />
    <select name="scat" style="margin-top:20px;"/>
    <option value="0">-Chọn danh mục-</option>
     <%
    int scat = 0;
    if(request.getAttribute("scat") != null){
		scat = (Integer) request.getAttribute("scat");
	}
    CatDAO catDao = new CatDAO();
   	List<Category> cat1 = catDao.findAll();
    if (cat1 != null){
    	if (cat1.size() > 0){
    		for (Category item : cat1){
     %>
    	<option <%if(scat == item.getId()) out.print("selected = 'selected'"); %> value="<%=item.getId() %>"><%=item.getName() %></option>
    <%
    			}
    		}
    	} 
    %>
    </select>
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  	if (cat1.size() > 0){
  		for (Category item : cat1){
  			String urlNews = request.getContextPath()+"/danh-muc/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
  %>
    <li><a href="<%=urlNews%>"><%=item.getName() %></a></li>
  <%}} %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  <%
  	SongDAO songDao = new SongDAO();
  	List<Song> recentSongs = songDao.findAll(6);
  	if (recentSongs.size() > 0){
  		for (Song item : recentSongs){
  			String urlDetails = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
  %>
   		 <li><a href="<%=urlDetails%>"><%=item.getName() %></a><br />
     	 <%if (item.getDescription().length() > 50) out.print(item.getDescription().substring(0, 50)); else out.print(item.getDescription()); %></li>
  
  <%}} %>
  </ul>
</div>