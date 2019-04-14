package com.smartjobs.smartjobs.activity;

/**
 * Created by bjean on 9/19/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewClickManager implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
//this is the click interface for both click and longClick

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        //void onItemLongClick(View view, int position);
    }



    private GestureDetector mGestureDetector;

    private RecyclerView mRecyclerView;

    public RecyclerViewClickManager(Context context, OnItemClickListener listener) {
        mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        /*@Override
        public void onLongPress(MotionEvent e) {
            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null) {
                mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
            }
        } */
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());



        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}