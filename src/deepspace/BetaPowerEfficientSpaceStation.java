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
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation {
    
    private static final float EXTRAEFFICIENCY = 1.2f;
    
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    @Override
    public BetaPowerEfficientSpaceStationToUI getUIversion(){
        return new BetaPowerEfficientSpaceStationToUI(this);
    }
    
    @Override
    public float fire(){
        return super.fire() * EXTRAEFFICIENCY;
    }
}
