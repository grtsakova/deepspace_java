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
public abstract class Damage {
    
    protected int nShields;
    
    Damage(int s){
        nShields = s;
    }
    
    public abstract Damage copy();
    
    abstract DamageToUI getUIversion();
   
    public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    public abstract void discardWeapon(Weapon w);
    
    public void discardShieldBooster(){
        if(nShields > 0){
            nShields--;
        }
    }
    
    public boolean hasNoEffect(){
        return nShields == 0;      
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public String toString(){
        return "Shields: " + nShields;
    }
}
