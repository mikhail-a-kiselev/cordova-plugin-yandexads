var exec = require('cordova/exec');
exports.initBannerView = function(){
		//alert('ibv');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "initBannerView",
            []);
	};
	
exports.refreshBannerAd = function(){
		//alert('rba');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "refreshBannerAd",
            []);
	};