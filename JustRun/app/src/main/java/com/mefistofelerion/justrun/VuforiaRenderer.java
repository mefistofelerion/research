package com.mefistofelerion.justrun;
import android.app.Activity;
import android.content.Context;

import com.qualcomm.QCAR.QCAR;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * This class will be the one who expects changes on the image.
 * Created by Ivan Guerra on 10/12/14.
 */
public class VuforiaRenderer extends Activity {


    public VuforiaRenderer(Context context){
        //get camera from device
        //create matrix where it will be view
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig config){
        //make sure everything is initialized with initRendering()
        QCAR.onSurfaceCreated();
    }

    public void onSurfaceChanged(GL10 gl10, int width, int height){
        //updateRendering(int width, height)
        QCAR.onSurfaceChanged(width,height);
        //create new instance of targetRenderer
    }

    public void onDestroyedEnemy(){
        //eliminate the instance of object which is the enemy when hit
    }

    public void createEnemy(){
        //calculate the direction where it can be created
    }

}
