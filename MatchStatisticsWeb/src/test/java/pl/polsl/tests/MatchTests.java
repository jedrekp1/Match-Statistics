/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tests;

import junit.framework.Assert;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.matchstatisticsweb.exceptions.ImpossibleSituationException;
import pl.polsl.matchstatisticsweb.models.Match;
import pl.polsl.matchstatisticsweb.models.Result;
import pl.polsl.matchstatisticsweb.models.Team;

/**
 * Class for match model tests
 *
 * @author Jedrzej Piaskowski
 * @version 3.0
 */
public class MatchTests {

    private Match match;

    /**
     * Setting up match object before the tests
     */
    @BeforeEach
    public void setUp() {
        Team tempTeamA = new Team();
        Team tempTeamB = new Team();
        match = new Match(tempTeamA, tempTeamB);
    }

    /**
     * Testing exception throw, when incorrect possession is set
     */
    @ParameterizedTest
    @CsvSource({"-15,-15", "46,46", "100,0", "-10,0", "0,-10", "0,100"})
    void shouldThrowExceptionImpossibleSituationSetPossession(int x, int y)
            throws ImpossibleSituationException {
        try {
            match.setPossessions(x, y);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Sum of the possessions can not be higher than 90",
                    exception.getMessage());
        }
    }

    /**
     * Testing exception throw, when incorrect number of goals is set
     */
    @ParameterizedTest
    @CsvSource({"-15,-15", "-10,0", "0,-10"})
    void shouldThrowExceptionImpossibleSituationSetGoals(int x, int y)
            throws ImpossibleSituationException {
        try {
            match.setGoalsAndResult(x, y);
            fail("Exception wasn't thrown!");
        } catch (ImpossibleSituationException exception) {
            assertEquals("Number of goals must be >= 0.", exception.getMessage()
            );
        }
    }

    /**
     * Testing correct winner setting (A winner, B loser)
     */
    @ParameterizedTest
    @CsvSource({"15,0", "1,0"})
    void shouldSetAWinnerBLoser(int x, int y) throws
            ImpossibleSituationException {
        match.setGoalsAndResult(x, y);
        Assert.assertEquals("Winner not correct", Result.WINNER,
                match.getTeamA().getTeamResult());
        Assert.assertEquals("Loser not correct", Result.LOSER,
                match.getTeamB().getTeamResult());
    }

    /**
     * Testing correct winner setting (B winner, A loser)
     */
    @ParameterizedTest
    @CsvSource({"0,15", "0,1"})
    void shouldSetBWinnerALoser(int x, int y) throws
            ImpossibleSituationException {
        match.setGoalsAndResult(x, y);
        Assert.assertEquals("Winner not correct", Result.WINNER,
                match.getTeamB().getTeamResult());
        Assert.assertEquals("Loser not correct", Result.LOSER,
                match.getTeamA().getTeamResult());
    }

    /**
     * Testing correct draw setting
     */
    @ParameterizedTest
    @CsvSource({"15,15", "1,1"})
    void shouldSetDraw(int x, int y) throws
            ImpossibleSituationException {
        match.setGoalsAndResult(x, y);
        Assert.assertEquals("Team B draw not correct", Result.DRAW,
                match.getTeamB().getTeamResult());
        Assert.assertEquals("Team A draw not correct", Result.DRAW,
                match.getTeamA().getTeamResult());
    }

}
