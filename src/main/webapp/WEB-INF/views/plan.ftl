<#import "mainLayout.ftl" as layout>
<@layout.mainLayout "Plan">

<div class="container mainContentPlan">
	<div class="row">
		<div class="span6">	
			<a href="lostCatForm" class="btn btn-danger" style="width : 150px;">J'ai perdu mon chat</a>
			<a href="foundCatForm" class="btn btn-success" style="width : 150px;">J'ai trouvé un chat</a>
		</div>
		<div class="span6 form-search">
			<div class="input-append pull-right">
  				<input class="span3 search-query" 
  						placeholder="25 allée des lilas, Bordeaux ..." 
  						id="adresse" type="text"
  						onkeyPress="if (event.keyCode == 13) {searchLocation(15);return false;} ">
  				<button class="btn btn-info" 
  						onclick="javascript:searchLocation(15);return false;">
  						<i class="icon-search"></i>
				</button>
			</div>
		</div>	
	</div>
</div>

<div id="map_canvas"></div>

<div id="detailInfoContainer">
	<div id="detailInfo ">
		<ul class="thumbnails detailInfoUl">
		  <li class="span4">
		    <div class="thumbnail">
				<button class="close" onclick="closeDetailInfo();" id="detailInfoClose" >&times;</button>
				<img id="diImage" data-src="" alt="">
				<h3 id="diNom"></h3>
				<p>Perdu le <span id="diDateDisparition"></span></p>
				<a href="detailCat/id" id="detailButton" class="btn btn-info">Plus d'informations</a>
		    </div>
		  </li>
		</ul>
	</div>
</div>

<script type="text/javascript">
    var cats = JSON.parse('${cats}');
    window.onload = initialize("map_canvas", addCats);
</script>


</@layout.mainLayout>