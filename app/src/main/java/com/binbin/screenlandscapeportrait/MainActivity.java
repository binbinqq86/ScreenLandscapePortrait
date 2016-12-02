package com.binbin.screenlandscapeportrait;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.binbin.screenlandscapeportrait.fragment.Fragment1;
import com.binbin.screenlandscapeportrait.fragment.Fragment2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3;
    private int orientation;
    private MySensorEventListener mySensorEventListener;
    private int sh,sw;
    private ViewPager vp;
    private ArrayList<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        mySensorEventListener = new MySensorEventListener(getApplicationContext(), SensorManager.SENSOR_DELAY_FASTEST);
        mySensorEventListener.enable();
        Log.e("tianbin","=========onCreate===========");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"1111111111111111",Toast.LENGTH_SHORT).show();
            }
        });

        vp= (ViewPager) findViewById(R.id.vp);
        fragmentList=new ArrayList<>();
        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                if (fragmentList != null)
                    return fragmentList.size();
                else return 0;
            }
        };
        vp.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sh=getWindowManager().getDefaultDisplay().getHeight();
                sw=getWindowManager().getDefaultDisplay().getWidth();
//                fragment1.handleRotate(2);
            }
        },1000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("tianbin","=========onRestart===========");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("tianbin","=========onStart===========");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("tianbin","=========onPause===========");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("tianbin","=========onStop===========");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tianbin","=========onResume===========");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("tianbin","=========onRestoreInstanceState===========");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("tianbin","=========onSaveInstanceState===========");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tianbin","=========onDestroy===========");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.e("tianbin","=========onRetainCustomNonConfigurationInstance===========");
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("tianbin","=========onConfigurationChanged===========");

        setContentView(R.layout.activity_main);//重新设置布局后fragment中的改变不起作用
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        vp= (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(adapter);
        vp.invalidate();

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 针对横屏做一些处理
            tv2.setText("heng==============");
            full(true);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // 针对竖屏做一些处理
            tv2.setText("shu==============");
            full(false);
        }


        int mCurrentOrientation = getResources().getConfiguration().orientation;
//        Log.e("tianbin",mCurrentOrientation+"@@@"+newConfig.orientation);
        Display display = getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        tv3.setText(rotation+"===========");
    }

    class MySensorEventListener extends OrientationEventListener {

        public MySensorEventListener(Context context, int rate) {
            super(context, rate);
        }

        public MySensorEventListener(Context context) {
            super(context);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                return;  //手机平放时，检测不到有效的角度
            }
            //只检测是否有四个角度的改变
            if (orientation > 350 || orientation < 10) { //0度——竖屏
                orientation = 0;
                MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if (orientation > 80 && orientation < 100) { //90度——横屏反向
                orientation = 90;
                MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            } else if (orientation > 170 && orientation < 190) { //180度——倒坚屏
                orientation = 180;
                MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if (orientation > 260 && orientation < 280) { //270度——横屏
                orientation = 270;
                MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                return;
            }
//            Log.e("MyOrientationDetector ", "onOrientationChanged:" + orientation);
            tv1.setText(orientation + "");
        }
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp =  getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
