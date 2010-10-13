package com.ccmedia.parse.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.ccmedia.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultAdapter extends ArrayAdapter<VideoEntity> {
    private ArrayList<VideoEntity> items;
    private Activity act;
    private int textViewResourceId;
    private Resources rs;
    private Drawable original = null;
    
    private int selectedPos = -1;	// init value for not-selected
    
    public void clearSelection(){
    	selectedPos = -1;
    }
    
    public SearchResultAdapter(Context context, int textViewResourceId, ArrayList<VideoEntity> items, Activity act, Resources rs) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.act = act;
        this.textViewResourceId = textViewResourceId;
        this.rs = rs;
        //init();
    }
    
    public void init(){
    }
    
    public void setSelectedPosition(int pos){
		selectedPos = pos;
		// inform the view of this change
		notifyDataSetChanged();
	}
    
	public int getSelectedPosition(){
		return selectedPos;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
  
        
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(textViewResourceId, null);
        }
       
        String resultThumbnail = items.get(position).getThumbnailUrl();
        if (resultThumbnail != null) {
        	InputStream is;
        	Context context = act;
			try {
				is = (InputStream) this.fetch(resultThumbnail);
				Drawable d = Drawable.createFromStream(is, "src");
				
				ImageView imgView = new ImageView(context);
				imgView = (ImageView)v.findViewById(R.id.search_result_thumbnail);
				imgView.setImageDrawable(d);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
        
        String resultTitle = items.get(position).getTitle();
        if (resultTitle != null) {
            TextView txtName = (TextView) v.findViewById(R.id.search_result_title);
            if (txtName != null) txtName.setText( String.format("%s", resultTitle) );
        }
        String resultDescription = items.get(position).getDescription();
        if (resultDescription != null) {
            TextView txtName = (TextView) v.findViewById(R.id.search_result_description);
            if (txtName != null) txtName.setText( String.format("%s", resultDescription) );
        }
       
//        View label = (View)v.findViewById(R.id.search_result_);
//        if(original == null){
//        	original = label.getBackground();
//        	System.out.println("original: "+original);
//        }
        // change the row color based on selected state
        if(selectedPos == position){
        	
        	//label.setBackgroundDrawable(rs.getDrawable(R.drawable.play_top_lev));
        	//label.setBackgroundColor(Color.CYAN);
        }else{
        	//label.setBackgroundColor(Color.WHITE);
        	//label.setBackgroundDrawable(rs.getDrawable(R.drawable.translucent_background));
        }

        
        return v;
    }
    
    public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
    
    public ArrayList<VideoEntity> getItems(){
    	return items;
    }
    
    public void setItems(ArrayList<VideoEntity> items){
    	this.items = items;
    	clearSelection();
    }
    
    public VideoEntity getSelectedItem(){
    	return items.get(getSelectedPosition());
    }
}