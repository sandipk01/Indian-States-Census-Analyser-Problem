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
    private static final String INCORRECT_CSV_FILE = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/test/resources/IncorrectStateCensusData.csv";

    @Test
    public void GivenNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, StateCensusAnalyserException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        Assert.assertEquals(28, stateCensusAnalyser.load());
    }

    @Test
    public void GivenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_NAME);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void GivenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_TYPE);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void GivenFileDelimiter_WhenInCorrect_ThenShouldThrowNoSuchFieldException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(INCORRECT_CSV_FILE);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILED_EXCEPTION, e.type);
        }
    }
}
