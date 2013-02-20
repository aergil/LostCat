<#macro mainLayout title="LostCat">
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" media="screen" href="/public/stylesheets/bootstrap.css">
    <link rel="stylesheet" media="screen" href="/public/stylesheets/main.css">
    <link rel="stylesheet" media="screen" href="/public/stylesheets/map.css">
    <link rel="stylesheet" media="screen" href="/public/stylesheets/bubble.css">
    <link rel="shortcut icon" type="image/png" href="public/images/favicon.png">

    <script type="text/javascript" src="/public/javascripts/jquery-1.8.1.min.js" ></script>
    <script type="text/javascript" src="/public/javascripts/bootstrap.min.js" ></script>
    <script type="text/javascript" src="/public/javascripts/map.js" ></script>
    <script type="text/javascript" src="/public/javascripts/bubbleCat.js" ></script>
    <script type="text/javascript" src="/public/javascripts/InfoBubble.js" ></script>

    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCnODw-2W4Rlrf3FAiBOF_qhsh5dm41jYo&sensor=true&language=fr" ></script>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
</head>


<body >
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container" >
				<a class="brand" href="plan">Chat perdu<span class="alpha">alpha</span></a>
				<ul class="nav pull-right">
		            <li <#if title == "Plan">class="active"</#if>><a  href="/cats/plan/plan">Plan</a></li>
		            <li <#if title == "Links">class="active"</#if>><a  href="/cats/application/links">Liens</a></li>
		            <li <#if title == "Contact">class="active"</#if>><a  href="/cats/application/contact">Contact</a></li>
		            <li <#if title == "Index">class="active"</#if>><a  href="/cats/application/index">Index</a></li>
				</ul>
			</div>
		</div>
	</div> 

	<#nested/>

</body>
</html>

</#macro>
