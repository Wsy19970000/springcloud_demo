<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>测试fastDFS图片上传</title>
</head>
<body>
	<center>
		<form action="<%=basePath %>/upload/uploadPic" method="post" enctype="multipart/form-data">
			<input type="file" multiple name="mf"  id="inputfile">
			<input type="submit" value="上传">
		</form>
	</center>
</body>
<script type="text/javascript">
	function ajaxUploadFile(){
		//创建一个容器，封装图片信息。将容器作为ajax传递给后台的参数
		var form = new FormData();
		//将文件装入到容器中
		var file = $("#inputfile")[0].files[0];
		form.append("wenJian",file);
		//执行ajax
		$.ajax({
			url:"<%=basePath %>/upload/uploadPic",
			type:"POST",
			data:form,
			processData:false,
			contentType:false,
			success:function(rs){
				var status=rs.status;
				if(status=='1'){//上传失败
					//模态框
					alert("上传失败");
				}else{//上传成功
					$("input[name=imgUrl]").val(rs.url);
					$("#inputfile").after("<a target='_blank' href='"+rs.url+"'><img src='"+rs.url+"' style='width: 100px;height: 100px'></a>");
				}
			}
		});
	}
</script>
</html>
