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
class ShieldBooster implements CombatElement {
    private String name;
    private float boost;
    private int uses;
            
    ShieldBooster(String name, float boost, int uses){
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    ShieldBooster(ShieldBooster s){
        name = s.name;
        boost = s.boost;
        uses = s.uses;
    }
    
    public float getBoost(){
        return boost;
    }
    
    @Override
    public int getUses(){
        return uses;
    }
    
    @Override
    public float useIt(){
        if (uses>0){
            uses--;
            return boost;
        } else {
                return 1.0f;
        }
    }
    
    public String toString(){
        return name + " " + boost + " " + uses;
    }
    
    ShieldToUI getUIversion(){
        return new ShieldToUI(this);
    }
}

