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
public class Hangar {
    
    private int maxElements;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    
    Hangar (int capacity){
       maxElements = capacity;
       weapons = new ArrayList<Weapon>();
       shieldBoosters = new ArrayList<ShieldBooster>();
    }
    
    Hangar (Hangar h){
        maxElements = h.maxElements;
        weapons = h.weapons;
        shieldBoosters = h.shieldBoosters;
    }
    
    HangarToUI getUIversion(){
       return new HangarToUI(this); 
    }
    
    private boolean spaceAvailable(){
        return shieldBoosters.size() + weapons.size() < maxElements;
    }
    
    public boolean addWeapon(Weapon w){
       if(spaceAvailable()){
           weapons.add(w);
           return true;
       } else {
           return false;
       }
    }
    
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            shieldBoosters.add(s);
            return true;
        } else {
            return false;
        }

    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;

    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;

    }
    
    public ShieldBooster removeShieldBooster(int s){
        if(s>=0 && s<shieldBoosters.size()){
            return shieldBoosters.remove(s); 
        } else {
            return null;
        }
    }
    
    public Weapon removeWeapon(int w){
        if(w>=0 && w<weapons.size()){
            return weapons.remove(w);
        } else {
            return null;
        }
    }
    
    public String toString(){
        return maxElements + " " + weapons + " " + shieldBoosters;
    }
}
