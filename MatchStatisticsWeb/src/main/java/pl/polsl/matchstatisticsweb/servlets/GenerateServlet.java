/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.matchstatisticsweb.entities.MatchData;
import pl.polsl.matchstatisticsweb.entities.TeamData;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import pl.polsl.matchstatisticsweb.models.Match;

/**
 * Servlet class that generates the raport and adds new records to database
 *
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
public class GenerateServlet extends HttpServlet {

    /**
     * Entity manager injected, created in EMInitializer
     *
     */
    @Inject
    private EMInitializer em;

    /**
     * Method that generates the match raport and use cookies to show the result
     * of last raport made
     *
     * @param request request object
     * @param response response object
     * @throws IOException IOException if an I/O error occurs
     * @throws ServletException Servlet load exception
     */
    public void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();

        HttpSession currentSession
                = SessionCreator.sessionCheckAndCreate(request);

        Match match = (Match) currentSession.getAttribute("match");
        // Get parameter values - firstName i lastName
        match.getTeamA().setName(request.getParameter("teamAName"));
        match.getTeamB().setName(request.getParameter("teamBName"));
        match.setRefree(request.getParameter("refreeName"));
        try {
            match.setGoalsAndResult(parseInt(request.getParameter("goalsA")),
                    parseInt(request.getParameter("goalsB")));
            match.setPossessions(parseInt(request.getParameter("possessionA")),
                    parseInt(request.getParameter("possessionB")));
        } catch (NumberFormatException | ImpossibleSituationException ex) {
            out.println("<html>\n<body>\n<h1> Error: " + ex.getMessage()
                    + "</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
            return;
        }
        String result;
        if (null == match.getTeamA().getTeamResult()) {
            result = "tied";
        } else {
            switch (match.getTeamA().getTeamResult()) {
                case DRAW:
                    result = "tied";
                    break;
                case WINNER:
                    result = "won over";
                    break;
                default:
                    result = "lost with";
                    break;
            }
        }

        if (request.getCookies() != null) {
            Cookie cookieTab[] = request.getCookies();
            for (Cookie ck : cookieTab) {
                if (ck.getName().equals("lastResult")) {
                    out.println("<html>\n<body>" + ck.getValue()
                            + "\n");

                }
            }
        }
        Cookie cookieTemp = new Cookie("lastResult", "Last time team "
                + match.getTeamA().getName() + " " + result + " team "
                + match.getTeamB().getName());
        response.addCookie(cookieTemp);

        out.println("<html>\n<body>\n<h1>Team " + match.getTeamA().getName()
                + " " + result + " team " + match.getTeamB().getName()
                + "</h1>\n");
        out.println("<html>\n<body>\n<h1>" + match.getTeamA().getGoals()
                + " : " + match.getTeamB().getGoals() + "</h1>\n");
        out.println("<html>\n<body>\n<h1>Refree of the match was: "
                + match.getRefree() + "</h1>\n");
        out.println("<html>\n<body>\n<h1>First team ball possession: "
                + match.getTeamA().calculatePossessionPercent() + "%.</h1>\n");
        out.println("<html>\n<body>\n<h1>Second team ball possession: "
                + match.getTeamB().calculatePossessionPercent() + "%.</h1>\n");
        out.println("<html>\n<body>\n<h1>First team shots: "
                + match.getTeamA().calculateShots() + " and "
                + match.getTeamA().calculateShotsOnTarget() + " on target ("
                + match.getTeamA().calculateShotsOnTargetPercent()
                + "%).</h1>\n");
        out.println("<html>\n<body>\n<h1>Second team shots: "
                + match.getTeamB().calculateShots() + " and "
                + match.getTeamB().calculateShotsOnTarget() + " on target ("
                + match.getTeamB().calculateShotsOnTargetPercent()
                + "%).</h1>\n");
        out.println("<html>\n<body>\n<h1>Number of team A players added: "
                + match.getTeamA().getPlayers().size() + ".</h1>\n");
        out.println("<html>\n<body>\n<h1>Number of team B players added: "
                + match.getTeamB().getPlayers().size() + ".</h1>\n");
        out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                + "Go Back</button><script>function goBack() "
                + "{window.history.back();}</script>");

        //TODO to another method:
        currentSession.setAttribute("match", match);

//        EntityManagerFactory emf
//                = Persistence.createEntityManagerFactory(
//                        "pl.polsl_MatchStatisticsWeb_war_4.0-SNAPSHOTPU");
//        EntityManager em = emf.createEntityManager();
        EntityManager entityManager = em.getEntityManager();
        try {
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
            //TODO to another method:
            TeamData teamDataA = new TeamData();
            teamDataA.setName(match.getTeamA().getName());
            TeamData teamDataB = new TeamData();
            teamDataB.setName(match.getTeamB().getName());
            MatchData matchData = new MatchData();
            matchData.setTeamApossession(match.getTeamA()
                    .calculatePossessionPercent());
            matchData.setTeamBpossession(match.getTeamB()
                    .calculatePossessionPercent());
            matchData.setTeamAgoals(match.getTeamA().getGoals());
            matchData.setTeamBgoals(match.getTeamB().getGoals());
            matchData.setRefree(match.getRefree());
            matchData.setTeama(teamDataA);
            matchData.setTeamb(teamDataB);
            Set<MatchData> matches = new HashSet<MatchData>();
            matches.add(matchData);
            entityManager.getTransaction().begin();
            entityManager.persist(teamDataA);
            entityManager.persist(teamDataB);
            entityManager.persist(matchData);
            entityManager.getTransaction().commit();
        } catch (NullPointerException e) {
            out.println("<html>\n<body>\n<h1> Database may be"
                    + " turned off!</h1>\n");
            out.println("<html>\n<body>\n<h1><button onclick=\"goBack()\">"
                    + "Go Back</button><script>function goBack() "
                    + "{window.history.back();}</script>");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            out.println("<html>\n<body>\n<h1> Error: " + e.getMessage()
                    + "</h1>\n");
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
