package org.example.core.common.mp.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Base服务实现类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/23
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public boolean save(T entity) {
        //TODO 当前用户，设置用户ID

        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        entity.setDelFlag(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        //TODO 当前用户，设置更新用户ID

        entity.setUpdatedAt(LocalDateTime.now());
        return super.updateById(entity);
    }

    @Override
    public boolean deleteLogic(List<Long> ids) {
        return super.removeByIds(ids);
    }


}
