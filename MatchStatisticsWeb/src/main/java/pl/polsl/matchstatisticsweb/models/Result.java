/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.models;

/**
 * Result of the match enumerator used for the result of the teams
 * @author Jedrzej Piaskowski
 * @version 2.0
 */
public enum Result{

    /**
     * Means that team lost
     */
    LOSER,

    /**
     * Means that team won
     */
    WINNER,

    /**
     * Means that there was a draw
     */
    DRAW
}
