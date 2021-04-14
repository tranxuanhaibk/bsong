<%@page import="utils.DefineUtil"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="models.Category"%>
<%@page import="constants.CommonConstant"%>
<%@page import="models.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/song/index">
                                    <input value="<% if(request.getParameter("sname") != null) out.print(request.getParameter("sname")); %>"type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" name="sname" style="float:right; width: 300px;" />
                                    	<select name="scat" class="btn" style="float:right; margin-right:20px;"/>
                                    		<option value="0">-Chọn danh mục-</option>
                                    	<%
                                    		int scat = 0;
                                    		if(request.getAttribute("scat") != null){
                                    			scat = (Integer) request.getAttribute("scat");
                                    		}
                                    		if (request.getAttribute("listCat") != null){
                                    			List<Category> listCat = (List<Category>) request.getAttribute("listCat");
                                    			if (listCat.size() > 0){
                                    				for (Category objCat : listCat){
                                    	%>
                                    		<option <% if(scat == objCat.getId()) out.print("selected = 'selected'");%> value="<%=objCat.getId()%>"><%=objCat.getName()%></option>
                                    	<%
                                    				}
                                    			}
                                    		}
                                    
                                    	%>
                                    	</select>
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
							if (request.getParameter("msg") != null) {
								String msg= request.getParameter("msg");
								if (msg.equals(CommonConstant.SUCCESS)){
									out.print("Xử lý thành công");
								} else{ 
									out.print("Có lỗi trong qúa trình xử lý");
								}
							}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th width="400px">Hình ảnh</th>
                                        <th width="158px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                 if (request.getAttribute("songList") != null){
                                	 List<Song> songList = (List<Song>) request.getAttribute("songList");
                                	 if (songList.size() > 0){
                                		 for (Song song : songList){
                                			 int id = song.getId();
                                			 String name = song.getName();
                                			 String catName = song.getCat().getName();
                                			 int counter = song.getCounter();
                                			 String urlDel = request.getContextPath()+"/admin/song/del?id="+id;
                                 			 String urlEdit = request.getContextPath()+"/admin/song/edit?id="+id;
                                 			 
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=catName %></td>
                                        <td class="center"><%=counter %></td>
                                        
                                        <td class="center">
                                        <%
                                        	if (!"".equals(song.getPicture())){
										%>
											<img width="350px" height="200px" src="<%=request.getContextPath() %>/uploads/<%=song.getPicture() %>" alt="<%=song.getName()%>"/>
                                       
                                        <%}else { %>
                                        <strong> Không có hình ảnh </strong>
                                        <%} %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%}}} %>
                                </tbody>
                            </table>
                            <%
                            List<Song> songList = (List<Song>) request.getAttribute("songList");
                            int numberOfPages =  (Integer) request.getAttribute("numberOfPages");
                            int numberOfSongs =  (Integer) request.getAttribute("numberOfSongs");
                            int currentPage =  (Integer) request.getAttribute("currentPage");
                            int numberSongs = DefineUtil.NUMBER_PER_PAGE;
                            if (songList != null && songList.size() > 0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=numberSongs %> của <%=numberOfSongs %> bài hát</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
												if (currentPage != 1){
										%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=currentPage - 1%>">Trang trước</a></li>
                                            				<%}%>
                                        <%
                                            String active = "";
                                            for (int i = 1; i < numberOfPages; i ++){
                                            	
                                            	if (currentPage == i){
                                            		active = "active";
                                            	} else {
                                            		active = "";
                                            	}
                                        %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=i%>"><%=i %></a></li>
										<%}} %>
										<%
												if (currentPage != (numberOfPages-1)){
										%>
                                        	<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=currentPage + 1%>">Trang tiếp</a></li>
                                        <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>