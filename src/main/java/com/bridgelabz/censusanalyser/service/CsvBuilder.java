package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class CsvBuilder implements ICSVBuilder {

    //Generic method to load the csv data
    public <E> Iterator<E> load(Reader reader, Class className) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(className).build();
        return csvToBean.iterator();
    }

    //Method for get size of data
    public <E> int size(Iterator<E> iterator) {
        int counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        return counter;
    }
}
