/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.models;

/**
 * Class of the player model, stores infromation about the one player
 * @author Jedrzej Piaskowski
 * @version 3.0
 */
public class Player {
    /**
     * Players name in text
     */    
    private String name;
    /**
     * Players number of shots
     */  
    private int shots;
     /**
     * Players number of shots on target
     */  
    private int shotsOnTarget;

    /**
     * Player contructor with name, shots and shots on target default
     */
    public Player() {
        this.name = "Default";
        this.shots = 0;
        this.shotsOnTarget = 0;
    }

    
    /**
     * Player contructor with name, shots and shots on target to set
     * @param name name of the player to set
     * @param shots number of shots to set
     * @param shotsOnTarget number of shots on target to set
     */
    public Player(String name, int shots, int shotsOnTarget) {
        this.name = name;
        this.shots = shots;
        this.shotsOnTarget = shotsOnTarget;
    }
        
    /**
     * Player name getter
     * @return players name
     */
    public String getName() {
        return name;
    }

    /**
     * Player name setter
     * @param name players name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Player shots getter
     * @return number of player shots to get
     */
    public int getShots() {
        return shots;
    }

    /**
     * Player shots setter
     * @param shots number of player shots to sett
     */
    public void setShots(int shots) {
        this.shots = shots;
    }

    /**
     * Player shots on target getter
     * @return number of player shots on the target to get
     */
    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    /**
     * Player shots on target setter
     * @param shotsOnTarget number of player shots on the target to set
     */
    public void setShotsOnTarget(int shotsOnTarget) {
        this.shotsOnTarget = shotsOnTarget;
    }
    

    
}
