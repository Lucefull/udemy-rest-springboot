package br.com.lucefull.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationExcetion extends RuntimeException {

    public UnsupportedMathOperationExcetion(String e) {
        super(e);
    }

    private static final Long serialVersionUID = 1L;
}
