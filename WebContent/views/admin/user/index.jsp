<%@page import="java.util.List"%>
<%@page import="utils.DefineUtil"%>
<%@page import="constants.CommonConstant"%>
<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err = request.getParameter("err");
       		String msg = request.getParameter("msg");
        	if ("1".equals(msg)){
        		out.print("<span style=\"background: yellow;color: green; font-weight: bold; padding: 5px\">Thêm người dùng thành công</span>");
        		
        	}
        	if ("2".equals(msg)){
        		out.print("<span style=\"background: yellow;color: green; font-weight: bold; padding: 5px\">Sửa người dùng thành công</span>");
        		
        	}
        	if ("1".equals(err)){
        		out.print("<span style=\"background: yellow;color: red; font-weight: bold; padding: 5px\">Có lỗi xảy ra</span>");
        		
        	}
        	if ("2".equals(err)){
        		out.print("<span style=\"background: yellow;color: red; font-weight: bold; padding: 5px\">Id không tồn tại</span>");
        		
        	}
        	if ("3".equals(err)){
        		out.print("<span style=\"background: yellow;color: red; font-weight: bold; padding: 5px\">Không có quyền thêm</span>");
        		
        	}
        	if ("4".equals(err)){
        		out.print("<span style=\"background: yellow;color: red; font-weight: bold; padding: 5px\">Không có quyền sửa</span>");
        		
        	}
        	if ("6".equals(err)){
        		out.print("<span style=\"background: yellow;color: red; font-weight: bold; padding: 5px\">Không có quyền xóa</span>");
        		
        	}
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath() %>/admin/user/index">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" value="<%if(request.getParameter("nameuser") != null) out.print(request.getParameter("nameuser")); %>" name="nameuser" class="form-control input-sm" placeholder="Nhập tên người dùng" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên đăng nhập</th>
                                        <th>Mật Khẩu</th>
                                        <th>Họ tên</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                int currentPage = (Integer) request.getAttribute("currentPage");
                                int numberofUsers = (Integer) request.getAttribute("numberofUsers");
                                int numberofPages = (Integer) request.getAttribute("numberofPages");
                                int numberUsers = DefineUtil.NUMBER_USER_PAGE;
                                if (request.getAttribute("userLists") != null){
                                	List<User> userLists = (ArrayList<User>) request.getAttribute("userLists");
                                	if (userLists.size() > 0){
                                		for(User item : userLists){
                                			String username = item.getUsername();
                                			String password = item.getPassword();
                                			String fullname = item.getFullname();
                                			int id = item.getId();
                                			String urlDel = request.getContextPath()+"/admin/user/del?id="+id;
                                			String urlEdit = request.getContextPath()+"/admin/user/edit?id="+id;
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=username %></td>
                                        <td class="center"><%=password %></td>
                                        <td class="center"><%=fullname %></td>
                                        <% 
                                        HttpSession session1 = request.getSession();
                                		User userInfo = (User) session1.getAttribute("userInfo");
                                        if ("admin".equals(userInfo.getUsername())){
                                        %>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="Xóa" class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa không?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                        <%} else { %>
                                        	 <td class="center">
                                          	<%if (userInfo.getId() == item.getId()){ %>
                                          	<a href="<%=urlEdit %>" title="sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        	
                                        	<%} %>
                                        	</td>
                                        <%} %>
                                    </tr>
                                    <%}}}else { %>
                                 	<tr><td colspan="4" align="center">Chưa có người dùng<td></tr>
                                    <%} %>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=numberUsers %> của <%=numberofUsers %> người dùng</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
                                        	if (currentPage != 1){
                                        %>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/user/index?page=<%=currentPage - 1%>">Trang trước</a></li>
                                        <%} %>
                                        <%
                                        String active = "";
                                        for (int i = 1; i <= numberofPages; i++){
                                        	if (currentPage == i){
                                        		active = "active";
                                        	} else {
                                        		active = "";
                                        	}
                                        %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/user/index?page=<%=i%>"><%=i %></a></li>
                                        <%} %>
                                        <%
                                        	if (currentPage != numberofPages){
                                        %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/user/index?page=<%=currentPage + 1%>">Trang tiếp</a></li>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>