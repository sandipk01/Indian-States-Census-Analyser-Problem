package com.bridgelabz.censusanalyser;
import com.bridgelabz.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    private static String fileName;

    public StateCensusAnalyser(String fileName) {
        this.fileName = fileName;
    }

    public Iterator load() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CSVStateCensus.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
       Iterator iterator=csvToBean.iterator();
        return iterator;
    }

    public int size() throws IOException {
        int counter=0;
        Iterator itr=load();
        while(itr.hasNext()){
            counter++;
            itr.next();
        }
        return counter;
    }

}
