﻿<%@page import="utils.DefineUtil"%>
<%@page import="constants.CommonConstant"%>
<%@page import="java.util.ArrayList"%>
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
                <h2>Quản lý danh mục</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/cat/index">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" value="<% if(request.getParameter("cname") != null) out.print(request.getParameter("cname")); %>" class="form-control input-sm" placeholder="Nhập tên danh mục" name="cname" style="float:right; width: 300px;" />
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
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                int numberCats = DefineUtil.NUMBER_CAT_PAGE;
                                int numberofCats = (Integer) request.getAttribute("numberofCats");
                                int currentPage = (Integer) request.getAttribute("currentPage");
                                int numberofPages = (Integer) request.getAttribute("numberofPages");
                                if (request.getAttribute("listCat") != null){
                                	List<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
                                	if (listCat.size() > 0){
                                		for (Category cat : listCat){
                                			String name = cat.getName();
                                			int id = cat.getId();
                                			String urlDel = request.getContextPath()+"/admin/cat/del?id="+id;
                                			String urlEdit = request.getContextPath()+"/admin/cat/edit?id="+id;
                                %>
                                    <tr>
                                        <td><%=id%></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" onclick="return confirm('Bạn có muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
								<%} }}%>	
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=numberCats %> của <%=numberofCats %> danh mục</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<%
                                        		if (currentPage != 1){
                                        	%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%=currentPage - 1%>">Trang trước</a></li>
                                            	<%} %>
                                            <%
                                            String active = "";
                                            for (int i = 1; i <= numberofPages; i++){
                                            	if (i == currentPage){
                                            		active = "active";
                                            	} else {
                                            		active ="";
                                            	}
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0" ><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%=i %>"><%=i %></a></li>
                                            <%} %>
                                            <%
                                        		if (currentPage != numberofPages){
                                        	%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%=currentPage + 1%>">Trang tiếp</a></li>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>