var exec = require('cordova/exec');
var YandexAds_ = {
	"initBannerView":function(){
		//alert('ibv');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "initBannerView",
            []);
	},
	"refreshBannerAd":function(){
		//alert('rba');
		cordova.exec(
            function(winParam) {
				
			},
            function(error) {
				
			},
            "YandexAds",
            "refreshBannerAd",
            []);
	}
};
module.exports = YandexAds_;