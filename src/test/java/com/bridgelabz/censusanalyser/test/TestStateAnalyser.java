package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.dao.CensusDao;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.bridgelabz.censusanalyser.model.CsvStateCode;
import com.bridgelabz.censusanalyser.model.UsCensusData;
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
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
        Assert.assertEquals(28, stateCensusAnalyser.checkCsv(CSV_FILE_PATH,CSVStateCensus.class).size());
    }

    @Test
    public void givenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
        try {
            stateCensusAnalyser.checkCsv(WRONG_CSV_FILE_NAME,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
        try {
            stateCensusAnalyser.checkCsv(WRONG_CSV_FILE_TYPE,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
        try {
            stateCensusAnalyser.checkCsv(INCORRECT_CSV_FILE,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CSVStateCensus> stateCensusAnalyser = new StateCensusAnalyser<>();
        try {
            stateCensusAnalyser.checkCsv(INCORRECT_HEADER_FILE,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>();
        Assert.assertEquals(37, csvStates.checkCsv(STATE_CODE_CSV,CsvStateCode.class).size());
    }

    @Test
    public void givenStateCodeFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>();
        try {
            csvStates.checkCsv(WRONG_STATE_CODE_FILE_NAME,CsvStateCode.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>();
        try {
            csvStates.checkCsv(WRONG_STATE_CODE_FILE_TYPE,CsvStateCode.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenStateCodeFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>();
        try {
            csvStates.checkCsv(INCORRECT_STATE_CODE_CSV_FILE,CsvStateCode.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStates = new StateCensusAnalyser<>();
        try {
            csvStates.checkCsv(INCORRECT_STATE_CODE_HEADER_FILE,CsvStateCode.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser = new StateCensusAnalyser<>();
        HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
        String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedCensusJsonData(csvStateCensuses);
        CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
        Assert.assertEquals("Andhra Pradesh", csvStateCodes[0].state);
        Assert.assertEquals("West Bengal", csvStateCodes[csvStateCodes.length-1].state);
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenSorted_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        StateCensusAnalyser<CsvStateCode> csvStateCensusStateCensusAnalyser = new StateCensusAnalyser<>();
        HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser.checkCsv(STATE_CODE_CSV,CsvStateCode.class);
        String sortedByValue = csvStateCensusStateCensusAnalyser.getStateCodeWiseSortedJsonData(csvStateCensuses);
        CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
        Assert.assertEquals("AD", csvStateCodes[0].stateCode);
        Assert.assertEquals("WB", csvStateCodes[csvStateCodes.length-1].stateCode);
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenEmpty_ShouldReturnNoCensusData() throws IOException {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser = new StateCensusAnalyser<>();
        HashMap<String, CensusDao> csvStateCensuses = null;
        try {
            csvStateCensuses = csvStateCensusStateCensusAnalyser.checkCsv(EMPTY_STATE_CENSUS_FILE, CSVStateCensus.class);
            csvStateCensusStateCensusAnalyser.getStateWiseSortedCensusJsonData(csvStateCensuses);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenEmpty_ShouldReturnNoCensusData() throws IOException {
        StateCensusAnalyser<CsvStateCode> csvStateCensusStateCensusAnalyser = new StateCensusAnalyser<>();
        HashMap<String, CensusDao> csvStateCode = null;
        try {
            csvStateCode = csvStateCensusStateCensusAnalyser.checkCsv(EMPTY_STATE_CODE_FILE, CsvStateCode.class);
            csvStateCensusStateCensusAnalyser.getStateCodeWiseSortedJsonData(csvStateCode);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CENSUS_CODE_DATA, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnFirstValue() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedSPopulation(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Uttar Pradesh", csvStateCodes[0].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnLastValue() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedSPopulation(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Sikkim", csvStateCodes[csvStateCensuses.size()-1].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensityPerSquareKM_ShouldReturnFirstSortedData() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedSPopulationDensity(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Bihar", csvStateCodes[0].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensityPerSquareKM_ShouldReturnLastSortedData() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedSPopulationDensity(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Mizoram", csvStateCodes[csvStateCodes.length-1].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAreaPerKm_ShouldReturnFirstSortedData() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedStateArea(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Rajasthan", csvStateCodes[0].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAreaPerKm_ShouldReturnLastSortedData() {
        StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser=new StateCensusAnalyser<>();
        try {
            StateCensusAnalyser<CSVStateCensus> csvStateCensusStateCensusAnalyser1 = new StateCensusAnalyser<>();
            HashMap<String, CensusDao> csvStateCensuses = csvStateCensusStateCensusAnalyser1.checkCsv(CSV_FILE_PATH,CSVStateCensus.class);
            String sortedByValue = csvStateCensusStateCensusAnalyser.getStateWiseSortedStateArea(csvStateCensuses);
            CensusDao[] csvStateCodes = new Gson().fromJson(sortedByValue, CensusDao[].class);
            Assert.assertEquals("Goa", csvStateCodes[csvStateCodes.length-1].state);
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        StateCensusAnalyser<UsCensusData> stateCensusAnalyser = new StateCensusAnalyser<>();
        Assert.assertEquals(51, stateCensusAnalyser.checkCsv(US_CENSUS_FILE,UsCensusData.class).size());
    }

}

