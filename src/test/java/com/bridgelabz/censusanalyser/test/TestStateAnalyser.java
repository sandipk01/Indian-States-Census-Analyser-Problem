package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static com.bridgelabz.censusanalyser.utils.Constants.*;

public class TestStateAnalyser {

    @Test
    public void givenNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>(CSV_FILE_PATH, CSVStateCensus.class);
        Assert.assertEquals(28, stateCensusAnalyser.load().size());
    }

    @Test
    public void givenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>(WRONG_CSV_FILE_NAME, CSVStateCensus.class);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>(WRONG_CSV_FILE_TYPE, CSVStateCensus.class);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>(INCORRECT_CSV_FILE, CSVStateCensus.class);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>(INCORRECT_HEADER_FILE, CSVStateCensus.class);
        try {
            stateCensusAnalyser.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>(STATE_CODE_CSV, CsvStateCode.class);
        Assert.assertEquals(37, csvStates.checkCsv().size());
    }

    @Test
    public void givenStateCodeFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>(WRONG_STATE_CODE_FILE_NAME, CsvStateCode.class);
        try {
            csvStates.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>(WRONG_STATE_CODE_FILE_TYPE, CsvStateCode.class);
        try {
            csvStates.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenStateCodeFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>(INCORRECT_STATE_CODE_CSV_FILE, CsvStateCode.class);
        try {
            csvStates.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>(INCORRECT_STATE_CODE_HEADER_FILE, CsvStateCode.class);
        try {
            csvStates.checkCsv();
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser = new StateCensusAnalyser<>(CSV_FILE_PATH, CSVStateCensus.class);
        HashMap<Integer, CSVStateCensus> csvStateCensuses = csvStateCensusStateCensusAnalyser.checkCsv();
        Comparator<Map.Entry<Integer, CSVStateCensus>> censusComparator = Comparator.comparing(csvStateCensusEntry -> csvStateCensusEntry.getValue().getStateName());
        String sortedByValue = csvStateCensusStateCensusAnalyser.getSortedJsonData(censusComparator, csvStateCensuses);
        CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
        Assert.assertEquals("Andhra Pradesh", csvStateCodes[0].getStateName());
        Assert.assertEquals("West Bengal", csvStateCodes[csvStateCodes.length-1].getStateName());
    }

}
