<%@page import="utils.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="models.Contact"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        String err = request.getParameter("err");
        String msg = request.getParameter("msg");
        if ("1".equals(msg)){
        	out.print("<span style=\"background: yellow; color: green; font-weight: bold; padding : 5px\">Xóa liên hệ thành công</span>");
        }
        if ("1".equals(err)){
        	out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding : 5px\">ID Không tồn tại</span>");
        }
        if ("2".equals(err)){
        	out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding : 5px\">Có lỗi xảy ra</span>");
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
                                   
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath() %>/admin/contact/index">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" value="<%if(request.getParameter("namecontact") != null) out.print(request.getParameter("namecontact")); %>" name="namecontact" class="form-control input-sm" placeholder="Nhập tên liên hệ" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="85px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                @SuppressWarnings("unchecked")
                                int numberofPages = (Integer) request.getAttribute("numberofPages");
                                int numberofContacts = (Integer) request.getAttribute("numberofContacts");
                                int currentPage = (Integer) request.getAttribute("currentPage");
                                int numberContacts = DefineUtil.NUMBER_CONTACT_PAGE;
                                
                                ArrayList<Contact> listContact = (ArrayList<Contact>) request.getAttribute("listContact");
                                if (listContact != null && listContact.size() > 0){
                                	for (Contact item : listContact){
                                
                                %>
                                    <tr>
  										<td><%=item.getId() %></td>
                                        <td class="center"><%=item.getName() %></td>
                                        <td class="center"><%=item.getEmail() %></td>
                                        <td class="center"><%=item.getWebsite() %></td>
                                        <td class="center"><%=item.getMessage() %></td>
                                        <td class="center">
                                           
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=item.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%}} else { %>
                                    <tr><td class="center">Chưa có liên hệ</td></tr>
                                    <%}%>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=DefineUtil.NUMBER_CONTACT_PAGE%> của <%=numberofContacts %> liên hê</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                       <%
                                       if (currentPage != 1){
                                       %>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPage - 1%>">Trang trước</a></li>
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
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i%></a></li>
                                            <%} %>
                                            <%
                                            if (currentPage != numberofPages){
                                            %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPage + 1%>">Trang tiếp</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>