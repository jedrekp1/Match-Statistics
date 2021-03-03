/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.models;

import java.util.ArrayList;
import java.util.List;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import static pl.polsl.matchstatisticsweb.models.Result.*;

/**
 * Model class of the team
 * 
 * @author Jedrzej Piaskowski
 * @version 3.0
 */
public class Team {
    /** Team name */
    private String name = "default";
    /** Number of team goals */
    private int goals = 0;
    /** Team possession of the ball in minutes */
    private int possessionMinutes = 0;
    /** boolean saying if team won the match */
    private Result teamResult = DRAW;
     /** List of the players in team */
    List<Player> players;

    /**
     * Team constructor creating new list
     */
    public Team() {
        players = new ArrayList<Player>();
    }

    /**
     * Players list getter
     * @return list of the players to get 
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Team result getter
     * @return result of the team
     */
    public Result getTeamResult() {
        return teamResult;
    }

    /**
     * Team result setter
     * @param teamResult team result to set
     */
    public void setTeamResult(Result teamResult) {
        this.teamResult = teamResult;
    }

    /**
     * Add one player to the list
     * @param name players name
     * @param shots players number of shots
     * @param shotsOnTarget players number of shots on the target
     * @throws pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException 
     * thrown when number of shots is higher than number of shots on the target
     * or any number of shots is less than 0
     */
    public void addPlayer(String name, int shots, int shotsOnTarget) 
            throws ImpossibleSituationException
    {
        if (shots < 0)
            throw new ImpossibleSituationException("Number of shots must be"
                        + " >= 0.");    
        if (shotsOnTarget < 0 || shotsOnTarget > shots )
            throw new ImpossibleSituationException("Number of shots on target "
                    + "must be >= 0 and <= number of shots.");                      
        Player temp = new Player(name, shots, shotsOnTarget);
        players.add(temp);
    }
    
    /**
     *Removes all players from the list
     */
    public void clearPlayers() {
        players.clear();
    }
    
    /**
     * Winners component getter
     * @return value of winners parameter
     */


    /**
     * Summs up number of shots from all of the players with the for-each
     * 
     * @return number of shots of the team
     */
    public int calculateShots() {
        int sum = 0;
        for (Player player : players) {
            sum += player.getShots();
        }
        return sum;
    }

    /**
     * Summs up number of shots on the target from all of the players with the
     * stream
     * @return number of shots on the target
     */
    public int calculateShotsOnTarget() {     
        Integer sum = players.stream()
        .map(x -> x.getShotsOnTarget())
        .reduce(0, Integer::sum);
        return sum;
    }

    /**
     * Team name getter
     * @return name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Number of goals setter
     * @param goals number of goals to set
     * @throws ImpossibleSituationException thrown, when number of goals is less
     * than 0
     */
    public void setGoals(int goals) throws ImpossibleSituationException {
        if (goals < 0)
            throw new ImpossibleSituationException("Number of goals must be"
                        + " >= 0 ");        
        this.goals = goals;
    }

    /**
     * Possession minutes getter
     * @return time of the possesion in minutes (int)
     */
    public int getPossessionMinutes() {
        return possessionMinutes;
    }

    /**
     * Calculates percent possession of the ball in the game using minute
     * possession
     * @return calculated percent possession
     */
    public float calculatePossessionPercent() {
        return 10 * (float)possessionMinutes / 9;
    }
    
    /**
     * Calculates percent of shots on the target from all shots
     * @return percent of shots on the target
     */
    public float calculateShotsOnTargetPercent() {
        int shotsOnTarget = calculateShotsOnTarget();
        int shots = calculateShots();
        if (shotsOnTarget==0)
            return 0;
        return 100 * (float)shotsOnTarget / shots;
    }   
    
    /**
     * Calculates percent ratio of goals and shots
     * @return percent ratio of goals and all shots
     */
    public float calculateGoalsPerShotsPercent() {
        int shots = calculateShots();
        if (shots==0)
            return 0;
        return 100 * (float)goals / shots;
    }   
    
    /**
     * Possession minutes setter
     * @param possessionMinutes number of minutes in possession to set
     * @throws pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException
     * thrown when possession is less than 0 or more than 90
     */
    public void setPossessionMinutes(int possessionMinutes) 
            throws ImpossibleSituationException {
         if (possessionMinutes < 0 || possessionMinutes > 90 )
            throw new ImpossibleSituationException("Possession must be"
                        + " >= 0 ");
        this.possessionMinutes = possessionMinutes;
    }

    /**
     * Team name setter
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Goals number getter
     * @return number of goals of the team
     */
    public int getGoals() {
        return goals;
    }

}
