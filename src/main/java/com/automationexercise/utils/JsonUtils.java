package com.automationexercise.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> List<T> readList(String filePath, TypeReference<List<T>> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(new File(filePath), typeReference);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to parse JSON file: " + filePath, exception);
        }
    }
}

