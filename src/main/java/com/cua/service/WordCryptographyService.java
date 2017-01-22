package com.cua.service;

/**
 * Created by ryan.zhu on 21/01/2017.
 */
public interface WordCryptographyService {

    String encode(String json) throws Exception;

    String decode(String json) throws Exception;

}
