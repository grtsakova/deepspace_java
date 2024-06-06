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
public class SpaceStation implements SpaceFighter {
    
   static private int MAXFUEL = 100;
   static private float SHIELDLOSSPERUNITSHOT = 0.1f;
   
   private float ammoPower;
   private float fuelUnits;
   private String name;
   private int nMedals;
   private float shieldPower;
   private Hangar hangar;
   private ArrayList<ShieldBooster> shieldBoosters;
   private ArrayList<Weapon> weapons;
   private Damage pendingDamage;
   
   
   SpaceStation(String n, SuppliesPackage supplies){
       name = n;
       ammoPower = supplies.getAmmoPower();
       fuelUnits = supplies.getFuelUnits();
       shieldPower = supplies.getShieldPower();
       shieldBoosters = new ArrayList<ShieldBooster>();
       weapons = new ArrayList<Weapon>();
       nMedals = 0;
       pendingDamage = null;
//       hangar = null;
   }
   
   SpaceStation(SpaceStation station){
       name = station.name;
       ammoPower = station.ammoPower;
       fuelUnits = station.fuelUnits;
       shieldPower = station.shieldPower;
       shieldBoosters = station.shieldBoosters;
       weapons = station.weapons;
       nMedals = station.nMedals;
       pendingDamage = station.pendingDamage;
       hangar = station.hangar;
   }
   
   
    private void assignFuelValue(float f){
       if(f > MAXFUEL){
           fuelUnits = MAXFUEL;
       }
   }
   
   private void cleanPendingDamage(){
       if(pendingDamage.hasNoEffect()){
           pendingDamage = null;
       }
   }
   
   public void cleanUpMountedItems(){
       for(int i=weapons.size()-1; i >= 0 ; i--){
           if(weapons.get(i).getUses() == 0){
               weapons.remove(i);
           }
       }
       
       for(int i=shieldBoosters.size()-1; i >= 0 ; i--){
           if(shieldBoosters.get(i).getUses() == 0){
               shieldBoosters.remove(i);
           }
       }
   }
   
   public void discardHangar(){
       hangar = null;
   }
   
   public void discardShieldBooster(int i){
       int size = shieldBoosters.size();
       if (i >= 0 && i < size){
           ShieldBooster s = shieldBoosters.remove(i);
           if (pendingDamage != null){
               pendingDamage.discardShieldBooster();
               cleanPendingDamage();
           }
       }
   }
   
   public void discardShieldBoosterInHangar(int i){
       if(hangar != null){
           hangar.removeShieldBooster(i);
       }
   }
   
   public void discardWeapon(int i){
       int size = weapons.size();
       if (i >= 0 && i < size){
           Weapon w = weapons.remove(i);
           if (pendingDamage != null){
               pendingDamage.discardWeapon(w);
               cleanPendingDamage();
           }
       }
   }
   
   public void discardWeaponInHangar(int i){
       if(hangar != null){
           hangar.removeWeapon(i);
       }
   }
   
   @Override
   public float fire(){
       int size = weapons.size();
       float factor = 1.0f;
       for(int i=0; i < size; i++){
           Weapon w = weapons.get(i);
           factor *= w.useIt();
       }
       return ammoPower*factor;
   }
   
   public float getAmmoPower(){
       return ammoPower;
   }
   
   public float getFuelUnits(){
       return fuelUnits;
   }
   
   public Hangar getHangar(){
       return hangar;
   }
   
   public String getName(){
       return name;
   }
   
   public int getNMedals(){
       return nMedals;
   }
   
   public Damage getPendingDamage(){
       return pendingDamage;
   }
   
   public ArrayList<ShieldBooster> getShieldBoosters(){
       return shieldBoosters;
   }
   
   public float getShieldPower(){
       return shieldPower;
   }
   
   public SpaceStationToUI getUIversion(){
       return new SpaceStationToUI(this);
   }
   
   public ArrayList<Weapon> getWeapons(){
       return weapons;
   }
   
   public void mountShieldBooster(int i){
       if(hangar != null){
           ShieldBooster escudo = hangar.removeShieldBooster(i);
           if(escudo != null){
               shieldBoosters.add(escudo);
           }
       }
   }
   
   public void mountWeapon(int i){
       if(hangar != null){
           Weapon arma = hangar.removeWeapon(i);
           if(arma!=null){
               weapons.add(arma);
           }
       }
   }
   
   public float getSpeed(){
       return fuelUnits/MAXFUEL; 
   }
   
   public void move(){
       fuelUnits -= fuelUnits * getSpeed();
       if(fuelUnits < 0){
           fuelUnits = 0;
       }
   }
   
   @Override
   public float protection(){
       int size = shieldBoosters.size();
       float factor = 1.0f;
       for (int i=0; i < size; i++){
           ShieldBooster s = shieldBoosters.get(i);
           factor *= s.useIt();
       }
       return shieldPower*factor;
   }
   
   public void receiveHangar(Hangar h){
       if(hangar == null){
           hangar = h;
       }
   }
   
   public boolean receiveShieldBooster(ShieldBooster s){
       if(hangar != null){
           return hangar.addShieldBooster(s);
       } else {
           return false;
       }
   }
   
   @Override
   public ShotResult receiveShot(float shot){
       float myProrection = protection();
       if(myProrection >= shot){
           shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
           shieldPower = Math.max(0.0f,shieldPower);
           return ShotResult.RESIST;
       } else {
           shieldPower = 0.0f;
           return ShotResult.DONOTRESIST;
       }
   }
   
   public void receiveSupplies(SuppliesPackage s){
       ammoPower += s.getAmmoPower();
       shieldPower += s.getShieldPower();
       fuelUnits += s.getFuelUnits();
       assignFuelValue(fuelUnits);
   }
   
   public boolean receiveWeapon(Weapon w){
       if (hangar != null){
           return hangar.addWeapon(w);
       } else {
           return false;
       }
   }
   
   public Transformation setLoot(Loot loot){
       CardDealer dealer = CardDealer.getInstance();
       int h = loot.getNHangars();
       if (h > 0) {
           Hangar hangar = dealer.nextHangar();
           receiveHangar(hangar);
       }
       
       int elements = loot.getNSupplies();
       
       for (int i = 0; i < elements; i++){
           SuppliesPackage sup = dealer.nextSuppliesPackage();
           receiveSupplies(sup);
       }
       
       elements = loot.getNWeapons();
       
       for (int i = 0; i < elements; i++){
           Weapon weap = dealer.nextWeapon();
           receiveWeapon(weap);
       }
       
       elements = loot.getNShields();
       
       for (int i = 0; i < elements; i++){
           ShieldBooster sh = dealer.nextShieldBooster();
           receiveShieldBooster(sh);
       }
       
       int medals = loot.getNMedals();
       nMedals += medals;
       
       if (loot.getEfficient()){
           return Transformation.GETEFFICIENT;
       } else if (loot.spaceCity()){
           return Transformation.SPACECITY;
       } else {
           return Transformation.NOTRANSFORM;
       } 
   }
   
   public void setPendingDamage(Damage d){
      pendingDamage = d.adjust(weapons, shieldBoosters);
      cleanPendingDamage();
   }
   
   public boolean validState(){
      return (pendingDamage == null || pendingDamage.hasNoEffect());
   }
   
   public String toString(){
       return MAXFUEL + " " + SHIELDLOSSPERUNITSHOT + " " + ammoPower + " " +
            fuelUnits + " " + name + " " + nMedals + " " + shieldPower + " " +
               hangar + " " + shieldBoosters + " " + weapons + " " + pendingDamage;
   }
   
}
