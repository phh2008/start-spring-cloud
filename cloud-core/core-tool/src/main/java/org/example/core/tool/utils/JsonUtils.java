package org.example.core.tool.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.JSR310StringParsableDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.MonthDayDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.OffsetTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.DurationKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.MonthDayKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.PeriodKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.YearKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.YearMonthKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.ZoneIdKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.ZoneOffsetKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.ZonedDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.key.ZonedDateTimeKeySerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
        private static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

    public static class JacksonObjectMapper extends ObjectMapper {
        private String dateFormat = "yyyy-MM-dd HH:mm:ss";
        private String timeZone = TimeZone.getDefault().getID();

        public JacksonObjectMapper() {
            super();
            this.initObjectMapper();
        }

        public JacksonObjectMapper(String dateFormat, String timeZone) {
            super();
            this.dateFormat = dateFormat;
            this.timeZone = timeZone;
            this.initObjectMapper();
        }


        private void initObjectMapper() {
            //设置地点
            this.setLocale(Locale.getDefault());
            //去掉默认的时间戳格式
            this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //设置时区
            TimeZone tz = timeZone == null ? TimeZone.getTimeZone(ZoneId.systemDefault()) : TimeZone.getTimeZone(timeZone);
            this.setTimeZone(tz);
            //序列化时，日期的统一格式
            String sdf = dateFormat != null ? dateFormat : DateTimeUtils.YYYY_MM_DD_HH_MM_SS;
            this.setDateFormat(new SimpleDateFormat(sdf));
            //序列化处理
            this.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
            this.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
            this.findAndRegisterModules();
            //失败处理
            this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //单引号处理
            this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            //反序列化时，属性不存在的兼容处理
            this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            //java 8 日期格式化
            //直接使用JavaTimeModule添加addDeserializer与addSerializer会无效
            //JavaTimeModule javaTimeModule = new JavaTimeModule();
            SimpleModule javaTimeModule = getSimpleModule();
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
            this.registerModule(new ParameterNamesModule());
            this.registerModule(new Jdk8Module());
            this.registerModule(javaTimeModule);
        }

        public SimpleModule getSimpleModule() {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addDeserializer(Instant.class, InstantDeserializer.INSTANT);
            simpleModule.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
            simpleModule.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);
            simpleModule.addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
            simpleModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
            simpleModule.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
            simpleModule.addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE);
            simpleModule.addDeserializer(MonthDay.class, MonthDayDeserializer.INSTANCE);
            simpleModule.addDeserializer(OffsetTime.class, OffsetTimeDeserializer.INSTANCE);
            simpleModule.addDeserializer(Period.class, JSR310StringParsableDeserializer.PERIOD);
            simpleModule.addDeserializer(Year.class, YearDeserializer.INSTANCE);
            simpleModule.addDeserializer(YearMonth.class, YearMonthDeserializer.INSTANCE);
            simpleModule.addDeserializer(ZoneId.class, JSR310StringParsableDeserializer.ZONE_ID);
            simpleModule.addDeserializer(ZoneOffset.class, JSR310StringParsableDeserializer.ZONE_OFFSET);
            simpleModule.addSerializer(Duration.class, DurationSerializer.INSTANCE);
            simpleModule.addSerializer(Instant.class, InstantSerializer.INSTANCE);
            simpleModule.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
            simpleModule.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
            simpleModule.addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE);
            simpleModule.addSerializer(MonthDay.class, MonthDaySerializer.INSTANCE);
            simpleModule.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
            simpleModule.addSerializer(OffsetTime.class, OffsetTimeSerializer.INSTANCE);
            simpleModule.addSerializer(Period.class, new ToStringSerializer(Period.class));
            simpleModule.addSerializer(Year.class, YearSerializer.INSTANCE);
            simpleModule.addSerializer(YearMonth.class, YearMonthSerializer.INSTANCE);
            simpleModule.addSerializer(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE);
            simpleModule.addSerializer(ZoneId.class, new ToStringSerializer(ZoneId.class));
            simpleModule.addSerializer(ZoneOffset.class, new ToStringSerializer(ZoneOffset.class));
            simpleModule.addKeySerializer(ZonedDateTime.class, ZonedDateTimeKeySerializer.INSTANCE);
            simpleModule.addKeyDeserializer(Duration.class, DurationKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(LocalDateTime.class, LocalDateTimeKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(LocalTime.class, LocalTimeKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(MonthDay.class, MonthDayKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(OffsetDateTime.class, OffsetDateTimeKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(OffsetTime.class, OffsetTimeKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(Period.class, PeriodKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(Year.class, YearKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(YearMonth.class, YearMonthKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(ZonedDateTime.class, ZonedDateTimeKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(ZoneId.class, ZoneIdKeyDeserializer.INSTANCE);
            simpleModule.addKeyDeserializer(ZoneOffset.class, ZoneOffsetKeyDeserializer.INSTANCE);
            return simpleModule;
        }

    }


}
