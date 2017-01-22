package com.cua.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ryan.zhu on 21/01/2017.
 */

@RestController
public class ErrorHandlerController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity  error() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
