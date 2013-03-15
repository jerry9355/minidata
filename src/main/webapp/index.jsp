<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ebuy.dongfeng-nissan.com.cn/js/jquery1.3.2.js" type="text/javascript"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=3974094147" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

	$(function() {
		$("#show_comments").click(function() {
			$.ajax({
				url : "getCommentsToMe.do",
				dataType : "json",
				type : "POST",
				success : function(data) {
					displayData(data);
				}

			});
		});

		$("#show_mentions").click(function() {
			$.ajax({
				url : "getMentions.do",
				dataType : "json",
				type : "POST",
				success : function(data) {
					displayData(data);
				}

			});
		});
	});

	function displayData(data) {
		$("#first p:eq(1)").html(data.first.screenName);
		$("#first p:eq(2)").html(data.first.count);
		$("#first p:eq(0) img").attr("src", data.first.avatarLarge);
		$("#second p:eq(1)").html(data.second.screenName);
		$("#second p:eq(2)").html(data.second.count);
		$("#second p:eq(0) img").attr("src", data.second.avatarLarge);
		$("#third p:eq(1)").html(data.third.screenName);
		$("#third p:eq(2)").html(data.third.count);
		$("#third p:eq(0) img").attr("src", data.third.avatarLarge);
	}
	
    WB2.anyWhere(function(W){
        W.widget.publish({
        id:'standardSelector',
	    default_text:'test'
        });
    });
	
</script>
<style type="text/css">
html,body {
	height: 100%;
	margin: 0;
	padding: 0;
}

.container {
	min-height: 100%;
	height: 100%; /*ie6不识别min-height,如上述处理*/
	position: relative;
}

.page {
	width: 100%;
	margin: 0 auto;
	padding-bottom: 35px; /*padding等于footer的高度*/
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 30px; /*footer的高度*/
	background: #f5f5f5;
	clear: both;
}

input {
	float: "left";
}

ul {
	position: relative;
	top: 20px;
}

li {
	list-style-type: none;
	width: 760px;
	height: 150px;
}

p {
	float: left;
	width: 150px;
}

img {
	border-style: none;
	border-width: 0;
}

.publish {
	background: #384313;
	border: none;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	-khtml-border-radius: 20px;
	border-radius: 20px;
	color: #ffffff;
	display: block;
	font: 18px Georgia, "Times New Roman", Times, serif;
	letter-spacing: 1px;
	margin: auto;
	padding: 7px 25px;
	text-shadow: 0 1px 1px #000000;
	text-transform: uppercase;
	position: absolute;
	left: 550px;
	top: 500px;
}

.publish:hover {
	background: #1e2506;
	cursor: pointer;
}

.version {
	position: relative;
	top: 4px;
	left: 650px;
}

#show {
	width: 100px;
	height: 100px;
	position: absolute;
	top: 200px;
	left: 200px;
	background: #ff0000;
	z-index: 11;
	zoom: 1;
	padding: 1px;
}

#bg {
	width: 760px;
	height: 600px;
	position: absolute;
	top: 0px;
	left: 0px;
	background: #000000;
	z-index: 10;
	opacity:0.3;
}

</style>
</head>
<body>
	<div class="container">
		<div class="content">
			<input id="show_comments" value="show_comments_to_me" type="button"></input>
			<input id="show_mentions" value="show_mentions" type="button"></input>
			<ol>
				<li id="first">
					<p>
						<img height="80px" width="80px" src=""></img>
					</p>
					<p></p>
					<p></p>
				</li>
				<li id="second">
					<p>
						<img height="80px" width="80px" src=""></img>
					</p>
					<p></p>
					<p></p>
				</li>
				<li id="third">
					<p>
						<img height="80px" width="80px" src=""></img>
					</p>
					<p></p>
					<p></p>
				</li>
			</ol>
			<input id="publish" class="publish" value="publish" type="button"></input>
			<a href="javascript:"><img id="standardSelector" src="http://www.sinaimg.cn/blog/developer/icon/publish_button_32.gif"></a>
		</div>
		<footer class="footer" data-role="footer">
			<div class="version">alpha 0.1</div>
		</footer>
	</div>
<!-- 	<div id="show"></div>
	<div id="bg"></div>  -->

</body>
</html>
