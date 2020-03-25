package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CsvBuilder implements ICSVBuilder {

    //Generic method to load the csv data
    public <E> Iterator<E> load(Reader reader, Class className) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withIgnoreLeadingWhiteSpace(true).withType(className).build();
        return csvToBean.iterator();
    }

    //Generic method to load csv data using list
    public <E> List loadList(Reader reader, Class className) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withIgnoreLeadingWhiteSpace(true).withType(className).build();
        return csvToBean.parse();
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
