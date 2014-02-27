var map;

$(document).ready(function(){
	
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
}

function onOpen(event){

}

function onMessage(event){
	console.log(JSON.parse(event.data));
	var aeronave = JSON.parse(event.data);
	var image = new google.maps.MarkerImage('img/aeronaves/rotacionado'+ aeronave.grau +'.png',new google.maps.Size(25,25),new google.maps.Point(0,0),new google.maps.Point(13,12));
	var marcador = new google.maps.Marker({
		position: new google.maps.LatLng(aeronave.latitude, aeronave.longitude),
        map: map,
        icon: image,
        title: aeronave.hex
	});
}

//$.sidr('open', 'sidr');
//$.sidr('close', 'sidr');