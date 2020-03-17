package com.bridgelabz.censusanalyser.exception;

public class StateCensusAnalyserException extends Exception{

    public enum TypeOfException{
        NO_SUCH_FILE_EXCEPTION
    }

    public TypeOfException type;

    public StateCensusAnalyserException(String message, TypeOfException type) {
        super(message);
        this.type = type;
    }
}
