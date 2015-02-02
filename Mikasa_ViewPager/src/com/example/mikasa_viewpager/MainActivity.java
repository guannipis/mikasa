package com.example.mikasa_viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	public static final int TAB_HOME = 0;
	public static final int TAB_BUY = 1;
	public static final int TAB_CAR = 2;
	public static final int TAB_MORE = 3;
	
	private ViewPager pager;
	private RadioButton homebtn, carbtn, buybtn, morebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        addListenter();

    }



	private void initview() {
		pager = (ViewPager)findViewById(R.id.pager);
		homebtn = (RadioButton)findViewById(R.id.home);
		buybtn = (RadioButton)findViewById(R.id.buy);
		carbtn = (RadioButton)findViewById(R.id.car);
		morebtn = (RadioButton)findViewById(R.id.more);
		homebtn.setOnClickListener(this);
		buybtn.setOnClickListener(this);
		carbtn.setOnClickListener(this);
		morebtn.setOnClickListener(this);
		
		FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
	}

    private void addListenter() {
		// TODO Auto-generated method stub
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int id) {
				switch (id) {
				case TAB_HOME:
					homebtn.setChecked(true);
					break;
				case TAB_BUY:
					buybtn.setChecked(true);
					break;
				case TAB_CAR:
					carbtn.setChecked(true);
					break;
				case TAB_MORE:
					morebtn.setChecked(true);
					break;

				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.home:
			pager.setCurrentItem(TAB_HOME);
			break;
		case R.id.buy:
			pager.setCurrentItem(TAB_BUY);
			break;
		case R.id.car:
			pager.setCurrentItem(TAB_CAR);
			break;
		case R.id.more:
			pager.setCurrentItem(TAB_MORE);
			break;
			
		default:
			break;
		}
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


}
