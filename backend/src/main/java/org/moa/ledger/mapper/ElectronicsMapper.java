package org.moa.ledger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moa.ledger.model.Electronics;
import java.util.List;

@Mapper
public interface ElectronicsMapper {
    // upsert: MyBatis에는 upsert가 없으므로, 여기서는 update 후 insert 처리하는 예제
    int updateElectronics(Electronics electronics);
    int insertElectronics(Electronics electronics);
    Electronics selectByLedgerId(int ledgerId);
}
