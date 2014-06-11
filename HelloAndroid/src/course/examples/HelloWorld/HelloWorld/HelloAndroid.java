package course.examples.HelloWorld.HelloWorld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HelloAndroid extends Activity {
	WebView view;
	private final Activity activity = this;
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		   if (savedInstanceState != null){
			      ((WebView)findViewById(R.id.webView1)).restoreState(savedInstanceState);
		   }
		view = (WebView) this.findViewById(R.id.webView1);
		//custom web view client
		class MyWebViewClient extends WebViewClient {
		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		    	    view.loadUrl(url);		    	    
		            return true;		       		        
		    }
		    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		    	view.loadUrl("file:///android_asset/error.html");

            }
		    
		    
		}
		view.setWebViewClient(new MyWebViewClient());
		String url = "http://10.150.34.140/matrix/";	
		view.getSettings().setJavaScriptEnabled(true);
		view.getSettings().setSupportMultipleWindows(true);
		view.getSettings().setAppCacheEnabled(true);
		view.getSettings().setPluginsEnabled(true);
		view.getSettings().setAllowFileAccess(true);
		view.getSettings().setPluginsEnabled(true);
		view.getSettings().setBuiltInZoomControls(true);
		view.loadUrl(url);
		
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack() ) {
	        view.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	protected void onSaveInstanceState(Bundle outState) {
	      view.saveState(outState);
	   }
	
}