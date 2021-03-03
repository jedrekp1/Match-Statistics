/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Match database entity class
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
@Entity
@Table(name = "MATCHES")
@NamedQueries({
    @NamedQuery(name = "MatchData.findAll", 
            query = "SELECT m FROM MatchData m"),
    @NamedQuery(name = "MatchData.findById", 
            query = "SELECT m FROM MatchData m WHERE m.id = :id"),
    @NamedQuery(name = "MatchData.findByTeamagoals", query = "SELECT m FROM "
            + "MatchData m WHERE m.teamagoals = :teamagoals"),
    @NamedQuery(name = "MatchData.findByTeambgoals", query = "SELECT m FROM "
            + "MatchData m WHERE m.teambgoals = :teambgoals")})
public class MatchData implements Serializable {
    /**
     * serial version UID (generated with entity)
     */
    private static final long serialVersionUID = 1L;
        /**
     * ID
     */
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
        /**
     * Refree name
     */
    @Size(max = 50)
    @Column(name = "REFREE")
    private String refree;
        /**
     * Team A goals number
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEAMAGOALS")
    private int teamagoals;
        /**
     * Team B goals number
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEAMBGOALS")
    private int teambgoals;
        /**
     *Team A possession percent
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEAMAPOSSESSION")
    private float teamapossession;
        /**
     *Team B possession percent
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEAMBPOSSESSION")
    private float teambpossession;
        /**
     *Team A Data entity object
     */
    @JoinColumn(name = "TEAMB", referencedColumnName = "ID")
    @ManyToOne(cascade=CascadeType.REMOVE)
    private TeamData teamb;
        /**
     * Team B Data entity object
     */
    @JoinColumn(name = "TEAMA", referencedColumnName = "ID")
    @ManyToOne(cascade=CascadeType.REMOVE)
    private TeamData teama;

    /**
     * Constructor
     */
    public MatchData() {
    }

    /**
     * Constructor with ID to set
     * @param id id to set
     */
    public MatchData(Integer id) {
        this.id = id;
    }

    /**
     * Constructor with all parameters to set
     * @param id id to set
     * @param refree refree name to set
     * @param teamagoals number of goals of team A to set
     * @param teambgoals number of goals of team B to set
     * @param teamapossession possession percent of team A to set
     * @param teambpossession possession percent of team B to set
     */
    public MatchData(Integer id, String refree, int teamagoals, int teambgoals, 
            int teamapossession, int teambpossession) {
        this.id = id;
        this.refree = refree;
        this.teamagoals = teamagoals;
        this.teambgoals = teambgoals;
        this.teamapossession = teamapossession;
        this.teambpossession = teambpossession;
    }

    /**
     * ID getter
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * ID setter
     * @param id id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Refree name getter
     * @return refree name
     */
    public String getRefree() {
        return refree;
    }

    /**
     * Refree name setter
     * @param refree refree name to set
     */
    public void setRefree(String refree) {
        this.refree = refree;
    }

    /**
     * team A possession getter
     * @return possession of team A
     */
    public float getTeamApossession() {
        return teamapossession;
    }

    /**
     * team A possession setter
     * @param teamapossession possession to set
     */
    public void setTeamApossession(float teamapossession) {
        this.teamapossession = teamapossession;
    }

    /**
     * team B possession getter
     * @return team B possession
     */
    public float getTeamBpossession() {
        return teambpossession;
    }

    /**
     * team B possession setter
     * @param teambpossession possession to set
     */
    public void setTeamBpossession(float teambpossession) {
        this.teambpossession = teambpossession;
    }

    /**
     * Team A goals getter
     * @return team A goals
     */
    public int getTeamAgoals() {
        return teamagoals;
    }

    /**
     * team A goals setter
     * @param teamagoals goals of team A to set
     */
    public void setTeamAgoals(int teamagoals) {
        this.teamagoals = teamagoals;
    }

    /**
     * Team B goals getter
     * @return team B goals
     */
    public int getTeamBgoals() {
        return teambgoals;
    }

    /**
     * team B goals setter
     * @param teambgoals goals of team B to set
     */
    public void setTeamBgoals(int teambgoals) {
        this.teambgoals = teambgoals;
    }

    /**
     * team B data object getter
     * @return team B data object
     */
    public TeamData getTeamb() {
        return teamb;
    }

    /**
     * team B data object setter
     * @param teamb team B object to set
     */
    public void setTeamb(TeamData teamb) {
        this.teamb = teamb;
    }

    /**
     * team A Data object getter
     * @return team A data object
     */
    public TeamData getTeama() {
        return teama;
    }

    /**
     * team A data object setter
     * @param teama team a data object
     */
    public void setTeama(TeamData teama) {
        this.teama = teama;
    }

        /**
     * generates hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

        /**
     * check if objects are equal
     * @param object object to compare
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MatchData)) {
            return false;
        }
        MatchData other = (MatchData) object;
        if ((this.id == null && other.id != null) || (this.id != null && 
                !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * generates entity string information
     * @return entity information 
     */
    @Override
    public String toString() {
        return "Match number " + id + ". Score: " + teamagoals + ":" + 
                teambgoals;
    }
    
}
