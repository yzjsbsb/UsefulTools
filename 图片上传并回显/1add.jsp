<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-add</title>
<script type="text/javascript">
	function uploadPic(){
		var options={
		   	type: "POST",
		   	url: "/uploadPic.do",			//异步请求路径
		  	dataType: "json",				//dataType预期服务器返回的数据类型
		   	success: function(data){		//请求路径返回的参数
		   		//根据id获取jquery对象
				$("#allImgUrl").attr("src",data.allUrl);		//用于显示图片
				$("#path").val(data.path);						//将path作为提交内容进行保存
			}
		}
		//采用jqueryForm框架，利用其的异步请求
		$("#jvForm").ajaxSubmit(options);
	}

</script>
</head>
<body>
	<form id="jvForm" action="${pageContext.request.contextPath }/back_page/brand/add.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>  
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allImgUrl"/>
						<!-- 上传图片时，点击打开，触发onchange事件，调用uploadPic()方法上传图片 -->
						<input type="file" name="pic" onchange="uploadPic()"/>
						<!-- 设置隐藏域，用于传输图片路径 -->
						<input type="hidden" id="path" name="imgUrl"/>
					</td>
				</tr>
		</table>
	</form>
</div>
</body>
</html>