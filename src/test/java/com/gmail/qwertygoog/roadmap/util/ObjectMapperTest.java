package com.gmail.qwertygoog.roadmap.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Skill;
import org.junit.jupiter.api.Test;

import static com.gmail.qwertygoog.roadmap.model.Level.A;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {

        MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

    }

    @Test
    void testSerializeEnumAndExpectOkWithDoubleQuotes() throws JsonProcessingException {
        String json = MAPPER.writeValueAsString(Level.A);
        System.out.println(json);
        assertEquals("\"A\"", json);
    }
    @Test
    void testDeserializeFromStringAndExpectOk() throws JsonProcessingException {
        String json = "A";
        Skill skill = new Skill();
        skill.setLevel(MAPPER.readValue(json,Level.class));
        assertEquals(skill.getLevel(),Level.A);
    }
}
