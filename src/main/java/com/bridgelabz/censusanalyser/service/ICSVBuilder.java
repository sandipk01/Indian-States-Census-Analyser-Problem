package com.bridgelabz.censusanalyser.service;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {

    public <E> Iterator<E> load(Reader reader, Class className);
    public <E> int size(Iterator<E> iterator);
}
