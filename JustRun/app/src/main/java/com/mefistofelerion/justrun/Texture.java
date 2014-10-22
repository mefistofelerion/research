/**
 * Created by Ivan on 10/21/14.
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/** Texture is a support class for the Vuforia samples applications.
 *
 *  Exposes functionality for loading a texture from the APK.
 *
 * */

public class Texture {
    public int mWidth;      /// The width of the texture.
    public int mHeight;     /// The height of the texture.
    public int mChannels;   /// The number of channels.
    public byte[] mData;    /// The pixel data.

    /**
     * Returns the raw data
     */
    public byte[] getData() {
        return mData;
    }
}
