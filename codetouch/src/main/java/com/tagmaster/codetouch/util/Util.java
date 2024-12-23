package com.tagmaster.codetouch.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.dynalink.beans.StaticClass;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
        return data.toLocalDate();
    }

    //date to day&time
    public static LocalDateTime DateToDateTime(LocalDate data){
        return data.atStartOfDay();
    }

    //string to local date time
    public static LocalDateTime StringToLocalDateTime(String Str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(Str, formatter);
    }

    // Null or emptyList check
    public static <T> List<T> checkNull(List<T> list) {
        return (list == null || list.isEmpty()) ? Collections.emptyList() : list;
        //null이거나 빈리스트는 emptyList 로 리턴한다
    }
    // 일반 오브젝트 null 체크 메서드
    public static <T> T checkNull(T obj) {
        return obj == null ? null : obj;
    }
}

