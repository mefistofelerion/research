package com.mefistofelerion.justrun;

/**
 * Created by Ivan on 11/11/14.
 */
import java.nio.Buffer;
        import java.nio.ByteBuffer;
        import java.nio.ByteOrder;


public abstract class MeshObject
{

    public enum BUFFER_TYPE
    {
    }


    public Buffer getVertices()
    {
    }


    public Buffer getTexCoords()
    {
    }


    public Buffer getNormals()
    {
    }


    public Buffer getIndices()
    {
    }


    protected Buffer fillBuffer(double[] array)
    {
    }
}
