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
    private ToggleButton recoverButton;
    private Button shotterButton;

    static class MyActivityHandler extends Handler
    {
        private WeakReference<GUIManager> guiManager;
        private Context context;


        MyActivityHandler(GUIManager guim, Context c)
        {

        }

        public void handleMessage(Message msg)
        {

            Button shotterButton = guiManager.get().shotterButton;
            ToggleButton recoverButton = guiManager.get().recoverButton;

            switch (msg.what)
            {
                case SHOTTER_BUTTON:
                    if (shotterButton != null)
                    {
                        shotterButton.setVisibility(View.VISIBLE);
                    }
                    break;

                case RECOVER_BUTTON:
                    if (shotterButton != null)
                    {
                        shotterButton.setVisibility(View.GONE);
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
                    nativeStart();
                }
                else
                {
                    nativeReset();
                }
            }
        });



        shooter = (Button) overlayView.findViewById(R.id.shooter_button);
        shooter.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                nativeDelete();
            }
        });

    }


    /** Clear the button listeners. */
    public void deinitButtons()
    {

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


