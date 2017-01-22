package com.cua.service;

import com.cua.representation.WordsStringRep;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by ryan.zhu on 21/01/2017.
 */
@Service
public class WordCryptographyServiceImpl implements WordCryptographyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * returns the encoded string
     *
     * @param json
     * @return encoded string
     * @throws Exception
     */
    public String encode(String json) throws Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            WordsStringRep inputStringRep = om.readValue(json, WordsStringRep.class);

            logger.info("Start encoding the string: " + inputStringRep.getWordsString());

            //split the input string by space
            String[] words = inputStringRep.getWordsString().split("\\s+");

            if (words.length <= 0) {
                return inputStringRep.getWordsString();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(encodeWord(words[0]));

            for (int i = 1; i < words.length; i++) {
                sb.append(" ").append(encodeWord(words[i]));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new Exception("Error occur.");
        }
    }

    /**
     * returns the decoded string
     *
     * @param json
     * @return decoded string
     * @throws Exception
     */
    public String decode(String json) throws Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            WordsStringRep inputStringRep = om.readValue(json, WordsStringRep.class);

            logger.info("Start decoding the string: " + inputStringRep.getWordsString());

            //split the input string by space
            String[] words = inputStringRep.getWordsString().split("\\s+");

            if (words.length <= 0) {
                return inputStringRep.getWordsString();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(decodeWord(words[0]));

            for (int i = 1; i < words.length; i++) {
                sb.append(" ").append(decodeWord(words[i]));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new Exception("Error occur.");
        }
    }

    //Handle the word encode logic
    private String encodeWord(String word) {
        char[] chars = word.toCharArray();
        int positionIndex;

        for (int i = 0; i < chars.length; i++) {
            positionIndex = chars.length - i;

            if (Character.isLetter(chars[i])) {
                if (Character.isUpperCase(chars[i])) {
                    chars[i] = (char) ((chars[i] + positionIndex - 'A') % 26 + 'A');
                } else {
                    chars[i] = (char) ((chars[i] + positionIndex - 'a') % 26 + 'a');
                }
            }
        }

        return String.valueOf(chars);
    }

    //Handle the word decode logic
    private String decodeWord(String word) {
        char[] chars = word.toCharArray();
        int positionIndex;

        for (int i = 0; i < chars.length; i++) {
            positionIndex = chars.length - i;

            if (Character.isLetter(chars[i])) {
                if (Character.isUpperCase(chars[i])) {
                    chars[i] = (char) ((chars[i] + 26 - positionIndex - 'A') % 26 + 'A');
                } else {
                    chars[i] = (char) ((chars[i] + 26 - positionIndex - 'a') % 26 + 'a');
                }
            }
        }

        return String.valueOf(chars);
    }
}
