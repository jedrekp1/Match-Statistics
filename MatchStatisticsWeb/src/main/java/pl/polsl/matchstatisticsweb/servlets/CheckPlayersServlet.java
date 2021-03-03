/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import pl.polsl.matchstatisticsweb.models.Match;

/**
 * Servlet that shows all players added to both teams
 * @author Jedrzej Piaskowski
 * @version 4.0
 */
public class CheckPlayersServlet extends HttpServlet {

    /**
     * Processes requests for both GET and POST
     * methods. Shows all added players in this session
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws
     * pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException, ImpossibleSituationException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession currentSession = 
                SessionCreator.sessionCheckAndCreate(request);
        Match match = (Match) currentSession.getAttribute("match");

        out.println("<html>\n<body>\n<h1>Team A:</h1>\n");
        match.getTeamA().getPlayers().forEach(player -> {
            out.println("<html>\n<body>\n<h1>Name: " + player.getName()
                    + ". Shots: " + player.getShots() + ". Shots on target: "
                    + player.getShotsOnTarget() + ".</h1>\n");
        });
        out.println("<html>\n<body>\n<h1>Team B:</h1>\n");
        match.getTeamB().getPlayers().forEach(player -> {
            out.println("<html>\n<body>\n<h1>Name: " + player.getName()
                    + ". Shots: " + player.getShots() + ". Shots on target: "
                    + player.getShotsOnTarget() + ".</h1>\n");
        });
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
        try {
            processRequest(request, response);
        } catch (ImpossibleSituationException ex) {
            PrintWriter out = response.getWriter();
            out.println("<html>\n<body>\n<h1> Error: " + ex.getMessage()
                    + "</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
            return;
        }
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
        try {
            processRequest(request, response);
        } catch (ImpossibleSituationException ex) {
            PrintWriter out = response.getWriter();
            out.println("<html>\n<body>\n<h1> Error: " + ex.getMessage()
                    + "</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
            return;
        }
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
