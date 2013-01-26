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

function initialize() {
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
		map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
		addCats();
}

function displayDetailInfo(i) {
	if(cats[i].statut == "PERDU")
		$("#diTitle").html("Chat perdu");
	else
		$("#diTitle").html("Chat trouvé");
	
	$("#diName").html(cats[i].nom);
	$("#diColor").html(cats[i].couleur);
	$("#diSize").html(cats[i].taille);
	$("#diImage").attr("src", imageUrl + cats[i].imageFileName);

	$("#detailInfoContainer").fadeIn('fast');
	$("#detailInfoContainerBackground").fadeIn('fast');
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
	var coord = eval(cat.adresse);
	var myLatlng = new google.maps.LatLng(coord[0], coord[1]);
	var content = "<img src='" + imageUrl + cat.iconFileName + "' height='52px' width='52px' onclick='displayDetailInfo(" + i + ");return false;' />";

	var backgroundColor;
	if (cat.statut == CHAT_PERDU)
		backgroundColor = "#f20000";
	else
		backgroundColor = "#00aa00";
	
	var infoBubble = new InfoBubble({
		map : map,
		content : content,
		position : myLatlng,
		shadowStyle : 1,
		padding : 3,
		// backgroundColor : '#ffffff',
		backgroundColor : backgroundColor,
		borderRadius : 0,
		arrowSize : 9,
		borderWidth : 1,
		borderColor : backgroundColor, // '#0f0f0f',
		disableAutoPan : true,
		hideCloseButton : false,
		arrowPosition : 48,
		backgroundClassName : 'infoBubble',
		arrowStyle : 0
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
	$('#latlng2').val(positionString);
}

function searchLocation() {
	var address = $('#adresse').val();
	geocoder.geocode({'address' : address},function(results, status){ displayLocation(results, status);}	);
}
function searchLocation2() {
	var address = $('#adresse2').val();
	geocoder.geocode({'address' : address},function(results, status){ displayLocation(results, status);}	);
}

function displayLocation(results, status) {
	if (status != google.maps.GeocoderStatus.OK) {
		alert('Geocode was not successful for the following reason: ' + status);
		return;
	}
	var location = results[0].geometry.location;
	map.setZoom(20);
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
				
				if(type == CHAT_PERDU)
					$("#address").val(results[1].formatted_address);
				else
					$("#address2").val(results[1].formatted_address);
	});
}

function AjouterChatPerdu() {
	if (ajouterChatTrouveVisible) {
		$('#sidebar2').animate({height : 10}, 30);
		ajouterChatTrouveVisible = false;
	} 
	
	if (ajouterChatPerduVisible) {
		$('#sidebar2').animate({top : 160}, 300);
		$('#sidebar').animate({height : 10}, 300, OpenInfoBubbles());
		ajouterChatPerduVisible = false;
	} else {
		$('#sidebar2').animate({top : 600}, 300);
		$('#sidebar').animate({height : 450}, 300, CloseInfosBubblesAndCreateNewMarker(CHAT_PERDU));
		ajouterChatPerduVisible = true;
	}
}

function AjouterChatTrouve() {
	if (ajouterChatPerduVisible) {
		$('#sidebar').animate({height : 10}, 300);
		$('#sidebar2').animate({top : 160}, 300);
		ajouterChatPerduVisible = false;
	}
	
	if (ajouterChatTrouveVisible) {
		$('#sidebar2').animate({height : 10}, 300, OpenInfoBubbles());
		ajouterChatTrouveVisible = false;
	} else {
		$('#sidebar2').animate({height : 450}, 300,CloseInfosBubblesAndCreateNewMarker(CHAT_TROUVE));
		ajouterChatTrouveVisible = true;
	}
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


