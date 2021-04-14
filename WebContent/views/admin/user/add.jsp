<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err = request.getParameter("err");
        	String username = request.getParameter("username");
        	String fullname = request.getParameter("fullname");
        	if ("1".equals(err)){
        		out.print("<span style=\"background: yellow; font-weight: bold; padding: 5px\">Có lỗi xảy ra</span>");
        		
        	}
        	if ("2".equals(err)){
        		out.print("<span style=\"background: yellow; font-weight: bold; padding: 5px\">Username đã tồn tại</span>");
        		
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
                                <form action="<%=request.getContextPath() %>/admin/user/add" role="form" method="post"  class="sendemail">
                                    <div class="form-group">
                                        <label for="name">Tên đăng nhập</label>
                                        <input type="text" id="username" value="" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Mật Khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Họ tên</label>
                                        <input type="text" id="fullname" value="" name="fullname" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.sendemail').validate({
			rules:{
				"username":{
					required: true,
					maxlength: 20,
					minlength: 1,
				},
				"password":{
					required: true,
					password: true
				},
				"fullname":{
					required: true,
					maxlength: 36,
					minlength: 6,
				},
			},
			messages:{
				"username":{
					required: "Vui lòng nhập username ",
				},
				"password":{
					required: "Vui lòng nhập password",
				},
				"fullname":{
					required: "Vui lòng nhập fullname",
				},
			}
		});
	});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>