/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.servlets;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class thats made once at the start of an aplication. After that it's injected
 * to other classes which use Entity manager
 *
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
@Named
@ApplicationScoped
public class EMInitializer {

    /**
     * Entity manager factory object
     *
     */
    private EntityManagerFactory emf;
    /**
     * Entity manager object
     *
     */
    private EntityManager em;

    /**
     * Constructor that makes entity manager at the start of an application.
     *
     */
    EMInitializer() {
        try {
            emf = Persistence.createEntityManagerFactory("pl.polsl_MatchStatist"
                    + "icsWeb_war_4.0-SNAPSHOTPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            em = null;
            emf = null;
        }

    }

    /**
     * Entity manager getter
     * @return entity manager
     */
    public EntityManager getEntityManager() {
        return em;
    }
}
