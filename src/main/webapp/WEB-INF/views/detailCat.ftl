<#import "mainLayout.ftl" as layout>
<@layout.mainLayout "Plan">

<div class="container mainContent">
	<div class="row">
		<div class="span4">
			<div class="thumbnail">
				<a href="#myModal" role="button" data-toggle="modal">
   					<img src="https://s3-eu-west-1.amazonaws.com/mycat-storage/${chat.getImageFileName()!}" alt="">
   					<div style="width:100%;text-align: center;"><i class="icon-eye-open"></i></div>
   				</a>
			</div>	
 		</div>
		<div class="span8">
			<h2>Chat Perdu : ${chat.getNom()!}</h2>
			<p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique urna ut odio sodales elementum. Ut dignissim vestibulum egestas. In bibendum eleifend purus, ac luctus nisi iaculis et.</p>
		</div>
	</div>
	<div class="row">
		<div class="span6">	
			<fieldset>	
			<legend>${chat.getNom()!}</legend>
				<dl class="dl-horizontal">
					<dt>Nom</dt>
					<dd>&nbsp;${chat.getNom()!}</dd>
					<dt>Race</dt>
					<dd>&nbsp;${chat.getRace()!}</dd>
					<dt>Sexe</dt>
					<dd>&nbsp;${chat.getSexe()!}</dd>
					<dt>Couleur</dt>
					<dd>&nbsp;${chat.getCouleur()!}</dd>
					<dt>Age</dt>
					<dd>&nbsp;${chat.getAge()!}</dd>
					<dt>Tatouage</dt>
					<dd>&nbsp;${chat.getTatouage()!}</dd>
					<dt>Puce</dt>
					<dd>&nbsp;${chat.getPuce()!}</dd>
					<dt>Date de la disparition</dt>
				  	<dd>&nbsp;${chat.getDateDisparition()!}</dd>
				</dl>
			</fieldset>
		</div>
		<div class="span6">
			<fieldset>	
			<legend>Contacter le propriétaire</legend>
				<dl class="dl-horizontal">
				  <dt>Nom & Prénom</dt>
				  <dd>&nbsp;${chat.getProprietaireNomPrenom()!}</dd>
				  <dt>Email</dt>
				  <dd>&nbsp;${chat.getProprietaireEmail()!}</dd>
				  <dt>Téléphone</dt>
				  <dd>&nbsp;${chat.getProprietaireTelephone()!!}</dd>
				  <dt>Adresse</dt>
				  <dd>&nbsp;${chat.getProprietaireAdresse()!}</dd>
				</dl>
			</fieldset>
		</div>

	</div>
	<div id="map_canvas_form" class="map_form"></div>
</div>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Photo de ${chat.getNom()!}</h3>
  </div>
  <div class="modal-body">
    <img src="https://s3-eu-west-1.amazonaws.com/mycat-storage/${chat.getImageFileName()!}" alt="">
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Fermer</button>
  </div>
</div>

<script type="text/javascript">
	window.onload = initializeLocation("map_canvas_form", "${chat.getLatLng()!}");
</script>



</@layout.mainLayout>
