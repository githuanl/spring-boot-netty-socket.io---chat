package com.neo.dao.impl;

import com.neo.dao.GroupDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import org.springframework.stereotype.Component;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class GroupDaoImpl<T extends BaseEntity> extends BaseDaoImpl<GroupEntity> implements GroupDao<GroupEntity> {


}
