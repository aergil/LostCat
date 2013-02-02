var map;
var geocoder;
var infowindow;
var infoBubbles = [];
var ajouterChatPerduVisible = false;
var ajouterChatTrouveVisible = false;
var newCatMarker;
var imageUrl = "https://s3-eu-west-1.amazonaws.com/mycat-storage/";
var CHAT_TROUVE = "TROUVE";
var CHAT_PERDU = "PERDU";
var mapId;

function initializeAndAddCats(mapIdArg){
	initialize(mapIdArg) ;
	//addCats();
}

function initialize(mapIdArg) {
	mapId = mapIdArg;
	geocoder = new google.maps.Geocoder();
	infowindow = new google.maps.InfoWindow();
	
	if (navigator.geolocation)
		navigator.geolocation.watchPosition(
				function(position){afficherMap(position.coords.latitude, position.coords.longitude);},
				afficherMapDefault,
				{enableHighAccuracy:true});
	else
		afficherMapDefault();		
}

function afficherMapDefault(){
	geocoder.geocode({'address' : 'Paris, France'},function(results, status){
		if (status != google.maps.GeocoderStatus.OK) {
			alert('Geocode was not successful for the following reason: ' + status);
			return;
		}
		var location = results[0].geometry.location;
		afficherMap(location.lat(), location.lng());	
	});
}

function afficherMap(latitude, longitude){
	var mapOptions = {
			center : new google.maps.LatLng(latitude,longitude),
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		// ROADMAP, SATELLITE, HYBRID,TERRAIN
		};
		map = new google.maps.Map(document.getElementById(mapId), mapOptions);
		addCats();
}

function displayDetailInfo(i) {
	if(cats[i].statut == "PERDU")
		$("#diTitle").html("Chat perdu");
	else
		$("#diTitle").html("Chat trouvé");
	
	$("#diNom").html(cats[i].nom);
	$("#diDateDisparition").html(cats[i].dateDisparition);
	$("#diImage").attr("src", imageUrl + cats[i].imageFileName);
	$("#detailButton").attr('href','detailCat/' + cats[i].id);

	$("#detailInfoContainer").fadeIn('fast');
	/*$("#detailInfoContainerBackground").fadeIn('fast');*/
}

function closeDetailInfo() {
	$("#detailInfoContainer").fadeOut('fast');
	$("#detailInfoContainerBackground").fadeOut('fast');
}

function addCats() {
	for (i = 0; i < cats.length; i++) {
		var newInfoBubble = createInfoBubble(cats[i]);
		infoBubbles[i] = newInfoBubble;
		newInfoBubble.open();
	}
}

function createInfoBubble(cat){
	var coord = eval(cat.latLng);
	var myLatlng = new google.maps.LatLng(coord[0], coord[1]);
	var content = "<img src='" + imageUrl + cat.iconFileName + "' height='52px' width='52px' onclick='displayDetailInfo(" + i + ");return false;' />";

	var backgroundColor;
	if (cat.statut == CHAT_PERDU)
		backgroundColor = "#f20000";
	else
		backgroundColor = "#00aa00";
	
	var infoBubble = new Bubble({
		map : map,
		content : content,
		position : myLatlng,
		statut : cat.statut
	});
	return infoBubble;
}

function createNewCatMarker(myLatlng, type) {
	if (newCatMarker != null) {
		newCatMarker.setMap(null);
	}
	newCatMarker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		draggable : true,
		animation : google.maps.Animation.DROP
	});
	displayInfoWindow(type);
	setAddress();
	
	google.maps.event.addListener(newCatMarker, 'mouseup', function(event) {
	 		setAddress();
	 		displayInfoWindow(type);
 	});	

	return newCatMarker;
}

function setAddress() {
	var positionString ="[" + newCatMarker.position.lat() + "," + newCatMarker.position.lng() + "]";
	$('#latlng').val(positionString);
}

function searchLocation(zoom) {
	var address = $('#adresse').val();
	geocoder.geocode({'address' : address},function(results, status){ displayLocation(results, status, zoom);}	);
}

function displayLocation(results, status, zoom) {
	if (status != google.maps.GeocoderStatus.OK) {
		alert('Geocode was not successful for the following reason: ' + status);
		return;
	}
	var location = results[0].geometry.location;
	if(zoom == 'undefined')
		zoom = 20;
	map.setZoom(zoom);
	map.setMapTypeId(google.maps.MapTypeId.SATELLITE);
	map.setCenter(location);
	newCatMarker = createNewCatMarker(location);
}

function displayInfoWindow(type) {
	if (newCatMarker == null)
		return;
	geocoder.geocode({ 'latLng' : newCatMarker.position,'region' : 'fr'	},
			function(results, status) {
				if (status != google.maps.GeocoderStatus.OK) {
					alert('Geocoder failed due to: ' + status);
					return;
				}
				if (results[1] == null) {
					alert('No results found');
					return;
				}
				infowindow.setContent(results[1].formatted_address);
				infowindow.open(map, newCatMarker);
				
				$("#address").val(results[1].formatted_address);
	});
}

function OpenInfoBubbles() {
	for (i = 0; i < infoBubbles.length; i++) {
		infoBubbles[i].open();
	}
	if (newCatMarker != null) {
		newCatMarker.setMap(null);
		newCateMarker = null;
	}
}

function CloseInfosBubblesAndCreateNewMarker(type) {
	for (i = 0; i < infoBubbles.length; i++) {
		infoBubbles[i].close();
	}
	createNewCatMarker(map.center, type)
}


