<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.*,expert.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专家详细信息</title>

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

<!-- 导入样式转化器-->
<link rel="stylesheet" type="text/css" href="css/switcher.css">
<script src="js/switcher.js"></script>

</head>
<body>

	<div id="wrapper">

		<div class="container ie-dropdown-fix">

			<!-- 网站头 -->
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


		<!-- 网站主体-->

		<div class="container">

			<div class="sixteen columns">

				<div id="page-title">
					<h2>专家详细信息</h2>
					<div id="bolded-line"></div>
				</div>

			</div>
		</div>
		<div class="container">


			<!-- 专家详细信息展示 -->

			<div class="twelve columns">

				<div class="headline no-margin">
					<h4>专家详情</h4>
				</div>
				<!-- 此处可以进行专家风采的展示，现在数据库中可能没有专家图片信息 -->
				<p>
					<img class="image-left" src="images/portfolio/portoflio-01.jpg"
						alt="" />
					<%
						Expert expert = (Expert) request.getAttribute("expert");
						if (expert != null)
						{
					%>
				
				<dl style="font-size:15px">
					<dt><font>专家姓名：</font></dt>
					<dd><%=expert.getExpert_name()%></dd>
					<dt>工作单位：</dt>
					<dd><%=expert.getExpert_org()%></dd>
					<dt>文献评估：</dt>
					<dd><%=expert.getLiteratureAss()%>分</dd>
					<dt>情报统计：</dt>
					<dd><%=expert.getCntOfIntelligence()%>篇</dd>
					<dt>专利统计：</dt>
					<dd><%=expert.getCntOfPatent()%>篇</dd>
					<dt>工作单位评估：</dt>
					<dd><%=expert.getOrgAss()%>分</dd>
					<dt>	综合评估：</dt>
					<dd>	<%=expert.getComprehensiveAss()%>分</dd>
					<dd>&nbsp;</dd>
					<dd>&nbsp;</dd>
					<dd>&nbsp;</dd>
				</dl>
				<%
					}
				%>
				</p>

				<div class="headline">
					<h4>专家学术成果</h4>
				</div>

				<!-- Accordion #1 -->
				<span class="acc-trigger"><a href="#">专家文献信息</a></span>
				<div class="acc-container">
					<div class="content">
						<p>
						<dl style="font-size:15px">
							<%
								String author = "";
								ArrayList<ExpertLit> expertLits = (ArrayList) request
										.getAttribute("expertLits");
								if (expertLits != null)
									for (int i = 0; i < expertLits.size(); ++i)
									{
										author += expertLits.get(i).getAuthor_cn();
							%>
							<dt><font>文献标题：</font></dt>
							<dd>
								《<%=expertLits.get(i).getTitle()%>》
							</dd>
							<dt>发表日期：</dt>
							<dd><%=expertLits.get(i).getApp_date()%></dd>
							<dt>发表期刊：</dt>
							<dd><%=expertLits.get(i).getJournal_cn()%></dd>
							<dt>作者姓名：</dt>
							<dd><%=expertLits.get(i).getExpert_name()%></dd>
							<dt>合著作者：</dt>
							<dd><%=expertLits.get(i).getAuthor_cn()%></dd>
							<dt>发表单位：</dt>
							<dd><%=expertLits.get(i).getUnit()%></dd>
							<dt>文献摘要：</dt>
							<dd><%=expertLits.get(i).getAbs()%></dd>
							<dt>文献关键字：</dt>
							<dd><%=expertLits.get(i).getKeywords()%></dd>
							<hr align="center" width="700" size="9" color="black">
							<%
								}
							%>
						</dl>
						</p>
					</div>
				</div>

				<!-- Accordion #2 -->
				<span class="acc-trigger"><a href="#">专家专利信息</a></span>
				<div class="acc-container">
					<div class="content">
						<p>
						<dl>
							<%
								ArrayList<ExpertPatent> expertPatents = (ArrayList) request
										.getAttribute("expertPatents");
								if (expertPatents != null)
									for (int i = 0; i < expertPatents.size(); ++i)
									{
							%>
							<dt>专利标题：</dt>
							<dd>
								《<%=expertPatents.get(i).getTitle()%>》
							</dd>
							<dt>作者姓名：</dt>
							<dd><%=expertPatents.get(i).getExpert_name()%></dd>
							<dt>发表日期：</dt>
							<dd><%=expertPatents.get(i).getDate()%></dd>
							<dt>申请单位：</dt>
							<dd><%=expertPatents.get(i).getApplicant()%></dd>
							<dt>专利摘要：</dt>
							<dd><%=expertPatents.get(i).getAbs()%></dd>
							<hr align="center" width="700" size="9" color="black">
							<%
								}
							%>
						</dl>
						</p>
					</div>
				</div>

				<!-- Accordion #3 -->
				<span class="acc-trigger"><a href="#">专家情报信息</a></span>
				<div class="acc-container">
					<div class="content">
						<p>
						<dl>
							<%
								ArrayList<ExpertIntell> expertIntells = (ArrayList) request
										.getAttribute("expertIntells");
								if (expertIntells != null)
									for (int i = 0; i < expertIntells.size(); ++i)
									{
							%>
							<dt>情报标题：</dt>
							<dd>
								《<%=expertIntells.get(i).getTitle()%>》
							</dd>
							<dt>作者姓名：</dt>
							<dd><%=expertIntells.get(i).getExpert_name()%></dd>
							<dt>发表日期：</dt>
							<dd><%=expertIntells.get(i).getDate()%></dd>
							<dt>情报摘要：</dt>
							<dd><%=expertIntells.get(i).getAbs()%></dd>
							<hr align="center" width="700" size="9" color="black">
							<%
								}
							%>
						</dl>
						</p>
					</div>
				</div>

			</div>


			<!-- 专家关联网的展示-->

			<div class="four columns">

				<div class="widget-alt">
					<div class="headline no-margin">
						<h4>合作伙伴</h4>
					</div>
					<%
						String[] authorss = author.split(";|[|]");
						ArrayList<String> authors = new ArrayList();
						for (int i = 0; i < authorss.length; i++)
						{
							String buf = authorss[i].trim();
							if (!authors.contains(buf))
								authors.add(buf);
						}
						for (String s : authors)
						{
							if (s != null)
							{
					%>

					<div class="latest-post-blog">
					<p style="font-size:15px">
						<img src="images/popular-post-01.png" alt="" />
						<font><%=s%>  <span>______________</span></font>
						
					</p>
					</div>

					<%
							}
						}
					%>


				</div>

			</div>


		</div>


	</div>

	<!-- 网站底部-->
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


	<!-- 样式转换器 -->
	<div id="style-switcher">
		<h2>
			Style Switcher <a href="#"></a>
		</h2>

		<div>
			<h3>Predefined Colors</h3>
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

			<h3>Layout Style</h3>
			<div class="layout-style">
				<select id="layout-switcher">
					<option value="css/boxed">Boxed</option>
					<option value="css/wide">Wide</option>
				</select>
			</div>

			<h3>Background Image</h3>
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

			<h3>Background Color</h3>
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
			<a href="#" class="button color green boxed">Reset</a>
		</div>

	</div>

</body>
</html>