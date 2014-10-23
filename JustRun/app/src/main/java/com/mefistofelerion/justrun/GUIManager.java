package com.mefistofelerion.justrun;

import android.content.Context;
import android.os.Message;
import android.view.View;

/**
 * Created by Ivan on 10/22/14.
 */
public class GUIManager {
    static class MyActivityHandler extends Handler
    {
        private WeakReference<GUIManager> guiManager;
        private Context context;

        MyActivityHandler(GUIManager guim, Context c)
        {

        }

        public void handleMessage(Message msg)
        {



        }
    }
    private MyActivityHandler mainActivityHandler;

    // Flags for our Handler:
    public static final int SHOTTER_BUTTON = 0;
    public static final int RECOVER_BUTTON = 1;

    // Native methods to handle button clicks:
    public native void nativeStart();
    public native void nativeClear();
    public native void nativeReset();
    public native void nativeDelete();


    /** Initialize the GUIManager. */
    public GUIManager(Context context)
    {
        // Load our overlay view:
        // NOTE: This view will add content on top of the camera / OpenGL view


        // Create a Handler from the current thread:
        // This is the only thread that can make changes to the GUI,
        // so we require a handler for other threads to make changes

    }


    /** Button clicks should call corresponding native functions. */
    public void initButtons()
    {

    }


    /** Clear the button listeners. */
    public void deinitButtons()
    {

    }


    /** Send a message to our gui thread handler. */
    public void sendThreadSafeGUIMessage(Message message)
    {

    }


    /** Getter for the overlay view. */
    public View getOverlayView()
    {

    }
}


