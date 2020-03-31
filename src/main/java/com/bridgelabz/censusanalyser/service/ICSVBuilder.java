package com.bridgelabz.censusanalyser.service;

import java.io.Reader;
import java.util.*;

public interface ICSVBuilder {

    public <E> List<E> loadFromList(Reader reader, Class className);

    public <S, T> LinkedHashMap<S, T> sorting(Comparator<Map.Entry<S, T>> comparator, HashMap<S, T> censusHashMap);

    public <E> Iterator<E> loadFromIterator(Reader reader, Class className);
}
