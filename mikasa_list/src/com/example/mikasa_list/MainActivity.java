package com.example.mikasa_list;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mikasa_list.RefreshableView.PullToRefreshListener;

public class MainActivity extends ListActivity implements OnScrollListener {

	private ListView lv;
	private RefreshableView refreshableView;
	private Button btn;
	private View loadMoreView; 
	private int visibleLastIndex;
	private int firstVisibleItem;
	private int visibleItemCount;
	private ListViewAdapter adapter;
	private Handler mhandler = new Handler();
	private Boolean islast = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		//loadMoreView = getLayoutInflater().inflate(R.layout.activity_main, null);
		loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
		btn = (Button)loadMoreView.findViewById(R.id.loadMoreButton);
		lv = (ListView)findViewById(R.id.list_view);
		lv.addFooterView(loadMoreView);
		initadater();
		setListAdapter(adapter);
		lv.setOnScrollListener(this);
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {  
            @Override  
            public void onRefresh() {  
                try {  
                    Thread.sleep(3000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                refreshableView.finishRefreshing();  
            }  
        }, 0);  
	}
	private void initadater() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 10; i++){
			list.add(String.valueOf(i + 1));
		}
		adapter = new ListViewAdapter(this, list);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
		//public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub	
		this.firstVisibleItem = firstVisibleItem;
		//visibleItemCount = firstVisibleItem + visibleItemCount ;
		if(firstVisibleItem + visibleItemCount == totalItemCount){
			islast = true;
		}
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		//public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		int itemsLastIndex = adapter.getCount();
		int lastIndex = itemsLastIndex + 1 ;
		Log.i("TAG", String.valueOf(OnScrollListener.SCROLL_STATE_IDLE));
		if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
			visibleLastIndex = firstVisibleItem;
			if(islast){
				loadData();
				Toast.makeText(this, "正在加载中...", Toast.LENGTH_SHORT).show();
				islast = false;
			}
		}
		isFullScreen(scrollState);
	}

	private void isFullScreen(int scrollState) {
		Log.i("TAG", String.valueOf(visibleLastIndex));
		Log.i("TAG", String.valueOf(firstVisibleItem));
		if(scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL && visibleLastIndex < firstVisibleItem){
			WindowManager.LayoutParams params = getWindow().getAttributes();
			params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN; 
			getWindow().setAttributes(params);  
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);			
		}else if(scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL && visibleLastIndex > firstVisibleItem){
			WindowManager.LayoutParams params = getWindow().getAttributes();
			params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);  
			getWindow().setAttributes(params);  
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);	
		}

	}
	public void loadMore(View view) {  
		btn.setText("加载中...");
		loadData();
	}
	private void loadData() {
		mhandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int count = adapter.getCount();
				for(int i = count; i < count + 10; i++){
					adapter.additem(String.valueOf(i + 1));
				}
				adapter.notifyDataSetChanged();
				lv.setSelection(visibleLastIndex);
				btn.setText("加载更多...");
			}
		}, 2000);

	}
}
