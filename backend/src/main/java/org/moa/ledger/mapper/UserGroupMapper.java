package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.UserGroup;
import java.util.List;

@Mapper
public interface UserGroupMapper {
    List<UserGroup> findByUserId(String userId);
    UserGroup findByUserIdAndGroupId(String userId, String groupId);
    int insertUserGroup(UserGroup userGroup);
    int deleteUserGroup(String userId, String groupId);
    int updateUserGroupRole(UserGroup userGroup);
}
