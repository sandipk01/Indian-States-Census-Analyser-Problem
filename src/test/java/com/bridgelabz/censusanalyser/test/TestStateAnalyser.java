package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestStateAnalyser {

    private static final String CSV_FILE_PATH = "/home/admin1/IdeaProjects/Indian-States-Census-Analyser-Problem/src/test/resources/StateCensusData.csv";
    private static final String WRONG_CSV_FILE_NAME = "/home/admin1/IdeaProjects/Indian-States-Census-Analyser-Problem/src/test/resources/StateCensus.csv";
    private static final String WRONG_CSV_FILE_TYPE = "/home/admin1/IdeaProjects/Indian-States-Census-Analyser-Problem/src/test/resources/StateCensusData.txt";
    private static final String INCORRECT_CSV_FILE = "/home/admin1/IdeaProjects/Indian-States-Census-Analyser-Problem/src/test/resources/IncorrectStateCensusData.csv";
    private static final String INCORRECT_HEADER_FILE = "/home/admin1/IdeaProjects/Indian-States-Census-Analyser-Problem/src/test/resources/IncorrectHeaderStateCensusData.csv";

    @Test
    public void givenNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, StateCensusAnalyserException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(CSV_FILE_PATH);
        Assert.assertEquals(28, stateCensusAnalyser.load());
    }

    @Test
    public void givenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_NAME);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_CSV_FILE_TYPE);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(INCORRECT_CSV_FILE);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(INCORRECT_HEADER_FILE);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
}
