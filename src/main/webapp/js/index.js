var map;
var marcadores = [];

$(document).ready(function(){
	$(".close").click(function(){
		$.sidr('close', 'sidr');
	});
	
	inicializarMapa();
	
	$('#sidr-trigger').sidr();
	
	var wsUri = "ws://" + document.location.host + "/Radar-Livre/websocket";
	var websocket = new WebSocket(wsUri);
	
	websocket.onopen = function(event) { onOpen(event); };
	websocket.onmessage = function(event) { onMessage(event); };

});

function inicializarMapa() {
    var mapOptions = {
      center: new google.maps.LatLng(-14.239424,-53.186502),
      zoom: 4,
      disableDefaultUI: true,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("mapa"),
        mapOptions);
    
	google.maps.event.addListener(map,'click', function(){
		$.sidr('close', 'sidr');
	});
}

function onOpen(event){

}

function onMessage(event){
	console.log(JSON.parse(event.data));
	var aeronave = JSON.parse(event.data);
	
	switch(aeronave.status){
	case "ADD":
		adicionarMarcador(aeronave);
		break;
	case "REMOVE":
		removerMarcador(aeronave);
		break;
	case "UPDATE":
		atualizarMarcador(aeronave);
		break;
	}
}

function adicionarMarcador(aeronave){
	var image = new google.maps.MarkerImage('img/aeronaves/rotacionado'+ aeronave.grau +'.png',new google.maps.Size(25,25),new google.maps.Point(0,0),new google.maps.Point(13,12));
	var marcador = new google.maps.Marker({
		position: new google.maps.LatLng(aeronave.latitude, aeronave.longitude),
        map: map,
        icon: image,
        title: aeronave.aeronave_hex
	});
	marcadores.push(marcador);
	google.maps.event.addListener(marcador, 'click', function() {
		$.sidr('open', 'sidr');
		$("#hex").text(aeronave.aeronave_hex);
		$("#latitude").text(aeronave.latitude);
		$("#longitude").text(aeronave.longitude);
		$("#grau").text(aeronave.grau);
	});
}

function removerMarcador(aeronave){
	$.each(marcadores, function (key, val){
		if(val.title == aeronave.aeronave_hex){
			val.setMap(null);
		}
	});
	marcadores.remove(aeronave);
}

function atualizarMarcador(aeronave){ 
	$.each(marcadores, function (key, val){
		if(val.title == aeronave.aeronave_hex){
			val.setPosition(new google.maps.LatLng(aeronave.latitude, aeronave.longitude));
			val.setIcon(new google.maps.MarkerImage('img/aeronaves/rotacionado'+ aeronave.grau +'.png',new google.maps.Size(25,25),new google.maps.Point(0,0),new google.maps.Point(13,12)));
		}
	});
}
//Removes an element from an array.
//String value: the value to search and remove.
//return: an array with the removed element; false otherwise.
Array.prototype.remove = function(value) {
	var idx = this.indexOf(value);
	if (idx != -1) {
		return this.splice(idx, 1); // The second parameter is the number of elements to remove.
	}
	return false;
};

//$.sidr('open', 'sidr');
//$.sidr('close', 'sidr');