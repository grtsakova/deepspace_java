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
public class NumericDamage extends Damage {
    
    private int nWeapons;
    
    NumericDamage(int w,int s){
        super(s);
        nWeapons = w;
    }
    
    public NumericDamage copy(){
       return new NumericDamage(nWeapons,nShields);
    }
    
    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    @Override
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        return new NumericDamage(Math.min(nWeapons, w.size()),Math.min(nShields, s.size()));
    }
    
    @Override
    public void discardWeapon(Weapon w){
        if (nWeapons > 0) {
            nWeapons--;
        }
    }
    
    @Override
    public boolean hasNoEffect(){
        return super.hasNoEffect() && nWeapons == 0;
    }
   
    @Override
    public String toString(){
        return super.toString() + " " + nWeapons;
    }
}
