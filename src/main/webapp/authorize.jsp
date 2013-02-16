<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://tjs.sjs.sinajs.cn/t35/apps/opent/js/frames/client.js" language="JavaScript"></script>
<script> 

//弹出授权弹层：
function authLoad(){
 	App.AuthDialog.show({
	client_id : '3974094147',    //必选，appkey
	redirect_uri : 'http://apps.weibo.com/jerrytestapp',     //必选，授权后的回调地址，例如：http://apps.weibo.com/giftabc
	height: 120,    //可选，默认距顶端120px
        
	});
}
</script>
</head>
<body onload="authLoad();">

</body>
</html>