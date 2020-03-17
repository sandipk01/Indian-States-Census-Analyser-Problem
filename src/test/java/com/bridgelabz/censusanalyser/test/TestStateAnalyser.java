package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestStateAnalyser {

    private static final String CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/test/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_NAME = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/test/resources/StateCensus.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/test/resources/StateCensusData.txt";

    @Test
    public void GivenNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, StateCensusAnalyserException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        Assert.assertEquals(28, stateCensusAnalyser.size());
    }

    @Test
    public void GivenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_NAME);
        try {
            stateCensusAnalyser.size();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void GivenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_TYPE);
        try {
            stateCensusAnalyser.size();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }
}
