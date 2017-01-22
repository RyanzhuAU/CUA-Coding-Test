package com.cua.controller;

import com.cua.representation.WordsStringRep;
import com.cua.service.WordCryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ryan.zhu on 21/01/2017.
 */

@RestController
public class WordCryptographyController {

    @Autowired
    private WordCryptographyService wordCryptographyService;

    /**
     * POST /encode - encode the string
     * e.g. input: Encode Fuzzy output: Ksgrff Kycbz
     *
     * @param json
     * @return JSON - the string after encoding and status 200, or status 400
     */
    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public WordsStringRep encodeString(@RequestBody String json) {
        try {
            String outputString = wordCryptographyService.encode(json);

            return new WordsStringRep(outputString);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * POST /encode - decode the string
     * e.g. input: Encode Fuzzy output: Ksgrff Kycbz
     *
     * @param json
     * @return JSON - the string after decoding and status 200, or status 400
     */
    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public WordsStringRep decodeString(@RequestBody String json) {
        try {
            String outputString = wordCryptographyService.decode(json);

            return new WordsStringRep(outputString);

        } catch (Exception e) {
            return null;
        }
    }
}
