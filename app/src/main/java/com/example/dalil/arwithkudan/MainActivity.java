package com.example.dalil.arwithkudan;

import android.os.Bundle;
import android.util.Log;

import eu.kudan.kudan.ARAPIKey;
import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARArbiTrack;
import eu.kudan.kudan.ARGyroPlaceManager;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARImageTrackable;
import eu.kudan.kudan.ARImageTracker;


public class MainActivity extends ARActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Comment this out for the time being unless you plan to create UI elements
        //setContentView(R.layout.activity_main);

        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey("agWZcpYLYjBxCbWf2qZx6k+PWISqeGtFCqKaZwYtwS+kdn1HKiQAmsJ55STRBe9BqCw3VwG6qL+ESI5ntTF/iV/uekLG3PCokaUE0/uTzqhaYlxRdmuNBIduzBCjq3mV2na+gy3ffHH9Ipc7eIN0geTj3p+ppsmK0U399iGmN38ndIh6k2y16cByWIecMSU3yw3Ztw7gHRqf83hVhZ5T2ACGK4SNkQhhdKp+CTaR5W3amYCJBgwumqFqNFyI9UniuMk70T/cQObRQum2U51OjjbMfmEAwIBt8Q8jD2yACzye6K4/1O4pZhbGEbiDeLrAfxqMwBAe5o6vnYIilGNnpDhfi3wOHhRaqtLOVvB58GUIFTnAPvmYFVnLWRJmCUZ9FJNDyX3ALCl/alFEWh+A/a6NFjcwLGKI9drPuGG4ONFg4p0l+p3b9DZoLzszlmWAflI/UFzQa++kQn3/sclO9i0vPnpi0LWoABm5vGswLVAIX/0k6384GXxfkADI6fjGtf62XJ5ImaVDiiREa9mabWEQGoifghQG1sGNDYgBIYEpiaLsVzOfTALpe20Q7kFCMjedJImQhhuLtEK1BXfXJEed1QqUOsG9IeKxKk28GbOtOF9w3yrSF3gnJslzZxF2kEF3C6ckog8byagS+4p37FJmbpPsiKNH1Qm0LuouGcQ=");
    }

    @Override
    public void setup()
    {
        Log.d(TAG, "setup: called");
        super.setup();

        // AR Content to be set up here

        // Initialise the image trackable and load the image.
        Log.d(TAG, "setup: 1");
        ARImageTrackable imageTrackable = new ARImageTrackable("Lego Marker");
        imageTrackable.loadFromAsset("Kudan Lego Marker.jpg");

        // Get the single instance of the image tracker.
        Log.d(TAG, "setup: 2");
        ARImageTracker imageTracker = ARImageTracker.getInstance();
        imageTracker.initialise();

        //Add the image trackable to the image tracker.
        Log.d(TAG, "setup: 3");
        imageTracker.addTrackable(imageTrackable);

        // Initialise the image node with our image
        Log.d(TAG, "setup: 4");
        ARImageNode imageNode = new ARImageNode("Kudan Cow.png");

        // Add the image node as a child of the trackable's world
        Log.d(TAG, "setup: 5");
        imageTrackable.getWorld().addChild(imageNode);

        // Initialise ArbiTrack.
        Log.d(TAG, "setup: 6");
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();
        arbiTrack.initialise();

        // Initialise gyro placement.
        Log.d(TAG, "setup: 7");
        ARGyroPlaceManager gyroPlaceManager = ARGyroPlaceManager.getInstance();
        gyroPlaceManager.initialise();

        // Create a node to be used as the target.
        Log.d(TAG, "setup: 8");
        ARImageNode targetNode = new ARImageNode("Arrow.png");

        // Add it to the Gyro Placement Manager's world so that it moves with the device's Gyroscope.
        Log.d(TAG, "setup: 9");
        gyroPlaceManager.getWorld().addChild(targetNode);

        // Rotate and scale the node to ensure it is displayed correctly.
        Log.d(TAG, "setup: 10");
        targetNode.rotateByDegrees(0.0f, 1.0f, 0.0f, 0.0f);
        targetNode.rotateByDegrees(90.0f, 0.0f, 1.0f, 0.0f);

        targetNode.scaleByUniform(0.3f);

        // Set the ArbiTracker's target node.
        Log.d(TAG, "setup: 11");
        arbiTrack.setTargetNode(targetNode);

        // Create a node to be tracked.
        ARImageNode trackingNode = new ARImageNode("Cow Tracking.png");

        // Rotate the node to ensure it is displayed correctly.
        trackingNode.rotateByDegrees(90.0f, 1.0f, 0.0f, 0.0f);
        trackingNode.rotateByDegrees(180.0f, 0.0f, 1.0f, 0.0f);

        // Add the node as a child of the ArbiTracker's world.
        arbiTrack.getWorld().addChild(trackingNode);

        Log.d(TAG, "setup: method finished");
    }
}
