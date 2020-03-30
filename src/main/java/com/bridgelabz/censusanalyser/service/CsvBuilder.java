package com.bridgelabz.censusanalyser.service;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.*;

public class CsvBuilder implements ICSVBuilder {

    //Generic method to load the csv data using iterator
    public <E> Iterator<E> load(Reader reader, Class className) {
        CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withIgnoreLeadingWhiteSpace(true).withType(className).build();
        return csvToBean.iterator();
    }

    //Generic method to load csv data using list
    public <S,T> HashMap<S,T> loadHashMap(Reader reader, Class className) {
        CsvToBean csvToBean = new CsvToBeanBuilder(reader).withIgnoreLeadingWhiteSpace(true).withType(className).build();
        List list=csvToBean.parse();
        HashMap<Integer ,Object> map = new HashMap<>();
        Integer count = 0;
        for (Object record:list) {
            map.put(count,record);
            count++;
        }
        return (HashMap<S, T>) map;
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
    public <S,T> HashMap<S,T> sortingList(Comparator comparator, HashMap<S,T> censusHashMap) {
        Set<Map.Entry<S, T>> entries = censusHashMap.entrySet();
        List<Map.Entry<S, T>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries, comparator);
        LinkedHashMap<S, T> sortedByValue = new LinkedHashMap<S,T>(listOfEntries.size());
        // copying entries from List to Map
        for (Map.Entry<S, T> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }
}
