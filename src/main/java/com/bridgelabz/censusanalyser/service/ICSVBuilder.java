package com.bridgelabz.censusanalyser.service;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {

    public <E> Iterator<E> load(Reader reader, Class className);

    public <E> List loadList(Reader reader, Class csvClass);

    public <E> int size(Iterator<E> iterator);
}
