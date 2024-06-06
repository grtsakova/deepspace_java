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
class Loot {
    
    private int nSupplies, nWeapons, nShields, nHangars, nMedals; //декларираме променливите
    private boolean efficient, spaceCity;
    
        Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals){ //конструктор
            this.nSupplies = nSupplies;
            this.nWeapons = nWeapons;
            this.nShields = nShields;
            this.nHangars = nHangars;
            this.nMedals = nMedals;
            efficient = false;
            spaceCity = false;
        }
        
        Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals, boolean efficient, boolean spaceCity ){ 
            this.nSupplies = nSupplies;
            this.nWeapons = nWeapons;
            this.nShields = nShields;
            this.nHangars = nHangars;
            this.nMedals = nMedals;
            this.efficient = efficient;
            this.spaceCity = spaceCity;
            if (efficient && spaceCity){
                this.efficient = false;
            }
        }
        
        public int getNSupplies(){ //консултор
            return nSupplies;
        }
        
        public int getNWeapons(){
            return nWeapons;
        }
        
        public int getNShields(){
            return nShields;
        }
        
        public int getNHangars(){
            return nHangars;
        }
        
        public int getNMedals(){
            return nMedals;
        }
        
        public boolean getEfficient(){
            return efficient;
        }
        
        public boolean spaceCity(){
            return spaceCity;
        }
        
        public String toString(){
            return nSupplies + " " + nWeapons + " " + nShields + " " + nHangars + " " + nMedals + " " + efficient + " " + spaceCity; 
        }
        
        LootToUI getUIversion(){
            return new LootToUI(this);
        }
    }

