package org.example.core.common.mp.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.jwt.IJwtInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(T entity) {
        //TODO 当前用户，设置用户ID
        if (entity instanceof BaseColumnEntity) {
            LocalDateTime now = LocalDateTime.now();
            IJwtInfo jwtInfo = UserContextHandler.getJwtInfo();
            BaseColumnEntity entity1 = (BaseColumnEntity) entity;
            if (jwtInfo != null) {
                Long userId = Long.parseLong(jwtInfo.getId());
                entity1.setCreateBy(userId);
                entity1.setUpdateBy(userId);
            }
            entity1.setCreatedAt(now);
            entity1.setUpdatedAt(now);
            entity1.setDelFlag(0);
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(T entity) {
        //TODO 当前用户，设置更新用户ID
        if (entity instanceof BaseColumnEntity) {
            IJwtInfo jwtInfo = UserContextHandler.getJwtInfo();
            BaseColumnEntity entity1 = (BaseColumnEntity) entity;
            if (jwtInfo != null) {
                entity1.setUpdateBy(Long.parseLong(jwtInfo.getId()));
            }
            entity1.setUpdatedAt(LocalDateTime.now());
        }
        return super.updateById(entity);
    }

    @Override
    public boolean deleteLogic(List<Long> ids) {
        return super.removeByIds(ids);
    }


}
