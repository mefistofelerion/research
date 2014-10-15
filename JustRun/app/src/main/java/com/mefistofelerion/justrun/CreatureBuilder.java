package com.mefistofelerion.justrun;

/**
 * Created by Ivan on 10/14/14.
 */
public abstract class CreatureBuilder {
    String type;

   public void typeOfCreature(String type){
       this.type = type;
   }

    public Creature build(){
        return new Creature();
    }
}
