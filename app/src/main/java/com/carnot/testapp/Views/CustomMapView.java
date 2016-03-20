package com.carnot.testapp.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * CustomMapView for all map-based displays in the app.
 * In particular, used in the {@link com.carnotlabs.carnotapp.Fragments.ParkedFragment}
 * and {@link com.carnotlabs.carnotapp.Fragments.PostDriveFragment} Fragments. MapView concept borrowed from
 * SO: http://stackoverflow.com/questions/6546108/mapview-inside-a-scrollview, Answer #1, Comment #2.
 *
 * @author PJ
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomMapView extends MapView
{
    /**
     * Instantiates a new Custom map view.
     *
     * @param context the context
     */
    public CustomMapView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Custom map view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Custom map view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public CustomMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Instantiates a new Custom map view.
     *
     * @param context the context
     * @param options the options
     */
    public CustomMapView(Context context, GoogleMapOptions options) {
        super(context, options);
    }

    @Override
    public void getMapAsync(OnMapReadyCallback callback) {
        super.getMapAsync(callback);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Disallow ScrollView to intercept touch events.
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_UP:
                // Allow ScrollView to intercept touch events.
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        // Handle MapView's touch events.
        super.dispatchTouchEvent(ev);
        return true;
    }
}
