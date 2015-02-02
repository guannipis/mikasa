package com.example.mikasa_list;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	
	private List<String> items;
	private LayoutInflater inflater;
	
	public ListViewAdapter(Context context, List<String> list){
		items = list;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewholder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_item, null);
			viewholder = new ViewHolder();
			viewholder.text = (TextView)convertView.findViewById(R.id.list_item_text);
			convertView.setTag(viewholder);
		}else{
			viewholder = (ViewHolder)convertView.getTag();
		}
		viewholder.text.setText(items.get(position));
		return convertView;
	}
	
	class ViewHolder{
		private TextView text;
	}

	public void additem(String item){
		items.add(item);
	}
}
