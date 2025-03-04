package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.UserLedger;

@Mapper
public interface UserLedgerMapper {
    UserLedger findByUserId(String userId);
    int insertUserLedger(UserLedger userLedger);
    int updateUserLedger(UserLedger userLedger);
}
