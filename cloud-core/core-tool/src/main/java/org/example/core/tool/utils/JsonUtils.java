package org.example.core.tool.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * jackson 工具类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public class JsonUtils {

    /**
     * json string to List
     *
     * @param jsonString
     * @return List
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static final List readList(String jsonString) {
        return readValue(jsonString, List.class);
    }

    /**
     * json string to Map
     *
     * @param jsonString
     * @return Map
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static final Map readMap(String jsonString) {
        return readValue(jsonString, Map.class);
    }

    /**
     * json string to <T>
     *
     * @param jsonString
     * @return <T> T
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static final <T> T readValue(String jsonString, Class<T> type) {
        if (isEmpty(jsonString)) {
            return null;
        }
        try {
            return JsonUtils.getInstance().readValue(jsonString, type);
        } catch (Exception e) {
            throw ExceptionUtils.toUncheck(e);
        }
    }

    /**
     * json string to <T>
     *
     * @param json
     * @param type
     * @return <T> T
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static <T> T readValue(String json, TypeReference<T> type) {
        try {
            return JsonUtils.getInstance().readValue(json, type);
        } catch (JsonProcessingException e) {
            throw ExceptionUtils.toUncheck(e);
        }
    }

    /**
     * T to json string
     *
     * @param value
     * @return String
     * @throws RuntimeException if error
     * @author phh
     * @date 2020/6/24
     * @version V1.0
     */
    public static <T> String writeAsString(T value) {
        if (value == null) {
            return null;
        }
        try {
            return JsonUtils.getInstance().writeValueAsString(value);
        } catch (Exception e) {
            throw ExceptionUtils.toUncheck(e);
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    public static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static ObjectMapper INSTANCE = new JacksonObjectMapper().getJsonObjectMapper();
    }

    public static class JacksonObjectMapper {
        private String dateFormat = "yyyy-MM-dd HH:mm:ss";
        private String timeZone = TimeZone.getDefault().getID();

        public JacksonObjectMapper() {
        }

        public JacksonObjectMapper(String dateFormat, String timeZone) {
            this.dateFormat = dateFormat;
            this.timeZone = timeZone;
        }

        public ObjectMapper getXmlObjectMapper() {
            //TODO
            return null;
        }

        public synchronized ObjectMapper getJsonObjectMapper() {
            //创建ObjectMapper
            ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                    .json()
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build();
            //设置地点
            objectMapper.setLocale(Locale.getDefault());
            //去掉默认的时间戳格式
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //设置时区
            TimeZone tz = timeZone == null ? TimeZone.getTimeZone(ZoneId.systemDefault()) : TimeZone.getTimeZone(timeZone);
            objectMapper.setTimeZone(tz);
            //序列化时，日期的统一格式
            String sdf = dateFormat != null ? dateFormat : DateTimeUtils.YYYY_MM_DD_HH_MM_SS;
            objectMapper.setDateFormat(new SimpleDateFormat(sdf));
            //序列化处理
            objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
            objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
            objectMapper.findAndRegisterModules();
            //失败处理
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //单引号处理
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //反序列化时，属性不存在的兼容处理
            objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //日期格式化 java 8
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            DateTimeFormatter dft = dateFormat == null ? DateTimeUtils.FMT_YYYY_MM_DD_24HH_MM_SS : DateTimeFormatter.ofPattern(dateFormat);
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dft));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeUtils.FMT_YYYY_MM_DD));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeUtils.FMT_24HH_MM_SS));
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dft));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeUtils.FMT_YYYY_MM_DD));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeUtils.FMT_24HH_MM_SS));
            //BigDecimal和字符串转换
            javaTimeModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
            javaTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
            javaTimeModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            objectMapper.registerModule(new ParameterNamesModule());
            objectMapper.registerModule(new Jdk8Module());
            objectMapper.registerModule(javaTimeModule);
            //objectMapper.findAndRegisterModules();
            return objectMapper;
        }
    }

}
