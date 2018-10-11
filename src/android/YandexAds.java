package com.codemech.yandexads;

//import android.R;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
//import org.apache.cordova.test.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdRequestError;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;
import com.yandex.mobile.ads.nativeads.NativeAdAssets;
import com.yandex.mobile.ads.nativeads.NativeAdEventListener;
import com.yandex.mobile.ads.nativeads.NativeAdException;
import com.yandex.mobile.ads.nativeads.NativeAdImage;
import com.yandex.mobile.ads.nativeads.NativeAdLoader;
import com.yandex.mobile.ads.nativeads.NativeAdLoaderConfiguration;
import com.yandex.mobile.ads.nativeads.NativeAppInstallAd;
import com.yandex.mobile.ads.nativeads.NativeAppInstallAdView;
import com.yandex.mobile.ads.nativeads.NativeContentAd;
import com.yandex.mobile.ads.nativeads.NativeContentAdView;

public class YandexAds extends CordovaPlugin {
	 /** Cordova Actions. */
	private static final String INIT_BANNER_VIEW = "initBannerView"; 
	private static final String REFRESH_BANNER_AD = "refreshBannerAd";
	private static final String LOAD_AD = "loadAd";
	private static final String SHOW_VIEW = "showView";
	private static final String HIDE_VIEW = "hideView";
	
	private AdView mAdView;
    private AdRequest mAdRequest;
	
	@Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		mAdView = new AdView(cordova.getActivity());
		final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(android.widget.RadioGroup.LayoutParams.MATCH_PARENT, android.widget.ActionMenuView.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		cordova.getActivity().addContentView(mAdView, layoutParams);
		mAdView. setAutoRefreshEnabled(false);
		mAdView.bringToFront();
		//initBannerView(null, null);
		//refreshBannerAd();
	}
	
	@Override
    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        if(INIT_BANNER_VIEW.equals(action)){
        	JSONObject options = inputs.optJSONObject(0);
            result = initBannerView(options, callbackContext);
			return true;
        }
        else if(REFRESH_BANNER_AD.equals(action)){
            refreshBannerAd();
			return true;
        }
        else if(LOAD_AD.equals(action)){
        	mAdView.loadAd(mAdRequest);
			return true;
        }
        else if(SHOW_VIEW.equals(action)){
        	mAdView.setVisibility(View.VISIBLE);
			return true;
        }
        else if(HIDE_VIEW.equals(action)){
        	mAdView.setVisibility(View.INVISIBLE);
			return true;
        } else {
			return false;
		}
        
        
	}
	private PluginResult initBannerView(final JSONObject options, CallbackContext context) {
        /*
        * Replace demo R-M-DEMO-320x50 with actual Block ID
        * Following demo Block IDs may be used for testing:
        * R-M-DEMO-300x250 for AdSize.BANNER_300x250
        * R-M-DEMO-300x250-context for AdSize.BANNER_300x250
        * R-M-DEMO-300x300-context for AdSize.BANNER_300x300
        * R-M-DEMO-320x50 for AdSize.BANNER_320x50
        * R-M-DEMO-320x50-app_install for AdSize.BANNER_320x50
		* R-M-DEMO-320x100-context for AdSize.BANNER_320x100
        * R-M-DEMO-728x90 for AdSize.BANNER_728x90
        */
        cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
            	if(options != null && options.has("bannerId")){
            		mAdView.setBlockId(options.optString("bannerId"));
            	} else {
            		mAdView.setBlockId("R-M-DEMO-320x50");//blockId
            	}
            	mAdView.setAdSize(AdSize.BANNER_320x50);

            	mAdRequest = AdRequest.builder().build();
            	mAdView.setAdEventListener(mBannerAdEventListener);
            //	Log.i("YA_", "after initialization");
            	
            }
		});
        
        return null;
    }
	private View.OnClickListener mLoadBannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            refreshBannerAd();
        }
    };
    private void refreshBannerAd() {
        cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
            	mAdView.setVisibility(View.INVISIBLE);
            	mAdView.loadAd(mAdRequest);
            }
    	});
    }
	    private void showBannerView(){
    	cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
            	mAdView.setVisibility(View.VISIBLE);
            }
    	});
    }
    private void hideBannerView(){
    	cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
            	mAdView.setVisibility(View.INVISIBLE);
            }
    	});
    }
	private AdEventListener mBannerAdEventListener = new AdEventListener.SimpleAdEventListener() {
        @Override
        public void onAdLoaded() {
            mAdView.setVisibility(View.VISIBLE);
			webView.loadUrl(String.format(
                    "javascript:cordova.fireDocumentEvent('onSuccessReceiveYandexAd', { });" 
            ));
        }
		@Override
        public void onAdFailedToLoad(final AdRequestError error){
        	webView.loadUrl(String.format(
                    "javascript:cordova.fireDocumentEvent('onFailedToReceiveYandexAd', { 'code': %d, 'description':'%s' });",
                    error.getCode(), error.getDescription()
             ));
        }
    };
}