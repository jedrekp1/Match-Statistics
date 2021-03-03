/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.exceptions;

/**
 * Exception class for objects thrown when wrong arguments given
 * 
 * @author Jedrzej Piaskowski 
 * @version 2.0
 */
public class ImpossibleSituationException extends Exception {

    /**
     * Non-parameter constructor
     */
    public ImpossibleSituationException() {
    }

    /**
     * Exception thrown, when the situation happens which wouldn't be possible
     * physically.
     * @param message thrown message
     */
    public ImpossibleSituationException(String message) {
        super(message);
    }    
}
