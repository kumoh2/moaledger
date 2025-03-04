package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.Group;
import java.util.List;

@Mapper
public interface GroupMapper {
    int insertGroup(Group group);
    Group findByGroupId(String groupId);
    List<Group> findGroupsByUserId(String userId);  // 조인을 통해 조회 (usergroup 테이블 사용)
}
