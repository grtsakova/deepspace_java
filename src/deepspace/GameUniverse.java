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
public class GameUniverse {
    
    static private int WIN = 10;
    
    private int currentStationIndex;
    private int turns;
    private Dice dice;
    private SpaceStation currentStation; 
    private ArrayList<SpaceStation> spaceStations;
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private boolean haveSpaceCity;
    
    public GameUniverse(){
        gameState = new GameStateController();
        turns = 0;
        dice = new Dice();
        haveSpaceCity = false;
    }
 /*
    public SpaceStation dameUnWeaponPrueba(){
        CardDealer dealer = CardDealer.getInstance();
        SuppliesPackage supplies = dealer.nextSuppliesPackage();
               SpaceStation station = new SpaceStation("a", supplies);
               spaceStations.add(station);
               int nh = dice.initWithNHangars();
               int nw = dice.initWithNWeapons();
               int ns = dice.initWithNShields();
               Loot lo = new Loot(0, nw, ns, nh, 0);
               station.setLoot(lo);
        return station;
    }
*/ 
    public SpaceFighter dameUnSpaceFighter(){
        CardDealer dealer = CardDealer.getInstance();
        if(dice.spaceFighter()){
            SuppliesPackage supplies = dealer.nextSuppliesPackage();
               SpaceStation station = new SpaceStation("a", supplies);
               spaceStations.add(station);
               int nh = dice.initWithNHangars();
               int nw = dice.initWithNWeapons();
               int ns = dice.initWithNShields();
               Loot lo = new Loot(0, nw, ns, nh, 0);
               station.setLoot(lo);
        return station;
        } else {
            return dealer.nextEnemy();
        }
    }
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch = dice.firstShot();
        boolean enemyWins;
        CombatResult combatResult;
        if (ch == GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);
            if (result == ShotResult.RESIST) {
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result == ShotResult.RESIST);
            } else {
                enemyWins = true;
            }
        } else {
            float fire = station.fire();
            ShotResult result = enemy.receiveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if (enemyWins){
           float s = station.getSpeed();
           boolean moves = dice.spaceStationMoves(s);
           if(!moves){
               Damage damage = enemy.getDamage();
               station.setPendingDamage(damage);
               combatResult = CombatResult.ENEMYWINS;
           } else {
               station.move();
               combatResult = CombatResult.STATIONESCAPES;
           }
        } else {
            Loot aLoot = enemy.getLoot();
            Transformation transformation = station.setLoot(aLoot);
            if(transformation==Transformation.GETEFFICIENT){
                makeStationEfficient();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            } else if (transformation==Transformation.SPACECITY){
                createSpaceCity();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            } else {
               combatResult = CombatResult.STATIONWINS; 
            }  
        }
        gameState.next(turns, spaceStations.size());
        return combatResult;
    }
    
    public CombatResult combat(){
       GameState state = gameState.getState();
       if ((state == GameState.BEFORECOMBAT) || (state == GameState.INIT)){
           return combat(currentStation, currentEnemy); 
       } else {
           return CombatResult.NOCOMBAT;
       }
    }
    
    public void discardHangar(){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }
    
    public void discardShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }
    
    public GameState getState(){
        return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(currentStation, currentEnemy);
    } 
    
    public boolean haveAWinner(){
        return currentStation.getNMedals() >= WIN;
    }
    
    public void init(ArrayList<String> names){
        GameState state = gameState.getState();    
        if (state == GameState.CANNOTPLAY){
           spaceStations = new ArrayList<SpaceStation>();
           CardDealer dealer = CardDealer.getInstance();
           for (int i = 0; i < names.size(); i++){
               SuppliesPackage supplies = dealer.nextSuppliesPackage();
               SpaceStation station = new SpaceStation(names.get(i), supplies);
               spaceStations.add(station);
               int nh = dice.initWithNHangars();
               int nw = dice.initWithNWeapons();
               int ns = dice.initWithNShields();
               Loot lo = new Loot(0, nw, ns, nh, 0);
               station.setLoot(lo);
           }
           currentStationIndex = dice.whoStarts(names.size());
           currentStation = spaceStations.get(currentStationIndex);
           currentEnemy = dealer.nextEnemy();
           gameState.next(turns, spaceStations.size());
        }       
    }
    
    public void mountShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountShieldBooster(i);
        }
    }
    
    public void mountWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountWeapon(i);
        }
    }
       
    public boolean nextTurn(){
        GameState state = gameState.getState();
        if (state == GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            if (stationState){
                currentStationIndex = (currentStationIndex + 1) % spaceStations.size();
                turns++;
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                gameState.next(turns, spaceStations.size());
                return true;
            }
            return false;
        } 
        return false;
    }
    
    private void makeStationEfficient(){
       if(dice.extraEfficiency()){
           currentStation = new BetaPowerEfficientSpaceStation(currentStation);
       } else {
          currentStation = new PowerEfficientSpaceStation(currentStation);
       }
    }
    
    private void createSpaceCity(){
       if(haveSpaceCity==false){
          ArrayList<SpaceStation> collaborators = new ArrayList<SpaceStation>();
          for(int i = 0; i < spaceStations.size(); i++){
              collaborators.add(spaceStations.get(i));
          }
          collaborators.remove(currentStation);
          currentStation = new SpaceCity(currentStation, collaborators);
          haveSpaceCity = true;
       } 
    }
    
    public String toString(){
        return WIN + " " + currentStationIndex + " " + turns + " " + dice +
                " " + currentStation + " " + spaceStations + " " + gameState +
                " " + currentEnemy;
    }
}
