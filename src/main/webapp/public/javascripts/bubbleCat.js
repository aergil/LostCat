var infoBubbles = [];
var imageUrl = "https://s3-eu-west-1.amazonaws.com/mycat-storage/";

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
}

function closeDetailInfo() {
	$("#detailInfoContainer").fadeOut('fast');
}

function addCats() {
	for (i = 0; i < cats.length; i++) {
		infoBubbles[i] = createInfoBubble(cats[i]);
		infoBubbles[i].open();
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
	
	return new Bubble({
		map : map,
		content : content,
		position : myLatlng,
		statut : cat.statut
	});
}

