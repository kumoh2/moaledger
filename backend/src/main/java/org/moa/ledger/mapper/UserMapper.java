package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.User;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(String userId);
}
