package com.tagmaster.codetouch.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@UtilityClass
public class Util {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // JSON -> Map 변환 (View로 전달하기 전)
    public HashMap<String, String> jsonToMap(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(jsonString, new TypeReference<HashMap<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // Object -> JSON 문자열 변환 (DB 저장용)
    public String objectToJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
    //day&time to date
    public static LocalDate DateTimeToDate(LocalDateTime data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return data.toLocalDate();
    }

    //date to day&time
    public static LocalDateTime DateToDateTime(LocalDate data){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return data.atStartOfDay();
    }
}

// DTO 고객이름 , 주소 ->
// DB에서 view: map으로변환
// DB에 넣을때

//from json to map (toView)

// from object() to json (toDB)
// DTO 새로 만들어주기

