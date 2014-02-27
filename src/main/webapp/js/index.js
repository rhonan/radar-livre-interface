$(document).ready(function(){
	$('#sidr-trigger').sidr();
	
	var wsUri = "ws://" + document.location.host + "/Radar-Livre/websocket";
	var websocket = new WebSocket(wsUri);
	
	websocket.onopen = function(event) { onOpen(event); };
	websocket.onmessage = function(event) { onMessage(event); };

});

function onOpen(event){

}

function onMessage(event){
	console.log(JSON.parse(event.data));
}

//$.sidr('open', 'sidr');
//$.sidr('close', 'sidr');