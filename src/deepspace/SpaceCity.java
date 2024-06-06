/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author gerga
 */
public class SpaceCity extends SpaceStation {
    
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;
    
    public SpaceCity(SpaceStation base,ArrayList<SpaceStation> rest){
        super(base);
        this.base = base;
        collaborators = new ArrayList<SpaceStation>();
        for(int i = 0; i < rest.size(); i++){
            collaborators.add(rest.get(i));
        }
    }
    
    public SpaceCityToUI getUIversion(){
        return new SpaceCityToUI(this);
    }
    
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    @Override
    public float fire(){
        float fire = super.fire();
        for(int i = 0; i < collaborators.size(); i++){
            fire += collaborators.get(i).fire();
        }
        return fire;
    }
    
    @Override
    public float protection(){
       float protection = super.protection();
       for(int i = 0; i < collaborators.size(); i++){
           protection += collaborators.get(i).protection();
       }
       return protection;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public String toString(){
       return super.toString() + "Base: " + base + "Collaborators: " + collaborators;
    }
    
}
