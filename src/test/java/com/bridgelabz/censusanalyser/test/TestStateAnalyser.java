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
    StateCensusAnalyser indianCensusAnalyser = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
    StateCensusAnalyser usCensusAnalyser=new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
    @Test
    public void givenNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        Assert.assertEquals(28, indianCensusAnalyser.checkCsv(CSV_FILE_PATH).size());
    }

    @Test
    public void givenFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(WRONG_CSV_FILE_NAME);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(WRONG_CSV_FILE_TYPE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(INCORRECT_CSV_FILE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(INCORRECT_HEADER_FILE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        Assert.assertEquals(37, indianCensusAnalyser.checkCsv(STATE_CODE_CSV,STATE_CODE_CSV).size());
    }

    @Test
    public void givenStateCodeFileName_WhenIncorrect_ThenShouldThrowsNoSuchFileException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(WRONG_STATE_CODE_FILE_NAME,WRONG_STATE_CODE_FILE_NAME);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_SUCH_FILE_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCodeFileType_WhenInCorrect_ThenShouldThrowNoSuchFileException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(WRONG_STATE_CODE_FILE_TYPE,WRONG_STATE_CODE_FILE_TYPE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CSV_FILE, e.type);
        }
    }

    @Test
    public void givenStateCodeFileDelimiter_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(INCORRECT_STATE_CODE_CSV_FILE,INCORRECT_STATE_CODE_CSV_FILE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeFileHeader_WhenInCorrect_ThenShouldThrowIncorrectDelimiterOrHeaderException() throws IOException {
        try {
            indianCensusAnalyser.checkCsv(INCORRECT_STATE_CODE_HEADER_FILE,INCORRECT_STATE_CODE_HEADER_FILE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenSortedStateWise_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
        String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.STATE);
        CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
        Assert.assertEquals("Andhra Pradesh", csvStateCodes[0].getStateName());
        Assert.assertEquals("West Bengal", csvStateCodes[csvStateCodes.length-1].getStateName());
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenSortedSateWise_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH,STATE_CODE_CSV);
        String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.STATE_CODE);
        CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
        Assert.assertEquals("AD", csvStateCodes[0].getStateCode());
        Assert.assertEquals("WB", csvStateCodes[csvStateCodes.length-1].getStateCode());
    }

    @Test
    public void givenTheStateCensusCSVFile_WhenEmpty_ShouldReturnNoCensusData() throws IOException {

        HashMap<String, CensusDao> csvStateCensuses = null;
        try {
            csvStateCensuses = indianCensusAnalyser.checkCsv(EMPTY_STATE_CENSUS_FILE);
            indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.STATE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenEmpty_ShouldReturnNoCensusData() throws IOException {

        HashMap<String, CensusDao> csvStateCode = null;
        try {
            csvStateCode = indianCensusAnalyser.checkCsv(EMPTY_STATE_CODE_FILE,EMPTY_STATE_CODE_FILE);
            indianCensusAnalyser.getSortedCensusData(csvStateCode,StateCensusAnalyser.SortingMode.STATE);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.TypeOfException.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedPopulationWise_ShouldReturnMostPopulatedState() {
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Uttar Pradesh", csvStateCodes[0].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedPopulationWise_ShouldReturnLeastPopulationState() {
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Sikkim", csvStateCodes[csvStateCensuses.size()-1].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedDensityPerSquareKmWise_ShouldReturnMostDensityState() {
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Bihar", csvStateCodes[0].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedDensityPerSquareKmWise_ShouldReturnLeastDensityState() {
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Mizoram", csvStateCodes[csvStateCodes.length-1].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAreaPerKmWise_ShouldReturnMostAreaState(){
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Rajasthan", csvStateCodes[0].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAreaPerKmWise_ShouldReturnLeastAreaState() {
        try {
            HashMap<String, CensusDao> csvStateCensuses = indianCensusAnalyser.checkCsv(CSV_FILE_PATH);
            String sortedByValue = indianCensusAnalyser.getSortedCensusData(csvStateCensuses,StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensus[] csvStateCodes = new Gson().fromJson(sortedByValue, CSVStateCensus[].class);
            Assert.assertEquals("Goa", csvStateCodes[csvStateCodes.length-1].getStateName());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusNumberOfRecord_WhenCompared_ThenShouldMatch() throws IOException, CSVBuilderException {
        Assert.assertEquals(51, usCensusAnalyser.checkCsv(US_CENSUS_FILE).size());
    }

    @Test
    public void givenUsStateCensusData_whenSortedPopulationWise_ShouldReturnMostPopulatedStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.POPULATION);
            UsCensusData[] usStateCodes = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("California", usStateCodes[0].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsStateCensusData_WhenSortedPopulationWise_ShouldReturnLeastPopulatedStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.POPULATION);
            UsCensusData[] usStateCodes = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("Wyoming", usStateCodes[usStateCodes.length-1].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsStateCensusData_WhenSortedPopulationDensityWise_ShouldReturnMostStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.DENSITY);
            UsCensusData[] usStateCodes = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("District of Columbia", usStateCodes[0].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsStateCensusData_WhenSortedPopulationDensityWise_ShouldReturnLeastStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.DENSITY);
            UsCensusData[] usStateData = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("Alaska", usStateData[usStateData.length-1].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsStateCensusData_WhenSortedAreaWise_ShouldReturnMostAreaStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.AREA);
            UsCensusData[] usStateCodes = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("Alaska", usStateCodes[0].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsStateCensusData_WhenSortedAreaWise_ShouldReturnLeastAreaStateName() {
        try {
            HashMap<String, CensusDao> csvUsStateCensuses = usCensusAnalyser.checkCsv(US_CENSUS_FILE);
            String sortedByValue = usCensusAnalyser.getSortedCensusData(csvUsStateCensuses,StateCensusAnalyser.SortingMode.AREA);
            UsCensusData[] usStateCodes = new Gson().fromJson(sortedByValue, UsCensusData[].class);
            Assert.assertEquals("District of Columbia", usStateCodes[usStateCodes.length-1].getState());
        } catch (CSVBuilderException | IOException e) {
            e.printStackTrace();
        }
    }

}

