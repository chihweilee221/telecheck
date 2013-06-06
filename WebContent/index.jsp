<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset=utf-8>
	<title>中華電信門號網內外查詢服務</title>

    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
<script language="javascript" type="text/javascript">


function query() {
	
	$('#numhelp').text("");
	$('#queryResult').html("");
	
	var num = $("#num").val();
	if(num.trim()=="") {
		$('#numhelp').text("Required");
		hasEmpty = true;
		return;
	}
	$('#queryResult').html("<img src='img/ajax-loader.gif'/>");

	$.get("telecheck?number=" + num,
	    function(data) {

		var res = "";
		var color = "";
		var slice = "";

		if(data.status == 0) {
			res = data.opCode;
			color = "blue";
		} else {
			res = data.msg;
			color = "red";
		}
		
		
		slice += "<h2 style='color:" + color +";'>" + res + "<h2/>";
		
		$('#queryResult').html(slice);
	}, 
	"json");
	
}
</script>
	
	
<style>

body{  
    font-family: "微軟正黑體", "Microsoft JhengHei";
}
</style>	
</head>
<body style="family-name：'微軟正黑體', 'Microsoft JhengHei'">

<div style="width:800px;position:absolute;left:50%;margin-left:-400px;">

<h1>中華電信門號網內外查詢服務</h1>
<div>
	<form>
	  <fieldset>
	    <legend>網內外查詢服務</legend>
	    <label>輸入門號</label>
	    <input id="num" type="text" placeholder="0912345678"><span id="numhelp" class="help-inline"></span><br/>
	    <input type="button" id="btn_submit" class="btn btn-primary" onClick="query();" value="查詢"></button>
	  </fieldset>
	</form>
	
	<div id="queryResult"/>
	</div>

</body>
</html>
