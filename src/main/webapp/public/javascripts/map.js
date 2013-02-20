var map;
var mapId;
var geocoder;
var infowindow;
var newCatMarker;
var CHAT_TROUVE = "TROUVE";
var CHAT_PERDU = "PERDU";

function initializeLocation(mapIdArg, location){
	mapId = mapIdArg;
	var coord = eval(location);
	var myLatlng = new google.maps.LatLng(coord[0], coord[1]);
	afficherMap(coord[0], coord[1], function(){
		new google.maps.Marker({
			position : myLatlng,
			map : map,
			draggable : false,
			animation : google.maps.Animation.DROP
		});	
	});
	
}

function initialize(mapIdArg, callback) {
	mapId = mapIdArg;
	geocoder = new google.maps.Geocoder();
	infowindow = new google.maps.InfoWindow();
	
	if (navigator.geolocation)
		navigator.geolocation.watchPosition(
				function(position){ afficherMap(position.coords.latitude, position.coords.longitude, callback);},
				function(){afficherMap(44.8248971, -0.572778, callback);},
				{enableHighAccuracy:true});
	else
		afficherMap(44.8248971, -0.572778, callback);		
}

function afficherMap(latitude, longitude, callback){
	var mapOptions = {
			center : new google.maps.LatLng(latitude,longitude),
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP // ROADMAP, SATELLITE, HYBRID,TERRAIN
		};
	map = new google.maps.Map(document.getElementById(mapId), mapOptions);

	if(callback)
		callback();
}

function searchLocation(zoom) {
	var address = $('#adresse').val();
	geocoder.geocode({'address' : address},function(results, status){ displayLocation(results, status, zoom);}	);
}

function displayLocation(results, status, zoom) {
	if (status != google.maps.GeocoderStatus.OK) {
		alertUnknownLocation();
		return;
	}
	alertUnknownLocationClose();
	var location = results[0].geometry.location;
	if(zoom == 'undefined'){ zoom = 20;}
	map.setZoom(zoom);
	map.setMapTypeId(google.maps.MapTypeId.SATELLITE);
	map.setCenter(location);
	newCatMarker = createNewCatMarker(location);
}

function createNewCatMarker(myLatlng) {
	if (newCatMarker != null)
		newCatMarker.setMap(null);

	newCatMarker = new google.maps.Marker({
		position : myLatlng,
		map : map,
		draggable : true,
		animation : google.maps.Animation.DROP
	});
	displayInfoWindow();
	setAddress();
	
	google.maps.event.addListener(newCatMarker, 'mouseup', function(event) {
	 		displayInfoWindow();
	 		setAddress();
 	});	

	return newCatMarker;
}

function displayInfoWindow() {
	if (newCatMarker == null)
		return;
	
	geocoder.geocode({ 'latLng' : newCatMarker.position,'region' : 'fr'	},
			function(results, status) {
				if (status != google.maps.GeocoderStatus.OK) return;
				if (results[1] == null) return;

				infowindow.setContent(results[1].formatted_address);
				infowindow.open(map, newCatMarker);
				
				$("#adresse").val(results[1].formatted_address);
	});
}

function setAddress() {
	var positionString ="[" + newCatMarker.position.lat() + "," + newCatMarker.position.lng() + "]";
	$('#latlng').val(positionString);
}

function alertUnknownLocation(){
	$("#alertNoPlace").fadeIn('fast');
}
function alertUnknownLocationClose(){
	$("#alertNoPlace").fadeOut('fast');
}
