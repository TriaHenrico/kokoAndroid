package com.example.coba;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends ActionBarActivity {

	//URL to get JSON Array
    private static String url = "http://jsonplaceholder.typicode.com/posts";
 
    //JSON Node Names
    private static final String TAG_USER = "userId";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_BODY = "body";
    TextView name1;
    TextView body1;
    
    JSONArray user = null;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.activity_main);
        
        new AsyncTaskParseJson().execute();
    	
    }
    
    public class AsyncTaskParseJson extends AsyncTask<String, String, ArrayList<String>> {

        //final String TAG = "AsyncTaskParseJson.java";


        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected ArrayList<String> doInBackground(String... arg0) {

        	// Creating new JSON Parser
            JSONParser jParser = new JSONParser();
     
            // Getting JSON from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            String title;
            String body;
            ArrayList<String> semua = new ArrayList<String>();
            try {
                // Getting JSON Array
	            for (int i = 0; i < json.length(); i++) { 
            		JSONObject c = json.getJSONObject(i);
     
    	            // Storing  JSON item in a Variable
    	            //String id = c.getString(TAG_ID);
    	            title = c.getString(TAG_TITLE);
    	            body = c.getString(TAG_BODY);
    	 
    	            //Importing TextView
    	            //final TextView uid = (TextView)findViewById(R.id.uid);
    	            //final TextView email1 = (TextView)findViewById(R.id.email);
            	    
    	        
    	            //Set JSON Data in TextView
    	            //uid.setText(id);
    				Log.v("log_v", "Title " + title);
    	            //email1.setText(body);
    				semua.add(title);
    				semua.add(body);
            	}
	            return semua;
     
    		    } catch (JSONException e) {
    		        e.printStackTrace();
    		    }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<String> strFromDoInBg) {
        	name1 = (TextView)findViewById(R.id.name);
            name1.setText(strFromDoInBg.get(0));
        	body1 = (TextView)findViewById(R.id.email);
            body1.setText(strFromDoInBg.get(1));
        
        }
    }

}
