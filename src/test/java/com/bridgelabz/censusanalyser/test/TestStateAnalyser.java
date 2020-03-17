package com.bridgelabz.censusanalyser.test;
import com.bridgelabz.censusanalyser.service.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

public class TestStateAnalyser {
    private static final String CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/test/resources/StateCensusData.csv";
    @Test
    public void GivenNumberOfRecord_WhenChecked_ThenShouldMatch() throws IOException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser(CSV_FILE_PATH);
        Assert.assertEquals(28,stateCensusAnalyser.size());
    }
}
