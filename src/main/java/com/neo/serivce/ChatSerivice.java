package com.neo.serivce;

import com.neo.entity.BaseEntity;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;

/**
 * Created by liudong on 2018/6/8.
 */

public interface ChatSerivice<T extends BaseEntity> extends BaseSerivice<MessageEntity> {

    void saveMessageData(T entity);

    void sendApnData();
}
