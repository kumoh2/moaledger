package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.Ledger;
import java.util.List;

@Mapper
public interface LedgerMapper {
    List<Ledger> selectLedgerByGroupAndCategory(String groupId, String category);
}
