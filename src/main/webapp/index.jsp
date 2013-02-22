<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ebuy.dongfeng-nissan.com.cn/js/jquery1.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#show_comments").click(function(){
			$.ajax({
				url:"getCommentsToMe.do",
				dataType:"json",
				type:"POST",
				success: function(data){
					alert(data.first.key);
					$("#first p:eq(0)").html(data.first.key);
					$("#first p:eq(1)").html(data.first.value);
				}
				
			});
		});
	});
</script>
</head>
<body>
<div>
<input id="show_comments" value="show_comments_to_me" type="button"></input>
</div>
<div id = first>
	<p></p>
	<p></p>
</div>
</body>
</html>
