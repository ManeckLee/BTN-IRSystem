<%@ page language="java" contentType="text/html; charset=utf-8 "
	import="java.util.*,expert.*,db.*,java.sql.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function post(URL, PARAMS) {
		var temp = document.createElement("form");
		temp.action = URL;
		temp.method = "post";
		temp.style.display = "none";
		for ( var x in PARAMS) {
			var opt = document.createElement("textarea");
			opt.name = x;
			opt.value = PARAMS[x];
			temp.appendChild(opt);
		}
		document.body.appendChild(temp);
		temp.submit();
		return temp;
	}
</script>
<title>专家浏览</title>

<!-- 设置网站缩放比，以适应移动设备等的屏幕-->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- 导入css文件 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/boxed.css" id="layout">
<link rel="stylesheet" type="text/css" href="css/colors/green.css"
	id="colors">

<!-- 导入Java Script-->
<script src="js/jquery.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/selectnav.js"></script>
<script src="js/flexslider.js"></script>
<script src="js/twitter.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/effects.js"></script>
<script src="js/fancybox.js"></script>
<script src="js/carousel.js"></script>
<script src="js/isotope.js"></script>

<!-- 导入样式转换器-->
<link rel="stylesheet" type="text/css" href="css/switcher.css">
<script src="js/switcher.js"></script>

<style type="text/css">
@media only screen and (min-width: 960px) {
	#portfolio-wrapper img {
		min-height: 139px;
	}
}

@media only screen and (min-width: 768px) and (max-width: 959px) {
	#portfolio-wrapper img {
		min-height: 108px;
	}
}
</style>

</head>
<body>
	<%!public static final int PAGESIZE = 12;
	int pageCount;
	int curPage = 1;%>
	<%
		try
		{
			DBConn conns = new DBConn();
			Connection con = conns.getConnection();
			String sql = "SELECT * FROM ExpInfoTable";
			PreparedStatement pre = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pre.executeQuery();
			rs.last();
			int size = rs.getRow();
			pageCount = (size % PAGESIZE == 0) ? (size / PAGESIZE) : (size
					/ PAGESIZE + 1);
			String tmp = request.getParameter("curPage");
			if (tmp == null)
			{
				tmp = "1";
			}
			curPage = Integer.parseInt(tmp);
			if (curPage >= pageCount)
				curPage = pageCount;
			rs.absolute((curPage - 1) * PAGESIZE + 1);
	%>
	<div id="wrapper">


		<!-- 网站头-->

		<div class="container ie-dropdown-fix">

			<!-- 网站头-->
			<div id="header">

				<!-- Logo和标语样式 -->
				<div class="eight columns">
					<div id="logo">
						<a href="index.jsp"><img src="images/logo.png" alt="logo" /></a>
						<div id="tagline">比特能-专家机器人-专家推荐系统</div>
						<div class="clear"></div>
					</div>
				</div>

			</div>
			<!-- 网站头结束 -->

			<!-- 导航栏 -->
			<div class="sixteen columns">
				<div id="navigation">
					<ul id="nav">

						<li><a href="index.jsp">主页</a></li>

						<li><a href="#">基本应用</a>
							<ul>
								<li><a href="expert_browse.jsp">专家浏览</a></li>
								<li><a href="expert_search.jsp">专家检索</a></li>
							</ul></li>

						<li><a href="#">协同服务</a>
							<ul>
								<li><a href="expert_coservice_introduce.jsp">协同服务功能介绍</a></li>
								<li><a href="expert_coservice.jsp">多机器人协同服务</a></li>
							</ul></li>

						<li><a href="#">智能推荐</a>
							<ul>
								<li><a href="expert_recommd_introduce.jsp">智能推荐功能介绍</a></li>
								<li><a href="expert_recommd.jsp">专家智能推荐</a></li>
							</ul></li>

						<li><a href="#">关于我们</a>
							<ul>
								<li><a href="aboutus.jsp">关于我们</a></li>
							</ul></li>

					</ul>

				</div>

			</div>
			<!-- 导航栏结束 -->

		</div>
		<!-- 网站主体 -->

		<div class="container">

			<div class="sixteen columns">

				<div id="page-title">
					<h2>专家浏览</h2>
		<form action="expert_browse.jsp" method="post">
					<div id="filters">
						<ul class="" data-option-key="filter">
							<li><a href="expert_browse.jsp?curPage=1">首页</a></li>
							<li><a href="expert_browse.jsp?curPage=<%=curPage - 1%>"
								class="selected" data-option-value="*">上一页</a></li>
							<li><a href="expert_browse.jsp?curPage=<%=curPage + 1%>"
								data-option-value=".interior-design">下一页</a></li>
							<li><a href="expert_browse.jsp?curPage=<%=pageCount%>">尾页</a></li>
							<li>第<%=curPage%>页/共<%=pageCount%>页,</li>
							<li>跳转到第</li>
							<li><a style="padding: 0px;border: 0px;"><input type="text" name="curPage" size="4"></a></li>
							<li>页</li>
							<li><a style="padding: 0px;border: 0px;" ><input type="submit" name="submit" value="跳转" style = "width:50px; height:30px;"></a></li>
						</ul>
					</div>
		</form>
					
					
				</div>

			</div>
		</div>
		<div class="container">
			<div id="portfolio-wrapper">
				<%
					for (int i = 0; i < PAGESIZE; ++i)
						{
							rs.next();
							String expert_Name = rs.getString("expert_name");
							String expert_org = rs.getString("expert_org");
				%>
				<div class="four columns">
					<form action="/SoftWare/Servlet" method="post">
						<div class="picturesec">
							<img alt="" src="images/portfolio/portoflio-07.jpg"
								onclick="javascript:post('Servlet',{expert_name:<%="'" + expert_Name + "'"%>, status:'browse'})">
						</div>
						<div class="item-description">
							<h5><%=expert_Name%></h5>
							<p><%=expert_Name%>，<%=expert_org%></p>
						</div>
					</form>
				</div>

				<%
					}
						rs.close();
						con.close();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				%>
			</div>
		</div>
		<!-- 底部-->
		<div id="footer">
			<div class="container">
				<div class="sixteen columns">
					<div id="footer-bottom">
						© Copyright 2015 . All rights reserved <a
							href="http://www.sdust.edu.cn" target="_blank" title="山东科技大学">山东科技大学</a>
						<div id="scroll-top-top">
							<a href="#"></a>
						</div>
					</div>
				</div>

			</div>

		</div>


		<!-- 样式转化器 -->
		<div id="style-switcher">
			<h2>
				样式转换器<a href="#"></a>
			</h2>

			<div>
				<h3>预定义颜色</h3>
				<ul class="colors" id="color1">
					<li><a href="#" class="green" title="Green"></a></li>
					<li><a href="#" class="blue" title="Blue"></a></li>
					<li><a href="#" class="orange" title="Orange"></a></li>
					<li><a href="#" class="navy" title="Navy"></a></li>
					<li><a href="#" class="yellow" title="Yellow"></a></li>
					<li><a href="#" class="peach" title="Peach"></a></li>
					<li><a href="#" class="beige" title="Beige"></a></li>
					<li><a href="#" class="purple" title="Purple"></a></li>
					<li><a href="#" class="red" title="Red"></a></li>
					<li><a href="#" class="pink" title="Pink"></a></li>
					<li><a href="#" class="celadon" title="Celadon"></a></li>
					<li><a href="#" class="brown" title="Brown"></a></li>
					<li><a href="#" class="cherry" title="Cherry"></a></li>
					<li><a href="#" class="gray" title="Gray"></a></li>
					<li><a href="#" class="dark" title="Dark"></a></li>
					<li><a href="#" class="cyan" title="Cyan"></a></li>
					<li><a href="#" class="olive" title="Olive"></a></li>
					<li><a href="#" class="dirty-green" title="Dirty Green"></a></li>
				</ul>

				<h3>布局风格</h3>
				<div class="layout-style">
					<select id="layout-switcher">
						<option value="css/boxed">盒式布局</option>
						<option value="css/wide">宽式布局</option>
					</select>
				</div>

				<h3>背景图片</h3>
				<ul class="colors bg" id="bg">
					<li><a href="#" class="bg1"></a></li>
					<li><a href="#" class="bg2"></a></li>
					<li><a href="#" class="bg3"></a></li>
					<li><a href="#" class="bg4"></a></li>
					<li><a href="#" class="bg5"></a></li>
					<li><a href="#" class="bg6"></a></li>
					<li><a href="#" class="bg7"></a></li>
					<li><a href="#" class="bg8"></a></li>
					<li><a href="#" class="bg9"></a></li>
					<li><a href="#" class="bg10"></a></li>
					<li><a href="#" class="bg11"></a></li>
					<li><a href="#" class="bg12"></a></li>
					<li><a href="#" class="bg13"></a></li>
					<li><a href="#" class="bg14"></a></li>
					<li><a href="#" class="bg15"></a></li>
					<li><a href="#" class="bg16"></a></li>
					<li><a href="#" class="bg17"></a></li>
					<li><a href="#" class="bg18"></a></li>
				</ul>

				<h3>背景颜色</h3>
				<ul class="colors bgsolid" id="bgsolid">
					<li><a href="#" class="green-bg" title="Green"></a></li>
					<li><a href="#" class="blue-bg" title="Blue"></a></li>
					<li><a href="#" class="orange-bg" title="Orange"></a></li>
					<li><a href="#" class="navy-bg" title="Navy"></a></li>
					<li><a href="#" class="yellow-bg" title="Yellow"></a></li>
					<li><a href="#" class="peach-bg" title="Peach"></a></li>
					<li><a href="#" class="beige-bg" title="Beige"></a></li>
					<li><a href="#" class="purple-bg" title="Purple"></a></li>
					<li><a href="#" class="red-bg" title="Red"></a></li>
					<li><a href="#" class="pink-bg" title="Pink"></a></li>
					<li><a href="#" class="celadon-bg" title="Celadon"></a></li>
					<li><a href="#" class="brown-bg" title="Brown"></a></li>
					<li><a href="#" class="cherry-bg" title="Cherry"></a></li>
					<li><a href="#" class="gray-bg" title="Gray"></a></li>
					<li><a href="#" class="dark-bg" title="Dark"></a></li>
					<li><a href="#" class="cyan-bg" title="Cyan"></a></li>
					<li><a href="#" class="olive-bg" title="Olive"></a></li>
					<li><a href="#" class="dirty-green-bg" title="Dirty Green"></a></li>
				</ul>
			</div>

			<div id="reset">
				<a href="#" class="button color green boxed">重置</a>
			</div>

		</div>
</body>
</html>