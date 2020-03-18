package com.bridgelabz.censusanalyser.exception;

public class StateCensusAnalyserException extends Exception{

    public enum TypeOfException{
        NO_SUCH_FILE_EXCEPTION,INCORRECT_DELIMITER_OR_HEADER,NO_CSV_FILE
    }

    public TypeOfException type;

    public StateCensusAnalyserException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}
