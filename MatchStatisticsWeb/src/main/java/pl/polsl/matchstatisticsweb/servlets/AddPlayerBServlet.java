/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import pl.polsl.matchstatisticsweb.models.Match;

/**
 * Servlet used to add new players to team B
 * @author Jedrzej Piaskowski
 * @version 4.0
 */
public class AddPlayerBServlet extends HttpServlet {

    /**
     * method that adds player to team B
     * 
     * @param request Request object
     * @param response Response object
     * @throws IOException Thrown when there are problems with output writer
     * @throws ServletException Normal servlet load exception
     */
    public void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        HttpSession currentSession = 
                SessionCreator.sessionCheckAndCreate(request);

        
        Match match = (Match) currentSession.getAttribute("match");
        try {
            match.getTeamB().addPlayer(request.getParameter("playerBName"),
                    parseInt(request.getParameter("shotsB")),
                    parseInt(request.getParameter("shotsOnTargetB")));
        } catch (NumberFormatException | ImpossibleSituationException ex) {
            out.println("<html>\n<body>\n<h1> Error: " + ex.getMessage() + 
                    "</h1>\n");
        out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                + "Go Back</button><script>function goBack() "
                + "{window.history.back();}</script>");               
            return;
        }
        
        
        out.println("<html>\n<body>\n<h1> Player has been added!</h1>\n");     
        out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                + "Go Back</button><script>function goBack() "
                + "{window.history.back();}</script>");        
        currentSession.setAttribute("match", match);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}