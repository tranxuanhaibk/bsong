<%@page import="models.Song"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Chỉnh sửa bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	
        	Song song = (Song) request.getAttribute("song");
        	String name = request.getParameter("name");
        	String catId = request.getParameter("cat");
        	String previewText = request.getParameter("description");
        	String detailText = request.getParameter("detail");
        	String picture = "";
        	if (song != null){
        		name = song.getName();
        		catId = String.valueOf(song.getCat().getId());
        		previewText = song.getDescription();
        		detailText = song.getDetail();
        		picture = song.getPicture();	
        		
        	}
         	
        %>
     
       
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="" role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Sửa bài hát</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="cat_id" class="form-control">
	                                        <%
	                                        //int idCat = 0;
	                                        if (request.getAttribute("cat") != null){
	                                        	//idCat = (Integer) request.getAttribute("idCat");
	                                        	List<Category> catList = (List<Category>) request.getAttribute("cat");
	                                        	if (catList.size() > 0){
	                                        	for(Category cat : catList){
	                                        %>
											<option value="<%=cat.getId()%>"><%=cat.getName() %></option>
											<%}}} %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                        <% if (!picture.isEmpty()){ %>
                                        <br />
                                        <img width="200px" height="200px" alt="Anh" src="<%=request.getContextPath()%>/uploads/<%=picture%>" />
                                        <%} %>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%if (previewText != null) out.print(previewText);%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%if (detailText != null) out.print(detailText);%></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>