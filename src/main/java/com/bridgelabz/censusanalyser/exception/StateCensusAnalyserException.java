package com.bridgelabz.censusanalyser.exception;

public class StateCensusAnalyserException extends Exception{

    public enum TypeOfException{
        NO_SUCH_FILE_EXCEPTION,NO_SUCH_FILED_EXCEPTION,NO_CSV_FILE
    }

    public TypeOfException type;

    public StateCensusAnalyserException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}
