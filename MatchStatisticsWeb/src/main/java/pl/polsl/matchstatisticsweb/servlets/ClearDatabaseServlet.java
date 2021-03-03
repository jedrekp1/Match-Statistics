/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet which clears all database records
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
public class ClearDatabaseServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            EntityManagerFactory emf
//                    = Persistence.createEntityManagerFactory(
//                            "pl.polsl_MatchStatisticsWeb_war_4.0-SNAPSHOTPU");
            EntityManager entityManager = em.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.createQuery("DELETE FROM MatchData")
                        .executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.getTransaction().begin();
                entityManager.createQuery("DELETE FROM TeamData")
                        .executeUpdate();
                entityManager.getTransaction().commit();
            } catch (NullPointerException e) {
                out.println("<html>\n<body>\n<h1> Database may be"
                        + " turned off!</h1>\n");
                out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                        + "Go Back</button><script>function goBack() "
                        + "{window.history.back();}</script>");
                return;
            } catch (Exception e) {
                out.println("<html>\n<body>\n<h1> Error: " + e.getMessage()
                        + "</h1>\n");
                out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                        + "Go Back</button><script>function goBack() "
                        + "{window.history.back();}</script>");
                return;
            }
            out.println("<html>\n<body>\n<h1>Database cleared!</h1>\n");
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
