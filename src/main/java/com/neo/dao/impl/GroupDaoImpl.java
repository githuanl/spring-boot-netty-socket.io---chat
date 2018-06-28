package com.neo.dao.impl;

import com.neo.dao.GroupDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.GroupEntity;
import com.neo.entity.GroupUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class GroupDaoImpl<T extends BaseEntity> extends BaseDaoImpl<GroupEntity> implements GroupDao<GroupEntity> {


    /**
     * 根据群的名字 查询所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findGroupsByGroupName(String groupName) {
        List<GroupEntity> list;
        if (StringUtils.isEmpty(groupName)) {
            list = mongoTemplate.findAll(GroupEntity.class);
        } else {
            Query query = new Query(Criteria.where("groupname").regex(groupName));
            list = mongoTemplate.find(query, GroupEntity.class);
        }
        return list;
    }


    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findMyGroupsByUserId(String user_id) {
        // 我创建的群
        Query query = new Query(Criteria.where("user_id").is(user_id));
        List<GroupEntity> list = mongoTemplate.find(query, GroupEntity.class);

        //我加过的群
        List<GroupUser> users = mongoTemplate.find(query, GroupUser.class);

        for (GroupUser user : users) {
            query = new Query(Criteria.where("id").is(user.getGroup_id()));
            GroupEntity entity = mongoTemplate.findOne(query, GroupEntity.class);
            if (!entity.getUser_id().equals(user_id)) { //不是自己创建的群 (只是加入的群)
                list.add(entity);
            }
        }
        return list;
    }


    /**
     * 根据群id 查询群下面的所有的人
     *
     * @return
     */
    @Override
    public List<GroupUser> findUsersByGroupId(String group_id) {
        Query query = new Query(Criteria.where("group_id").is(group_id));
        List<GroupUser> list = mongoTemplate.find(query, GroupUser.class);
        return list;
    }


}
