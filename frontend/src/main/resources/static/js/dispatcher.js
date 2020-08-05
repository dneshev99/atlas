var Dispatcher = (function () {
	return {
		sendEventToViewModel: function (listenerName, sendJson, widget) {
			zAu.send(new zk.Event(zk.Widget.$('$' + widget),
				listenerName,
				sendJson, {
					toServer: true
				}));
		}
	};
})();
