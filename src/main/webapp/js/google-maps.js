$(document).ready(function(){
	inicializarMapa();
});

function inicializarMapa() {
        var mapOptions = {
          center: new google.maps.LatLng(-14.239424,-53.186502),
          zoom: 4,
          disableDefaultUI: true,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("mapa"),
            mapOptions);
}