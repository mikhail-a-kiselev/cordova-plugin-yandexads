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
exports.loadAd = function(){
		//alert('rba');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "loadAd",
            []);
	};
exports.showView = function(){
		//alert('rba');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "showView",
            []);
	};
exports.hideView = function(){
		//alert('rba');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "hideView",
            []);
	};
