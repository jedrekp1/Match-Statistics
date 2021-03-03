/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.matchstatisticsweb.entities.MatchData;
import pl.polsl.matchstatisticsweb.entities.TeamData;

/**
 * Servlet used to display all database records
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
public class WatchDataServlet extends HttpServlet {

    /**
     *
     * Entity manager injected, created in EMInitializer
     *
     */
    @Inject
    private EMInitializer em;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        try {
            EntityManager entityManager = em.getEntityManager();
            Query q = entityManager.createQuery("SELECT a FROM TeamData a");
            List<TeamData> listTeams = q.getResultList();
            Query q1 = entityManager.createQuery("SELECT m FROM MatchData m");
            List<MatchData> listMatches = q1.getResultList();
            out.println("<html>\n<body>\n<br></h1>Matches:<br><br>");
            listMatches.forEach(o -> {
                out.println("<html>\n<body>\n Match score:" + o.getTeamAgoals()
                        + ":" + o.getTeamBgoals() + "\n");
                out.println("<html>\n<body>\n Teams:" + o.getTeama().getName()
                        + "(" + o.getTeamApossession() + "% possession) and "
                        + o.getTeamb().getName() + "(" + o.getTeamBpossession()
                        + "% possession). Refree: " + o.getRefree()
                        + "</h1><br>");
            });
            out.println("<html>\n<body>\n<br>Teams:</h1><br><br>");
            listTeams.forEach(o -> {
                out.println("<html>\n<body>\n" + o.getName() + "</h1><br>");
            });
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
        } catch (NullPointerException e) {
            out.println("<html>\n<body>\n<h1> Database may be"
                    + " turned off!</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
        } catch (Exception e) {
            out.println("<html>\n<body>\n<h1> Error: " + e.getMessage()
                    + "</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
        }
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
