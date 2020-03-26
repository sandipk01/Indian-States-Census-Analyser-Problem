package com.bridgelabz.censusanalyser.service;

import com.bridgelabz.censusanalyser.model.CSVStateCensus;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Comparator;
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

    //Method for sorting using comparator
    public <E> List<E> sortingList(Comparator<E> comparator, List censusRecords) {
        for (int iterate = 0; iterate < censusRecords.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < censusRecords.size() - iterate - 1; Inneriterate++) {
                E census1 = (E) censusRecords.get(Inneriterate);
                E census2 = (E) censusRecords.get(Inneriterate + 1);
                if (comparator.compare(census1, census2) > 0) {
                    censusRecords.set(Inneriterate, census2);
                    censusRecords.set(Inneriterate + 1, census1);
                }
            }
        }
        return  censusRecords;
    }

    //Method to convert data in json format
    public <E> String listConvertingToJson(List<E> list) {
         return new Gson().toJson(list);
    }

}
