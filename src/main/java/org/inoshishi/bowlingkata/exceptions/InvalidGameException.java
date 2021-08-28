package org.inoshishi.bowlingkata.exceptions;

/**
 * @author René Pascal Esemann
 * created on 28.08.2021
 **/
public class InvalidGameException extends Exception {
    public InvalidGameException(String message) {
        super(message);
    }
}
