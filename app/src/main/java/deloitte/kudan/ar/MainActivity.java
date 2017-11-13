package deloitte.kudan.ar;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import deloitte.kudan.ar.util.CustomTextAnimator;
import deloitte.kudan.ar.util.MarshMallowPermission;
import eu.kudan.kudan.ARAPIKey;
import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARArbiTrack;
import eu.kudan.kudan.ARGyroPlaceManager;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARImageTrackable;
import eu.kudan.kudan.ARImageTracker;

public class MainActivity extends ARActivity{
    private String deviceName = android.os.Build.MODEL;
    //private String deviceName = Settings.System.getString(getContentResolver(), "device_name");
    private static final String TAG = MainActivity.class.getSimpleName();
    //private GestureDetectorCompat gestureDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "In onCreate() method");
        // Comment this out for the time being unless you plan to create UI elements
        setContentView(R.layout.activity_main);

        ARAPIKey key = ARAPIKey.getInstance();
        Log.d(TAG, "Got ARAPIKey instance");
        key.setAPIKey("DdrfdSm3TE3XdO0IMuczUOMMB7Evph/4heQk0A8hCicbifpi/Xhvvxr/mEyfBkdJhBZrgyCQbBXIsssyvrw7CruPpfF/x9E4kjJPF1d+sL6y/lQqE1U+V51sMMnxeGV2xIYgtMXCHdRvIiuE9pfFlB6KdDM6a0jxMdYuZwSBZm8BFZ52kgV1YaCwOn/YpUcwKfn+BQ0Q14P32pl5djMwi5Ze77Z4ms8soJwDIPQSxWcOv6vxkgbk2UfuCqr0fLYVIIKZt/MOAOZsBFO69MVAa02c7cTigyJA6QsOK9Fjl5kAGja8/SYyW/YLOoGJQcn/nmF27vASAH+IyoOezCeg/AGVn9tF2x6votRcG9dcPUJ/080rbPlApRgWMilkFzSS8LaQBeo8QVfBNCwXYzKpbjBrSk5Av6YZs8qlAN7oGLsg/mpMtR2bd1qCek/V388a0FIWVA7+O0mUbgrxfHiJqQT6/LD0x8iumtuQ0DsgpOFWsKG6lp0ePw5cZE7lLUxlKl5oY+2bd0URn/O3YoV5Dx2Zs5FvOuXoa89TN04C3jytSNNvY+Jr/bw4N84oajVtWg9dgOIdWYLygY1/7nFrBHs4J9am0x6aAw9AqUiy1JDYze70OxlIy8Jk1A4RnNtbPeFicKvu8vTNZl8pX7M1lVaKElBndOLiZ+JxOAdyRkk=");
        Log.d(TAG, "After setting API Key");

        //This is for Marshmallow upgrade...
        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
        if (!marshMallowPermission.checkPermissionForCamera()) {
            Log.d(TAG, "No permission for camera...Requesting..");
            marshMallowPermission.requestPermissionForCamera();
        }

        // Create gesture recogniser to start and stop arbitrack
        //gestureDetect = new GestureDetectorCompat(this,this);

        showGreetingAndPickList();

    }

    @Override
    public void setup()
    {
        super.setup();
        Log.d(TAG, "In setup method");
        // AR Content to be set up here
        // Initialise the image trackable and load the image.
        /*ARImageTrackable imageTrackable = new ARImageTrackable("Lego");
        imageTrackable.loadFromAsset("KudanLegoMarker.jpg");
        //imageTrackable.loadFromPath("C:\\Users\\myunuskabir\\AndroidStudioProjects\\ar\\app\\src\\main\\assets\\Kudan Lego Marker.jpg", true);
        Log.d(TAG, "After loading lego marker");

        // Get the single instance of the image tracker.
        ARImageTracker imageTracker = ARImageTracker.getInstance();
        imageTracker.initialise();
        Log.d(TAG, "After getting image tracker instance");

        //Add the image trackable to the image tracker.
        try {
            imageTracker.addTrackable(imageTrackable);
            Log.d(TAG, "After adding trackable to image tracker instance");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // Initialise the image node with our image
        ARImageNode imageNode = new ARImageNode("KudanCow.png");

        // Add the image node as a child of the trackable's world
        imageTrackable.getWorld().addChild(imageNode);*/

        /////////////////////////////////////////////////////////////

        /*// Initialise ArbiTrack.
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();
        arbiTrack.initialise();

        // Initialise gyro placement.
        ARGyroPlaceManager gyroPlaceManager = ARGyroPlaceManager.getInstance();
        gyroPlaceManager.initialise();

        // Create a node to be used as the target.
        ARImageNode targetNode = new ARImageNode("CowTarget.png");

        // Add it to the Gyro Placement Manager's world so that it moves with the device's Gyroscope.
        gyroPlaceManager.getWorld().addChild(targetNode);

        // Rotate and scale the node to ensure it is displayed correctly.
        targetNode.rotateByDegrees(90.0f, 1.0f, 0.0f, 0.0f);
        targetNode.rotateByDegrees(180.0f, 0.0f, 1.0f, 0.0f);

        targetNode.scaleByUniform(0.3f);

        // Set the ArbiTracker's target node.
        arbiTrack.setTargetNode(targetNode);

        // Create a node to be tracked.
        ARImageNode trackingNode = new ARImageNode("CowTracking.png");

        // Rotate the node to ensure it is displayed correctly.
        trackingNode.rotateByDegrees(90.0f, 1.0f, 0.0f, 0.0f);
        trackingNode.rotateByDegrees(180.0f, 0.0f, 1.0f, 0.0f);

        // Add the node as a child of the ArbiTracker's world.
        arbiTrack.getWorld().addChild(trackingNode);*/
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e)
    {
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();

        // If arbitrack is tracking, stop tracking so that its world is no longer rendered, and make the target node visible.
        if (arbiTrack.getIsTracking())
        {
            arbiTrack.stop();
            arbiTrack.getTargetNode().setVisible(true);
        }

        // If it's not tracking, start tracking and hide the target node.
        else
        {
            arbiTrack.start();
            arbiTrack.getTargetNode().setVisible(false);
        }

        return false;
    }

    // We also need to implement the other overrides of the GestureDetector, though we don't need them for this sample.
    @Override
    public boolean onDown(MotionEvent e)
    {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e)
    {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        return false;
    }*/


    private void showGreetingAndPickList(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String wish = "";

        if(timeOfDay >= 0 && timeOfDay < 12){
            //Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
            wish = "Good Morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            //Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
            wish = "Good Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            //Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
            wish = "Good Evening";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            //Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
            wish = "Hello";
        }

        TextView mainView = (TextView)findViewById(R.id.textView5);
        mainView.setMovementMethod(new ScrollingMovementMethod());

        CustomTextAnimator animator = new CustomTextAnimator(mainView, new String[]{
                wish + " " + deviceName,
                "You have 2 picking items in your list..Do you want to proceed?"});

        animator.startAnimation();
    }

}
