package com.mefistofelerion.justrun;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;

/**
 * Created by Ivan on 10/22/14.
 */
public class GUIManager {
    private View overlayView;
    private Button recoverButton;
    private Button shooterButton;

    static class MyActivityHandler extends Handler
    {
        private WeakReference<GUIManager> guiManager;
        private Context context;


        MyActivityHandler(GUIManager guim, Context c)
        {

        }

        public void handleMessage(Message msg)
        {

            Button shooterButton = guiManager.get().shooterButton;
            Button recoverButton = guiManager.get().recoverButton;

            switch (msg.what)
            {
                case SHOTTER_BUTTON:
                    if (shooterButton != null)
                    {
                        shooterButton.setVisibility(View.VISIBLE);
                    }
                    break;

                case RECOVER_BUTTON:
                    if (shooterButton != null)
                    {
                        shooterButton.setVisibility(View.GONE);
                    }
                    break;

                default:
                    break;
            }



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
        overlayView = View.inflate(context, R.layout.interface_overlay, null);

        // Create a Handler from the current thread:
        // This is the only thread that can make changes to the GUI,
        // so we require a handler for other threads to make changes
        mainActivityHandler = new MyActivityHandler(this, context);


    }


    /** Button clicks should call corresponding native functions. */
    public void initButtons()
    {
        if (overlayView == null)
            return;

        startButton = (ToggleButton)overlayView.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (((ToggleButton) v).isChecked())
                {
                   //make a start
                }
                else
                {
                    //quit
                }
            }
        });



        shooterButton = (Button) overlayView.findViewById(R.id.shooter_button);
        shooterButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //make listener when shoot
            }
        });

    }


    /** Clear the button listeners. */
    public void deinitButtons()
    {
        if (overlayView == null)
            return;

        if (shooterButton != null)
        {
            shooterButton.setOnClickListener(null);
            shooterButton = null;
        }


        if (recoverButton != null)
        {
            recoverButton.setOnClickListener(null);
            recoverButton = null;
        }

    }


    /** Send a message to our gui thread handler. */
    public void sendThreadSafeGUIMessage(Message message)
    {
        mainActivityHandler.sendMessage(message);
    }


    /** Getter for the overlay view. */
    public View getOverlayView()
    {
        return overlayView;
    }
}


