/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pl.polsl.matchstatisticsweb.models.Match;
import pl.polsl.matchstatisticsweb.models.Team;

/**
 * Class of the static method for checking and creating seassions
 * @author Jedrzej Piaskowski
 * @version 4.0
 */
public class SessionCreator {
    
    /**
     * Static method for checking and creating seassions
     * @param request Request object in chosen servlet
     * @return HttpSession that was created or checked.
     */
    public static HttpSession sessionCheckAndCreate(HttpServletRequest request) 
    {
        HttpSession sessionTemp = request.getSession(false);
        
        if (sessionTemp == null) {
            
            sessionTemp = request.getSession(true);
            Team teamATemp = new Team();
            Team teamBTemp = new Team();
            sessionTemp.setAttribute("match", new Match(teamATemp, teamBTemp));
        }
        return sessionTemp;
    } 
    
}
