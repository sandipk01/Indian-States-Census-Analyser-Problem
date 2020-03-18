package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.StateCensusAnalyserException;
import com.bridgelabz.censusanalyser.service.CsvStates;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.bridgelabz.censusanalyser.utils.Constants.*;

public class TestStateAnalyser {

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

    @Test
    public void givenStateCodeCsvRecord_WhenCompared_ThenShouldMatch() throws IOException, StateCensusAnalyserException {
        CsvStates csvStates = new CsvStates(STATE_CODE_CSV);
        Assert.assertEquals(37, csvStates.loadCsvState());
    }

    @Test
    public void givenStateCodeFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        CsvStates csvStates = new CsvStates(WRONG_STATE_CODE_FILE_NAME);
        try {
            csvStates.loadCsvState();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        CsvStates csvStates = new CsvStates(WRONG_STATE_CODE_FILE_TYPE);
        try {
            csvStates.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenStateCodeFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        CsvStates csvStates = new CsvStates(INCORRECT_STATE_CODE_CSV_FILE);
        try {
            csvStates.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        CsvStates csvStates = new CsvStates(INCORRECT_STATE_CODE_HEADER_FILE);
        try {
            csvStates.checkCsv();
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
}
