package com.mefistofelerion.justrun;

/**
 * Created by Ivan on 11/3/14.
 */


        import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.qualcomm.vuforia.Matrix44F;
import com.qualcomm.vuforia.Renderer;
import com.qualcomm.vuforia.State;
import com.qualcomm.vuforia.Tool;
import com.qualcomm.vuforia.Trackable;
import com.qualcomm.vuforia.TrackableResult;
import com.qualcomm.vuforia.VIDEO_BACKGROUND_REFLECTION;
import com.qualcomm.vuforia.Vuforia;


import java.io.IOException;
import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


// The renderer class for the ImageTargets sample.
public class ImageTargetRenderer implements GLSurfaceView.Renderer
{




    public ImageTargetRenderer(ImageTargets activity,
                               SampleApplicationSession session)
    {
        mActivity = activity;
        vuforiaAppSession = session;
    }


    // Called to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl)
    {

    }


    // Called when the surface is created or recreated.
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {

    }


    // Called when the surface changed size.
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
    }


    // Function for initializing the renderer.
    private void initRendering()
    {

        }



    }


    // The render function.
    private void renderFrame()
    {

    }


    private void printUserData(Trackable trackable)
    {

    }


    public void setTextures(Vector<Texture> textures)
    {
        

    }

}
