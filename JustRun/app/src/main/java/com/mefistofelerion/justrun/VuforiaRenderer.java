package com.mefistofelerion.justrun;
import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.opengl.GLSurfaceView;

import com.qualcomm.QCAR.QCAR;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * This class will be the one that expects changes on the image.
 * Created by Ivan Guerra on 10/12/14.
 */
public class VuforiaRenderer implements GLSurfaceView.Renderer{

    private Player player;
    private  Creature creature;

    public boolean mIsActive = false;

    private GUIManager mGUIManager;

    /** Native function for initializing the renderer. */
    public native void initRendering();


    /** Native function to update the renderer. */
    public native void updateRendering(int width, int height);


    /** Native function to store Java environment information for callbacks. */
    public native void initNativeCallback();


    public VuforiaRenderer(Context context){
        //get camera from device
        //create matrix where it will be view
        player = new Player();
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig config){
        LoggerHelper.debug("GLRenderer::onSurfaceChanged");

        // Call native function to update rendering when render surface
        // parameters have changed:
        updateRendering(width, height);

        // Call Vuforia function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
    }

    public void onSurfaceChanged(GL10 gl10, int width, int height){
        //updateRendering(int width, height)
        QCAR.onSurfaceChanged(width,height);
        //create new instance of targetRenderer
    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    public void onDestroyedEnemy(){
        //eliminate the instance of object which is the enemy when hit
    }

    public void createEnemy(){
        List<Creature> creatures = new ArrayList<Creature>();
        Creature creature = new Creature();
        creature.typeOfCreature("red");
        creatures.add(creature.build());

        Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(Initial.getResources().getDrawable(R.drawable.ic_launcher)), 64, 64));
        TextureManager.getInstance().addTexture("texture", texture);


        cam = this.getCamera();
        cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
        cam.lookAt(creature.getTransformedCenter());

        SimpleVector sv = new SimpleVector();
        sv.set(creature.getTransformedCenter());
        sv.y -= 100;
        sv.z -= 100;
        creature.setPosition(sv);
        MemoryHelper.compact();
    }

    public void onHit(/*context, enemy*/){
        //which one is hit
        creature= getHitCreature();
        creatureGetHit();
    }

    public Creature getHitCreature(/*int posx int posy*/ ){
        return null; //will return requested creature depending in where it is positioned
    }

    public void userGetHit(){//will reduce the health from user when get hit
        player.getHit();
    }

    public void creatureGetHit(){//reduce the health of creature hit
        creature.getHit();
    }

}
