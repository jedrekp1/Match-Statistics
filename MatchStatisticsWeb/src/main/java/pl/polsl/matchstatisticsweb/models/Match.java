/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.models;

import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;

/**
 * Model class of the match 
 * @author Jedrzej Piaskowski
 * @version 3.0
 */
public class Match { 
    /** Team A object */
    final private Team teamA;
     /** Team B object */
    final private Team teamB;
     /** Refrees name */
    private String refree; 

    /**
     * Constructor with teams to set
     * @param teamA team A object
     * @param teamB team B object
     */
    public Match(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }
    
    /**
     * Team A object getter
     * @return team A object
     */
    public Team getTeamA() {
        return teamA;
    }

    /**
     * Team B object getter
     * @return team B object
     */
    public Team getTeamB() {
        return teamB;
    }   

    /**
     * Refrees name getter
     * @return refree name
     */
    public String getRefree() {
        return refree;
    }

    /**
     * Sets refrees name
     * @param refree refrees name to set
     */
    public void setRefree(String refree) {
        this.refree = refree;
    }
    
    /**
     * Method setting the possession of the ball of both Team objects.
     * @param possessionA possession of the ball in minutes of team A
     * @param possessionB possession of the ball in minutes of team B
     * @throws ImpossibleSituationException exception thrown when sum of
     * possessions is higher than possible 90 min.
     */
    public void setPossessions(int possessionA, int possessionB)
            throws ImpossibleSituationException {
        if (possessionA + possessionB > 90 || possessionA + possessionB < 0)
            throw new ImpossibleSituationException("Sum of the possessions can "
                    + "not be higher than 90");
        teamA.setPossessionMinutes(possessionA);
        teamB.setPossessionMinutes(possessionB); 
    }
    
    /**
     * Method setting the number of goals for Team objects and WINNER boolean
     * @param goalsTeamA number of goals of team A
     * @param goalsTeamB number of goals of team B
     * @throws ImpossibleSituationException thrown, when number of goals of any
     * team is less than 0
     */
    public void setGoalsAndResult(int goalsTeamA, int goalsTeamB) 
            throws ImpossibleSituationException {
        if (goalsTeamA < 0 || goalsTeamB < 0)
                throw new ImpossibleSituationException("Number of goals must be"
                        + " >= 0.");
        teamA.setGoals(goalsTeamA);
        teamB.setGoals(goalsTeamB);
        if (goalsTeamA > goalsTeamB) {
            teamA.setTeamResult(Result.WINNER);           
            teamB.setTeamResult(Result.LOSER);
        } else if (goalsTeamA < goalsTeamB) {
            teamA.setTeamResult(Result.LOSER);           
            teamB.setTeamResult(Result.WINNER);
        } else {
            teamA.setTeamResult(Result.DRAW);           
            teamB.setTeamResult(Result.DRAW);          
        }
            
            
        
    }
          

}