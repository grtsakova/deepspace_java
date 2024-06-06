/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 *
 * @author gerga
 */
class Dice {
    private final float NHANGARSPROB, NSHIELDSPROB, NWEAPONSPROB,FIRSTSHOTPROB,EXTRAEFFICIENCYPROB, SPACEFIGHTERPROB;
    private Random generator;
    
    Dice(){
        generator = new Random();
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
        EXTRAEFFICIENCYPROB=0.8f;
        SPACEFIGHTERPROB=0.5f;
    }
    
    public boolean spaceFighter(){
        float number = generator.nextFloat();
        return (number <= SPACEFIGHTERPROB);
    }
    
    public int initWithNHangars(){
        float number = generator.nextFloat();
        if(number <= NHANGARSPROB){
            return 0;
        } else {
            return 1;   
        }
    }
    
    public int initWithNWeapons(){
        float number = generator.nextFloat();
        if(number <= NWEAPONSPROB){
            return 1;
        } else if (number > 2*NWEAPONSPROB){
            return 3;
        } else {
            return 2;
        }
    }
    
    public int initWithNShields(){
        float number = generator.nextFloat();
        if(number <= NSHIELDSPROB){
            return 0;
        } else {
            return 1;
        }
    }
    
    public int whoStarts(int nPlayers){
        int number = generator.nextInt(nPlayers);
        return number;
    }
    
    public GameCharacter firstShot() {
        float number = generator.nextFloat();
        if(number <= FIRSTSHOTPROB){
            return GameCharacter.SPACESTATION;
        } else {
            return GameCharacter.ENEMYSTARSHIP;
        }
    }
    
    public boolean spaceStationMoves(float speed){
        float number = generator.nextFloat();
//        if(number<=speed){
//            return true;
//        } else {
//            return false;
//        }
          return number<=speed;
    }
    
    public boolean extraEfficiency(){
        float number = generator.nextFloat();
        return number <= EXTRAEFFICIENCYPROB;
    }
    
  
    public String toString(){
        return NHANGARSPROB + " " + NSHIELDSPROB + " " + NWEAPONSPROB + " " + FIRSTSHOTPROB; 
    }
    
    
}
