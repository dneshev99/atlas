var map;
var icon = L.icon({
    iconUrl: 'images/marker-icon.png',
    shadowUrl: 'images/marker-shadow.png',

    iconSize:     [25, 41], // size of the icon
    shadowSize:   [41, 41], // size of the shadow
    iconAnchor:   [13, 41], // point of the icon which will correspond to marker's location
    shadowAnchor: [20, 41],  // the same for the shadow
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});
var basesLayer = L.featureGroup();
var pathsLayer = L.featureGroup();

var newBases = {bases : []}

loadMap = function (uuid) {
    map = L.map(uuid).setView([42.698334, 23.319941], 6);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    	   attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    map.addLayer(basesLayer);
    map.addLayer(pathsLayer);

    map.on('click', onMapClick);
}

onMapClick = function(e) {
    var clickLocation = {
        lat : e.latlng.lat,
        lng : e.latlng.lng,
    }

    L.marker([clickLocation.lat, clickLocation.lng], {icon : icon}).addTo(basesLayer)

    Dispatcher.sendEventToViewModel("onMapClick", JSON.stringify(clickLocation), "dataTransfer");
}

clearBasesLayer = function() {
  basesLayer.clearLayers();
}

clearPathsLayer = function() {
  pathsLayer.clearLayers();
}

clearMap = function() {
    clearBasesLayer();
    clearPathsLayer();
}

addBasesToMap = function(bases) {
    bases.forEach(base => L.marker([base.lat, base.lng], {icon : icon}).addTo(basesLayer));
}

addPathsToMap = function(paths) {
    paths.forEach(function(path){
         L.marker([path.from.lat, path.from.lng], {icon : icon}).bindPopup(path.from.name).addTo(pathsLayer);
         L.marker([path.to.lat, path.to.lng], {icon : icon}).bindPopup("<p>" + path.to.name + "</p>").addTo(pathsLayer);
         L.polyline([[path.from.lat, path.from.lng],
                     [path.to.lat, path.to.lng]
                    ]).bindPopup(path.price).addTo(pathsLayer);
    });
}

zoomOnMarker = function(marker) {
    alert(marker);
    map.setView([marker.lat, marker.lng], 15);
}
