package com.bridgelabz.censusanalyser.service;

import java.io.Reader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {

    public <S,T> HashMap<S,T> loadHashMap(Reader reader, Class className);

    public <S,T> HashMap<S,T> sortingList(Comparator comparator, HashMap<S,T> censusHashMap);
}
