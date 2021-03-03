/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tests;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import pl.polsl.matchstatisticsweb.models.Player;
import pl.polsl.matchstatisticsweb.models.Team;

/**
 * Class for team model tests
 * @author Jedrzej Piaskowski
 * @version 3.0
 */
public class TeamTests {

    private Team team;

    /**
     * Creating new team before each test
     */
    @BeforeEach
    public void setUp() {
        team = new Team();
    }
    /**
     * Testing exception throw, when number of shots given is impossiblle
     */
    @ParameterizedTest
    @CsvSource({"-15", "-1"})
    void shouldThrowExceptionImpossibleSituationaddPlayerShots(int x)
            throws ImpossibleSituationException {
        try {
            team.addPlayer("test", x, 0);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Number of shots must be >= 0.", exception.getMessage()
            );
        }
    }
    /**
     * Testing exception throw, when number of shots on the target 
     * given is impossiblle
     */
    @ParameterizedTest
    @CsvSource({"0,-1", "0,1", "100,200"})
    void shouldThrowExceptionImpossibleSituationaddPlayerShotsTarget(int x,
            int y)
            throws ImpossibleSituationException {
        try {
            team.addPlayer("test", x, y);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Number of shots on target must be >= 0 and <= number "
                    + "of shots.", exception.getMessage()
            );
        }
    }
    /**
     *Testing, if new-added players parameters are correct
     */
    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "100,5"})
    void shouldAddPlayerCorretly(int x, int y)
            throws ImpossibleSituationException {
        team.addPlayer("test", x, y);
        ArrayList<Player> tempArray = (ArrayList<Player>) team.getPlayers();
        Assert.assertEquals("Shots arent correct", x,
                tempArray.get(tempArray.size() - 1).getShots());
        Assert.assertEquals("Shots on the target arent correct", y,
                tempArray.get(tempArray.size() - 1).getShotsOnTarget());

    }
    /**
     *Testing if clearing the players list works and if new size is correct
     */
    @Test
    void shouldClearPlayersAndSizeTest() throws ImpossibleSituationException {
        Assert.assertEquals("Wrong size of empty array", 0,
                team.getPlayers().size());
        team.addPlayer("test", 0, 0);
        Assert.assertEquals("Wrong size of array with 1 player", 1,
                team.getPlayers().size());
        team.clearPlayers();
        Assert.assertEquals("Wrong size of cleared array", 0,
                team.getPlayers().size());

    }
    /**
     * Testing, if summing up shots on target is correct (2 elements)
     */
    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "1,0", "100,5"})
    void shouldCalculateCorrectShotsOnTargetSumTwoElements(int x, int y)
            throws ImpossibleSituationException {
        int tempSum = x + y;
        team.addPlayer("test", x, x);
        team.addPlayer("test", y, y);
        Assert.assertEquals("Sum is not correct", tempSum,
                team.calculateShotsOnTarget());
    }
    /**
     * Testing, if summing up shots on target is correct (1 element)
     */
    @ParameterizedTest
    @CsvSource({"0", "1", "100"})
    void shouldCalculateCorrectShotsOnTargetSumOneElement(int x)
            throws ImpossibleSituationException {
        team.addPlayer("test", x, x);
        Assert.assertEquals("Sum is not correct", x,
                team.calculateShotsOnTarget());
    }
    /**
     * Testing, if summing up shots on target is correct (0 elements)
     */
    @Test
    void shouldCalculateCorrectShotsOnTargetSumNoElements()
            throws ImpossibleSituationException {
        Assert.assertEquals("Sum is not correct", 0,
                team.calculateShotsOnTarget());
    }
    /**
     *Testing, if summing up shots is correct (2 elements)
     */
    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "1,0", "100,5"})
    void shouldCalculateCorrectShotsSumTwoElements(int x, int y)
            throws ImpossibleSituationException {
        int tempSum = x + y;
        team.addPlayer("test", x, 0);
        team.addPlayer("test", y, 0);
        Assert.assertEquals("Sum is not correct", tempSum,
                team.calculateShots());
    }
    /**
     * Testing, if summing up shots is correct (0 elements)
     */
    @Test
    void shouldCalculateCorrectShotsSumNoElements()
            throws ImpossibleSituationException {
        Assert.assertEquals("Sum is not correct", 0, team.calculateShots());
    }
    /**
     * Testing, if summing up shots is correct (1 element)
     */
    @ParameterizedTest
    @CsvSource({"0", "1", "100"})
    void shouldCalculateCorrectShotsSumOneElement(int x)
            throws ImpossibleSituationException {
        team.addPlayer("test", x, 0);
        Assert.assertEquals("Sum is not correct", x, team.calculateShots());
    }
    /**
     * Testing exception throwm when number of goals is impossible
     */
    @ParameterizedTest
    @CsvSource({"-15", "-1"})
    void shouldThrowExceptionImpossibleSituationSetGoals(int x)
            throws ImpossibleSituationException {
        try {
            team.setGoals(x);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Number of goals must be >= 0 ", exception.getMessage()
            );
        }
    }
    /**
     * Testing, if ball possession percent calculation is correct
     */
    @ParameterizedTest
    @CsvSource({"90,100", "0,0", "45,50"})
    void shouldCalculateCorrectPossessionPercent(int x, int y)
            throws ImpossibleSituationException {
        team.setPossessionMinutes(x);
        Assert.assertEquals("Possession percent is not correct", (float) y,
                team.calculatePossessionPercent(), 0.01);
    }
    /**
     * Testing, if shots on target percent calculation is correct (1 element)
     */
    @ParameterizedTest
    @CsvSource({"100,100,100", "0,0,0", "100,45,45"})
    void shouldCalculateCorrectShotsOnTargetPercentOneElement(int x, int y,
            int z) throws ImpossibleSituationException {
        team.addPlayer("test", x, y);
        Assert.assertEquals("Shots percent is not correct", (float) z,
                team.calculateShotsOnTargetPercent(), 0.01);
    }
    /**
     * Testing, if shots on target percent calculation is correct (0 elements)
     */
    @Test
    void shouldCalculateCorrectShotsOnTargetPercentNoElements()
            throws ImpossibleSituationException {
        Assert.assertEquals("Shots percent is not correct", 0,
                team.calculateShotsOnTargetPercent(), 0.01);
    }
    /**
     * Testing exception throw, when the number of possesion minutes being set
     * is impossible
     */
    @ParameterizedTest
    @CsvSource({"-15", "91", "-1"})
    void shouldThrowExceptionImpossibleSituationSetPossessionMinutes(int x)
            throws ImpossibleSituationException {
        try {
            team.setPossessionMinutes(x);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Possession must be >= 0 ", exception.getMessage()
            );
        }
    }
}
