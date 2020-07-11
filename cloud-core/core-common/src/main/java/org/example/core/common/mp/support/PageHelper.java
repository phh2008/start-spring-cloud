package org.example.core.common.mp.support;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.core.common.query.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * mybatis plus page 构建工具
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/11
 */
@Slf4j
public class PageHelper {

    public static final String KEY_PAGE_NO = "pageNo";
    public static final String KEY_PAGE_SIZE = "pageSize";

    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 构建 page 对象
     *
     * @param pageNo   当前页
     * @param pageSize 每页数
     * @param <T>      泛型 T
     * @return Page<T>
     */
    public static <T> Page<T> build(Long pageNo, Long pageSize) {
        return new Page<>(pageNo != null ? pageNo : DEFAULT_PAGE_NO,
                pageSize != null ? pageSize : DEFAULT_PAGE_SIZE);
    }

    /**
     * 构建 page 对象
     *
     * @param no   当前页
     * @param size 每页数
     * @return Page<T>
     * @author phh
     * @date 2020/7/11
     */
    public static <T> Page<T> build(String no, String size) {
        try {
            long pageNo = no != null && no.length() > 0 ? Long.parseLong(no) : DEFAULT_PAGE_NO;
            long pageSize = size != null && size.length() > 0 ? Long.parseLong(size) : DEFAULT_PAGE_SIZE;
            return new Page<>(pageNo, pageSize);
        } catch (Exception e) {
            log.error("build page error", e);
            return new Page<>(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
        }
    }

    /**
     * 构建 page 对象
     * 从 request 中获取 pageNo,pageSize 参数构建
     *
     * @param request http request
     * @return Page<T>
     * @author phh
     * @date 2020/7/11
     */
    public static <T> Page<T> build(HttpServletRequest request) {
        return PageHelper.build(request.getParameter(KEY_PAGE_NO), request.getParameter(KEY_PAGE_SIZE));
    }

    /**
     * 构建 page 对象
     * 从 map 中获取 pageNo,pageSize
     *
     * @param param 请求参数
     * @param <T>
     * @return Page<T>
     */
    public static <T> Page<T> build(Map<String, Object> param) {
        try {
            Object no = param.get(KEY_PAGE_NO);
            Object size = param.get(KEY_PAGE_SIZE);
            long pageNo = no != null ? Long.parseLong(no.toString()) : DEFAULT_PAGE_NO;
            long pageSize = size != null ? Long.parseLong(size.toString()) : DEFAULT_PAGE_SIZE;
            return new Page<>(pageNo, pageSize);
        } catch (Exception e) {
            log.error("build page error", e);
            return new Page<>(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
        }
    }

    /**
     * 构建 page 对象
     *
     * @param query 查询对象
     * @return Page<T>
     * @author phh
     * @date 2020/7/11
     */
    public static <T> Page<T> build(Query query) {
        return PageHelper.build(query.getPageNo(), query.getPageSize());
    }

}
