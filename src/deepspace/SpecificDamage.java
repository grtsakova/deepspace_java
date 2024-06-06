/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gerga
 */
public class SpecificDamage extends Damage {
    
    private ArrayList<WeaponType> weapons;
    
    SpecificDamage(ArrayList<WeaponType> w, int s){
        super(s);
        weapons = w;
    }
    
    public SpecificDamage copy(){
       return new SpecificDamage(weapons,nShields);
    }
    
    SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
       if(w.contains(t)){
            return w.indexOf(t);
       } else {
           return -1;
       }
    }
    
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        
        ArrayList<WeaponType> adjustado = new ArrayList<WeaponType>();
        ArrayList<WeaponType> tiposDeW = new ArrayList<WeaponType>();
            
        for(int i=0; i < w.size(); i++){
            tiposDeW.add(w.get(i).getType());
        }

        WeaponType[] tipos = WeaponType.values();

        for(int i = 0; i < tipos.length; i++) {
            int veces= Math.min(Collections.frequency(weapons, tipos[i]), Collections.frequency(tiposDeW, tipos[i]));
            if (veces > 0){
                for(int j = 0; j < veces; j++){
                    adjustado.add(tipos[i]);
                }
            }
        }
        return new SpecificDamage(adjustado, Math.min(nShields, s.size()));
}
    @Override
    public void discardWeapon(Weapon w){
      int i = weapons.indexOf(w.getType());
      if (i != -1){
          weapons.remove(i);
        } 
    }
    
    @Override
    public boolean hasNoEffect(){
        return super.hasNoEffect() && weapons.isEmpty();
    }
    
    @Override
    public String toString(){
        return super.toString() + "Weapons: " + weapons;
    }
    
}
