package com.example.gridimagesearch.models;

import android.R.integer;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

public abstract class EndlessScrollListener implements OnScrollListener {
	
	private GridView gridView;
    private boolean isLoading;
    private int pageNumber;
	
	public EndlessScrollListener(GridView gridView) {
        this.gridView = gridView;
        this.isLoading = false;
        this.pageNumber = 0;
    }
	
	// FIXME: page number not using
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (gridView.getLastVisiblePosition() + 1 >= totalItemCount && !isLoading) {
			isLoading = true;
			pageNumber++;
			onLoadMore(pageNumber, totalItemCount);
		} 
		else if (gridView.getLastVisiblePosition() + 1 < totalItemCount && isLoading){
			isLoading = false;
		}
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// Do nothing
	}
	
	public abstract void onLoadMore(int page, int totalItemCount);
	

}
