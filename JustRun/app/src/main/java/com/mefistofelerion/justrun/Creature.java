package com.mefistofelerion.justrun;

import java.nio.Buffer;
import java.util.Random;

/**
 * Created by Ivan on 10/14/14.
 */
public class Creature extends CreatureBuilder {

    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public Creature() {
        this.health = FULL_HEALTH;
        this.dead = false;
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    public void typeOfCreature(String type) {
        //set type of creature and give its form
    }

    public Creature getNewCreature() {
        return build();
    }


    private static final int FULL_HEALTH = 100;
    private static final int CRITICAL_HIT = 10;
    private static final int HIT = 5;

    private boolean dead;
    private int health;


    public void getHit() {
        Random rand = new Random();
        int n = rand.nextInt(1);
        boolean isCritical = n == 1 ? true : false;
        if (isCritical) {
            if (this.health > 0)
                this.health -= CRITICAL_HIT;
            else
                this.dead = true;
        } else {
            this.health -= HIT;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }



        private void setVerts()
        {
            double[] CREATURE_VERTS = {
            mVertBuff = fillBuffer(CREATURE_VERTS);
            verticesNumber = CREATURE_VERTS.length / 3;
        }


        private void setTexCoords()
        {
            double[] CREATURE_TEX_COORDS = { };
            mTexCoordBuff = fillBuffer(CREATURE_TEX_COORDS);

        }


        private void setNorms()
        {
            double[] CREATURE_NORMS = { };
            mNormBuff = fillBuffer(CREATURE_NORMS);
        }


        private void setIndices()
        {
            short[] CREATURE_INDICES = {  };
            mIndBuff = fillBuffer(CREATURE_INDICES);
            indicesNumber = CREATURE_INDICES.length;
        }


        public int getNumObjectIndex()
        {
            return indicesNumber;
        }


        @Override
        public int getNumObjectVertex()
        {
            return verticesNumber;
        }


        @Override
        public Buffer getBuffer(BUFFER_TYPE bufferType)
        {
            Buffer result = null;
            switch (bufferType)
            {
                case BUFFER_TYPE_VERTEX:
                    result = mVertBuff;
                    break;
                case BUFFER_TYPE_TEXTURE_COORD:
                    result = mTexCoordBuff;
                    break;
                case BUFFER_TYPE_NORMALS:
                    result = mNormBuff;
                    break;
                case BUFFER_TYPE_INDICES:
                    result = mIndBuff;
                default:
                    break;

            }

            return result;
        }



}
