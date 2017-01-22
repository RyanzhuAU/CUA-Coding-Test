package com.cua.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ryan.zhu on 21/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCryptographyServiceTest {

    private WordCryptographyService wordCryptographyService;
    private String userId;

    @Before
    public void setup() throws Exception {
        wordCryptographyService = new WordCryptographyServiceImpl();

//        StringState stringState = new StringState("test1", "123abc12");
//        stringStateRepository.save(stringState);
    }

    @Test
    public void testEncodeInputString() throws Exception {
        //Test the default scenario - including the "Z" to "B" and "z" to "c" scenarios
        String outputString = wordCryptographyService.encode("{\"wordsString\":\"Encode FuzZy\"}");
        Assert.assertEquals(outputString, "Ksgrff KycBz");

        //Test the punctuation scenario
        outputString = wordCryptographyService.encode("{\"wordsString\":\";Enc!de Fuz,y]\"}");
        Assert.assertEquals(outputString, ";Ksg!ff Lzd,a]");
    }

    @Test
    public void testDecodeInputString() throws Exception {
        //Test the default scenario - including the "Z" to "B" and "z" to "c" scenarios
        String outputString = wordCryptographyService.decode("{\"wordsString\":\"Ksgrff KycBz\"}");
        Assert.assertEquals(outputString, "Encode FuzZy");

        //Test the punctuation scenario
        outputString = wordCryptographyService.decode("{\"wordsString\":\"Ksg!ff Kyc,z\"}");
        Assert.assertEquals(outputString, "Enc!de Fuz,y");
    }
}
