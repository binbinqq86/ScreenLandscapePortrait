package com.binbin.screenlandscapeportrait.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.binbin.screenlandscapeportrait.MainActivity;
import com.binbin.screenlandscapeportrait.R;

/**
 * Created by -- on 2016/11/2.
 */

public class Fragment1 extends Fragment {

    private static final String TAG="Fragmeng1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.view, container, false);
        view.setBackgroundColor(Color.CYAN);
        ((TextView)view.findViewById(R.id.tv)).setText("fragment1===========");
        Log.e(TAG,"=====================onCreateView=========================");
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"======================onDestroyView========================");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"========================onAttach======================");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"======================onCreate========================");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"=====================onViewCreated=========================");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"======================onActivityCreated========================");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"======================onStart========================");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"====================onResume==========================");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG,"=================onSaveInstanceState=============================");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG,"==================onConfigurationChanged============================");
        handleRotate(newConfig.orientation);
    }

    public void handleRotate(int orientation){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViewsInLayout();
        View newView = onCreateView(inflater,rootView,null);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            newView.setBackgroundColor(Color.BLUE);
            ((TextView)newView.findViewById(R.id.tv)).setText("fragment1=====hhhhhhhhhhhhhh======");
        }else if (orientation == Configuration.ORIENTATION_PORTRAIT){
            newView.setBackgroundColor(Color.CYAN);
            ((TextView)newView.findViewById(R.id.tv)).setText("fragment1===========");
        }
        rootView.addView(newView);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"====================onPause==========================");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"====================onStop==========================");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"=======================onDestroy=======================");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"===================onDetach===========================");
    }
}
