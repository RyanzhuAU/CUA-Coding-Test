package com.cua.representation;

import lombok.Data;

/**
 * Created by ryan.zhu on 21/01/2017.
 */
@Data
public class WordsStringRep {

    private String wordsString;

    public WordsStringRep() {

    }

    public WordsStringRep(String wordsString) {
        this.wordsString = wordsString;
    }
}
