<#import "mainLayout.ftl" as layout>
<@layout.mainLayout "Plan">

<script type="text/javascript">
    window.onload = initialize("map_canvas_form");
</script>

<div class="container mainContent">
	<h2>J'ai trouvé un chat</h2>
	<p class="lead">Veuillez remplir le formulaire suivant.</p>
	
	<form name="input" action="/cats/plan/addFoundCat" method="post" enctype="multipart/form-data" class="form-horizontal">
		<fieldset>	
		<legend>A quoi ressemble ce chat ?</legend>
					
			<label>Lieu où le chat à été aperçu</label>
			<input type="text" id="adresse" name="adresse" class="search-query input-xlarge " placeholder="25 allée des lilas, Bordeaux ..." onkeyPress="if (event.keyCode == 13) {searchLocation(20);return false;} " >
			<a class="btn" href="#" onclick="javascript:searchLocation(20);return false;"><i class="icon-search"></i></a>
			<span class="help-block"><small>Indiquez une addresse et affiner le lieu en deplaçant le marqueur</small></span>
			<input type="hidden" id="latlng" name="latlng" >
		
			<div id="map_canvas_form" class="map_form"></div>

			<div class="container formulaire">
				<div class="row">
					<div class="span4">	
						<label>Nom</label>
					    <input type="text" name="nom"/>

					    <label>Race</label>
						<input type="text" id="race" name="race" placeholder="européen, balinais ..." >
						
					    <label>Sexe</label>
					    <label class="radio inline">
							<input type="radio" id="sexe" value="male" checked> mâle
						</label>
						<label class="radio inline">
							<input type="radio" id="sexe" value="femelle"> femelle
						</label>
					</div>
					<div class="span4">
						<label>Couleur</label>
						<input type="text" id="couleur" name="couleur" placeholder="roux, isabelle..." >
						
						<label>Age</label>
						<input type="text" id="age" name="age" placeholder="6mois, 5 ans ...." >
									    		    
					    <label>Photo</label>
					    <input type="file" name="photo"/>
					</div>
					<div class="span4">
						<label>Tatouage</label>
						<input type="text" id="tatouage" name="tatouage" placeholder="12X15XE..." >
						
						<label>Puce</label>
						<input type="text" id="puce" name="puce" placeholder="12455..." >
						
						<label>Date</label>
						<input type="date" id="dateDisparition" name="dateDisparition" placeholder="12/01/2012" >
					</div>
				</div>
			 </div>
		</fieldset>
		
		<fieldset>	
		<legend>Contacts</legend>
		
			<div class="container formulaire">
				<div class="row">
					<div class="span4">	
						<label>Nom & Prénom</label>
						<input type="text" id="proprietaireNom" name="proprietaireNom" placeholder="Marc Dupont ..." >
					</div>
					<div class="span4">
						<label>Email</label>
						<input type="text" id="proprietaireEmail" name="proprietaireEmail" placeholder="marc.dupont@@gmail.com" >
					</div>
					<div class="span4">
						<label>Téléphone</label>
						<input type="text" id="proprietaireTel" name="proprietaireTel" placeholder="0678010215" >
					</div>
				</div>
			</div>
		</fieldset>
		<br/>
		<div class="row">
			<div class="span2 offset10"><button class="btn btn-primary btn-large" type="submit">Envoyer</button></div>
		</div>
	
	</form>
</div>


</@layout.mainLayout>
