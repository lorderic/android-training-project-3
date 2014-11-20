package com.example.gridimagesearch.activities;

import com.example.gridimagesearch.R;
import com.example.gridimagesearch.R.id;
import com.example.gridimagesearch.R.layout;
import com.example.gridimagesearch.R.menu;
import com.example.gridimagesearch.models.SearchFilters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {
	
	private Spinner spinnerSize, spinnerColor, spinnerType;
	private EditText etSite;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void saveAndReturn(View v){
		spinnerSize = (Spinner) findViewById(R.id.spinnerSize);
		spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
		spinnerType = (Spinner) findViewById(R.id.spinnerType);
		etSite = (EditText) findViewById(R.id.etSite);
		
		SearchFilters filters = new SearchFilters();
		//filters.setSize(String.valueOf(spinnerSize.getSelectedItem()));
		filters.setSize(spinnerSize.getSelectedItem().toString());
		filters.setColor(spinnerColor.getSelectedItem().toString());
		filters.setType(spinnerType.getSelectedItem().toString());
		filters.setSite(etSite.getText().toString());
		
		Intent intent = new Intent();
		intent.putExtra("filters", filters);
		setResult(RESULT_OK, intent);
		
		finish();
	}
	
}
