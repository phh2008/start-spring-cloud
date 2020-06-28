package org.example.core.common.mp.base;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务接口基类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 罗辑删除
     *
     * @param ids
     * @return
     */
    boolean deleteLogic(List<Long> ids);

}
