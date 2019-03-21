package com.eshc.backend.rest;

public class OutdatedEntityVersionException extends IllegalStateException {

    public OutdatedEntityVersionException() {super();}

    public OutdatedEntityVersionException(String message) {super(message);}
}
