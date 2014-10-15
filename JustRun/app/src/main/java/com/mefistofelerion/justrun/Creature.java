package com.mefistofelerion.justrun;

/**
 * Created by Ivan on 10/14/14.
 */
public class Creature extends CreatureBuilder{

    public Creature(){}

    public void typeOfCreature(String type){
        //set type of creature and give its form
    }

    public Creature getNewCreature(){
        return build();
    }
}
