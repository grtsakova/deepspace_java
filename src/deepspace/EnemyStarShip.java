/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author gerga
 */
public class EnemyStarShip implements SpaceFighter {
    public float ammoPower;
    public String name;
    public float shieldPower;
    private Loot loot;
    private Damage damage;
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    
    EnemyStarShip(EnemyStarShip e){
        name = e.name;
        ammoPower = e.ammoPower;
        shieldPower = e.shieldPower;
        loot = e.loot;
        damage = e.damage;
    }
    
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);
    }
    
    @Override
    public float fire(){
       return ammoPower;
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public Damage getDamage(){
        return damage;
    }
    
    public Loot getLoot(){
        return loot;
    }
    
    public String getName(){
        return name;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    @Override
    public float protection(){
        return shieldPower;
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        if(shieldPower <= shot){
            return ShotResult.DONOTRESIST;
        } else {
            return ShotResult.RESIST;
        }
    }
    
    public String toString(){
        return name + " " + ammoPower + " " + shieldPower + " " + loot + " " + damage;
    }

    
    
}
