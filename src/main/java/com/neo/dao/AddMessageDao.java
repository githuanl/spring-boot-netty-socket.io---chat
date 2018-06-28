package com.neo.dao;

import com.neo.entity.AddMessage;
import com.neo.entity.BaseEntity;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
public interface AddMessageDao<T extends BaseEntity> extends BaseDao<AddMessage> {

    /**
     * @description 查询消息盒子信息
     */
    List<AddMessage> findAddInfo(String userId);

}
