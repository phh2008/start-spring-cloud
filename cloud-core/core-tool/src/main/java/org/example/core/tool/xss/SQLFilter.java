package org.example.core.tool.xss;

import org.example.core.tool.utils.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SQL 过滤器
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
public final class SQLFilter {

    private static final String BAD_STR = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
            "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";
    private static final Set<String> BAD_SETS = new HashSet<>();

    static {
        String[] arr = BAD_STR.split("\\|");
        BAD_SETS.addAll(Arrays.asList(arr));
    }

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public String cleanSqlKeyWords(String str) {
        final String oldStr = str;
        String[] values = replaceAndSplit(str);
        // 判断是否包含非法字符
        for (String value : values) {
            if (BAD_SETS.contains(value)) {
                str = StringUtils.replace(str, value, "INVALID");
                System.err.printf("当前参数(%s)包含非法的sql关键词(%s)，系统已自动过滤。\n", oldStr, value);
            }
        }
        return str;
    }

    private String[] replaceAndSplit(String str) {
        if (str == null || str.length() == 0) {
            return new String[0];
        }
        // 去掉'|"|;|\字符
        str = str.replace("'", "");
        str = str.replace("\"", "");
        str = str.replace(";", "");
        str = str.replace("\\", "");
        str = str.toLowerCase();
        return str.split(" ");
    }


}
