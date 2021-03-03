/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.matchstatisticsweb.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Team database entity class
 * @author Jedrzej Piaskowski
 * @version 5.0
 */
@Entity
@Table(name = "TEAMS")
@NamedQueries({
    @NamedQuery(name = "TeamData.findAll", query = "SELECT t FROM TeamData t"),
    @NamedQuery(name = "TeamData.findById", query = "SELECT t FROM TeamData t "
            + "WHERE t.id = :id"),
    @NamedQuery(name = "TeamData.findByName", query = "SELECT t FROM TeamData t"
            + " WHERE t.name = :name")})
public class TeamData implements Serializable {
    /**
     * serial version UID (generated with entity)
     */
    private static final long serialVersionUID = 1L;
        /**
     * id
     */
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
        /**
     * team name
     */
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
        /**
     * related matches collection (as team A)
     */
    @OneToMany(mappedBy = "teamb")
    private Collection<MatchData> matchDataCollection;
            /**
     * related matches collection (as team B)
     */
    @OneToMany(mappedBy = "teama")
    private Collection<MatchData> matchDataCollection1;

    /**
     * constructor
     */
    public TeamData() {
    }

    /**
     * constructor with id to set
     * @param id id to set
     */
    public TeamData(Integer id) {
        this.id = id;
    }

    /**
     * id getter
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id setter
     * @param id id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Team name getter
     * @return name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * team name setter
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * related matches getter
     * @return
     */
    public Collection<MatchData> getMatchDataCollection() {
        return matchDataCollection;
    }

    /**
     * related matches setter
     * @param matchDataCollection matches to set
     */
    public void setMatchDataCollection(
            Collection<MatchData> matchDataCollection) {
        this.matchDataCollection = matchDataCollection;
    }

    /**
     * related matches (as team B) getter
     * @return matches collection (as team B)
     */
    public Collection<MatchData> getMatchDataCollection1() {
        return matchDataCollection1;
    }

    /**
     * related matches (as team B) setter
     * @param matchDataCollection1 matches collection (as team B)
     */
    public void setMatchDataCollection1(
            Collection<MatchData> matchDataCollection1) {
        this.matchDataCollection1 = matchDataCollection1;
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
        if (!(object instanceof TeamData)) {
            return false;
        }
        TeamData other = (TeamData) object;
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
        return "Team number: " + id + ". Name: " + name;
    }
    
}
