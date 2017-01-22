package com.cua.controller;

import com.cua.representation.WordsStringRep;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ryan.zhu on 21/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordCryptographyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void encodeString() throws Exception {
        // Test the default scenario - including the "Z" to "B" and "z" to "c" scenarios
        WordsStringRep encodeInputRep = new WordsStringRep("Encode FuzZy");
        this.mockMvc.perform(post("/encode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encodeInputRep)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"wordsString\":\"Ksgrff KycBz\"}"));

        // Test the punctuation scenario
        encodeInputRep = new WordsStringRep("Enc!de Fuz,y");
        this.mockMvc.perform(post("/encode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encodeInputRep)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"wordsString\":\"Ksg!ff Kyc,z\"}"));
    }

    @Test
    public void decodeString() throws Exception {
        // Test the default scenario - including the "Z" to "B" and "z" to "c" scenarios
        WordsStringRep decodeInputRep = new WordsStringRep("Ksgrff KycBz");
        this.mockMvc.perform(post("/decode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(decodeInputRep)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"wordsString\":\"Encode FuzZy\"}"));

        // Test the punctuation scenario
        decodeInputRep = new WordsStringRep("Ksg!ff Kyc,z");
        this.mockMvc.perform(post("/decode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(decodeInputRep)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"wordsString\":\"Enc!de Fuz,y\"}"));
    }

    @Test
    public void testWrongUrlCase() throws Exception {
        this.mockMvc.perform(get("/randomUrl"))
                .andExpect(status().isNotFound());

        this.mockMvc.perform(post("/randomUrl")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
