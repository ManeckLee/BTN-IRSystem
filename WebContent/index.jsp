<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>比特能.专家机器人</title>

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

</head>
<body>
	<div id="wrapper">

		<!-- 网站头-->
		<div class="container ie-dropdown-fix">

			<div id="header">

				<!-- Logo和标语样式 -->
				<div class="eight columns">
					<div id="logo">
						<a href="#"><img src="images/logo.png" alt="logo" /></a>
						<div id="tagline">
							<font>比特能-专家机器人-专家推荐系统</font>
						</div>
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
				<section class="slider">
				<div class="flexslider home">
					<ul class="slides">

						<li><img src="images/slider-img-01.jpg" alt="" />
							<div class="slide-caption">
								<h3>比特能</h3>
								<p>“比特能”是一种集数据、信息和知识于一体的能量源，如同原子通过裂变爆发出巨大能量一般，大数据通过大链接产生“比特能”。</p>
							</div></li>

						<li><img src="images/slider-img-02.jpg" alt="" />
							<div class="slide-caption">
								<h3>专家服务</h3>
								<p>企业在使用中国工业淘堡网的淘人才服务时，对科技专家的信息服务需求量巨大，具体需求包括查询专家、发现专家、专家动态跟踪、专家决策支持、专家团队关系和专家的研发成果等</p>
							</div></li>

						<li><img src="images/slider-img-03.jpg" alt="" /></li>

					</ul>
				</div>
				</section>
			</div>

		</div>
		<div class="container">

			<!-- 描述性方格 -->
			<div class="icon-box-container">

				<div class="one-third column">
					<div class="icon-box">
						<i class="ico-display" style="margin-left: -10px;"></i>
						<h3>清新 &amp; 简洁界面</h3>
						<p>比特能-专家机器人采用简洁，易操作的界面实现，为您提供简单高效的问题求解网站</p>
					</div>
				</div>

				<div class="one-third column">
					<div class="icon-box">
						<i class="ico-cogwheel"></i>
						<h3>专家自动推荐</h3>
						<p>比特能-专家机器人采用简洁，易操作的界面实现，为您提供简单高效的问题求解网站</p>
					</div>
				</div>

				<div class="one-third column">
					<div class="icon-box">
						<i class="ico-iphone"></i>
						<h3>即时响应</h3>
						<p>比特能-专家机器人采用简洁，易操作的界面实现，为您提供简单高效的问题求解网站</p>
					</div>
				</div>

			</div>
			<!-- 方格结束-->

		</div>
		<div class="container">

			<div class="sixteen columns">
				<!-- 标题 -->
				<div class="headline no-margin">
					<h3>知名专家</h3>
				</div>
			</div>

			<div class="four columns">
				<form action="/SoftWare/Servlet" method="post">
					<div class="picturesec">
						<img alt="" src="images/portfolio/portoflio-08.jpg"
							onclick="javascript:post('Servlet',{expert_name:'吕俊复', status:'browse'})">
					</div>
					<div class="item-description">
						<h5>吕俊复</h5>
						<p>吕俊复，清华大学，科学与动力工程，教育部重点实验室</p>
					</div>
				</form>


			</div>

			<div class="four columns">
				<form>
					<div class="picturesec">
						<img alt="" src="images/portfolio/portoflio-08.jpg"
							onclick="javascript:post('Servlet',{expert_name:'姚春德', status:'browse'})">
					</div>
					<div class="item-description">
						<h5>姚春德</h5>
						<p>姚春德，天津大学</p>
					</div>
				</form>
			</div>

			<div class="four columns">
				<form>
					<div class="picturesec">
						<img alt="" src="images/portfolio/portoflio-08.jpg"
							onclick="javascript:post('Servlet',{expert_name:'王晓昌', status:'browse'})">
					</div>
					<div class="item-description">
						<h5>王晓昌</h5>
						<p>王晓昌，西安建筑科技大学</p>
					</div>
				</form>
			</div>

			<div class="four columns">
				<div class="picturesec">
					<img alt="" src="images/portfolio/portoflio-08.jpg"
						onclick="javascript:post('Servlet',{expert_name:'潘贞存', status:'browse'})">
				</div>
				<div class="item-description">
					<h5>潘贞存</h5>
					<p>潘贞存，山东大学</p>
				</div>
			</div>

		</div>

		<div class="container">
			<div class="sixteen columns">

				<div class="headline no-margin">
					<h3>支持单位</h3>
				</div>

				<ul class="client-list">
					<li><a href="http://www.gytaobao.cn/" target="_blank"><img src="images/logo-01.png" alt="" /></a></li>
					<li><a href="http://www.rjg.gov.cn/" target="_blank"><img src="images/logo-02.png" alt="" /></a></li>
					<li><a href="http://www.travelsky.net/" target="_blank"><img src="images/logo-03.png" alt="" /></a></li>
					<li><a href="http://www.neusoft.com/cn/" target="_blank"><img src="images/logo-04.png" alt="" /></a></li>
					<li><a href="http://www.ztesoft.com/index.html" target="_blank"><img src="images/logo-05.png" alt="" /></a></li>
				</ul>

			</div>
		</div>

	</div>


	<!-- 底部 -->

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
	<!--底部结束 -->


	<!--样式转换器-->
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