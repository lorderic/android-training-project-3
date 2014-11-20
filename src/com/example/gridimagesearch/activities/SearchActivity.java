package com.example.gridimagesearch.activities;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gridimagesearch.R;
import com.example.gridimagesearch.adapters.ImageResultsAdapter;
import com.example.gridimagesearch.models.ImageResult;
import com.example.gridimagesearch.models.SearchFilters;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class SearchActivity extends Activity {
	private EditText etQuery;
	private GridView gvResults;
	private ArrayList<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;
	private SearchFilters filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }
    
    public void onImageSearch(View v){
    	String query = etQuery.getText().toString();
    	
    	AsyncHttpClient client = new AsyncHttpClient();
        String searchURL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8" + getFilterAPI();
        client.get(searchURL, new JsonHttpResponseHandler(){
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        		JSONArray imageResultJson = null;
        		try {
					imageResultJson = response.getJSONObject("responseData").getJSONArray("results");
					imageResults.clear(); // in case where its a new search
					
					// When making changes to the adapter, it does modify the underlying data and need not notify
					aImageResults.addAll(ImageResult.fromJSONArray(imageResultJson));
				} catch (JSONException e) {
					e.printStackTrace();
				}
        		//Log.i("INFO", imageResults.toString());
        	}
        });
    	
    	//Toast.makeText(this, "Search for: " + query, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.miSettings) {
        	Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
        	startActivityForResult(intent, 200);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 200){
    		if (resultCode == RESULT_OK){
    			filters = (SearchFilters) data.getSerializableExtra("filters");
    			//Log.i("INFO", filters.toString());
    		}
    	}
    }
    
    private void setupViews(){
    	etQuery = (EditText) findViewById(R.id.etQuery);
    	gvResults = (GridView) findViewById(R.id.gvResults);
    	gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SearchActivity.this, ImageDisplayActivity.class);
				ImageResult result = imageResults.get(position);
				intent.putExtra("result", result);
				startActivity(intent);
;				
			}
		});
    }
    
    private String getFilterAPI(){
    	if (filters != null){
    		String out = "";
    		if (filters.getColor() != "") {out += "&imgcolor=" + filters.getColor();}
    		if (filters.getSize() != "") {out += "&imgsz=" + filters.getSize();}
    		if (filters.getType() != "") {out += "&imgtype=" + filters.getType();}
    		if (filters.getSite() != "") {out += "&as_sitesearch=" + filters.getSite();}
    		
    		return out;
    	}
    	return "";
    }
}
