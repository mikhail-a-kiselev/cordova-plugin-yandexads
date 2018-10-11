var exec = require('cordova/exec');
exports.initBannerView = function(opt){
		//alert('ibv');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "initBannerView",
            [opt]);
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