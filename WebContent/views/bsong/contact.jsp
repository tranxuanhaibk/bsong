<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/bsong/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2><span>Liên hệ</span></h2>
      <div class="clr"></div>
      <p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      <%
       	String msg = request.getParameter("msg");
      	String err = request.getParameter("err");
      	String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
       if ("1".equals(msg)){
    	   out.print("<div style=\"background: yellow; color: green; font-weight: bold; padding: 5px\">Gửi liên hệ thành công</div>");
       } 
       if ("1".equals(err)){
    	   out.print("<div style=\"background: yellow; color: red; font-weight: bold; padding: 5px\">Gửi liên hệ thất bại</div>");
       }
      %>
      <form action="" method="post" id="sendemail">
        <ol>
          <li>
            <label for="name">Họ tên (required)</label>
            <input id="name" value="<%if (name != null) out.print(name);%>" name="name" class="text" />
          </li>
          <li>
            <label for="email">Email (required)</label>
            <input id="email" value="<%if (email != null) out.print(email);%>" name="email" class="text" />
          </li>
          <li>
            <label for="website">Website</label>
            <input id="website" value="<%if (website != null) out.print(website); %>" name="website" class="text" />
          </li>
          <li>
            <label for="message">Nội dung</label>
            <textarea id="message" name="message" rows="8" cols="50"><%if (message != null) out.print(message); %></textarea>
          </li>
          <li>
            <input type="image" name="imageField" id="imageField" src="<%=request.getContextPath() %>/templates/bsong/images/submit.gif" class="send" />
            <div class="clr"></div>
          </li>
        </ol>
      </form>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/bsong/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
			$('#sendemail').validate({
					rules:{
						"name":{
							required: true,
							maxlength: 32,
							minlength: 6,
						},
						"email":{
							required: true,
							email: true
						},
						"website":{
							required: false,
						},
						"message":{
							required: false,
						},
					},	
					messages:{
						"name":{
							required: "Vui lòng nhập tên ",
						},
						"email":{
							required: "Vui lòng nhập email",
						},
						"website":{
							required: "Vui lòng nhập website",
						},
						"message":{
							required: "Vui lòng nhập nội dung",
						},
					}
				});	
			});
</script>
<script>
	var editor = CKEDITOR.replace('message');
	CKFinder.setupCKEditor(editor,'libraries/ckfinder/');
	document.getElementById("contact").classList.add('active-menu');
</script>
<%@ include file="/templates/bsong/inc/footer.jsp" %>
