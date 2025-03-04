package org.moa.ledger.service;

import org.moa.ledger.mapper.LedgerMapper;
import org.moa.ledger.mapper.ElectronicsMapper;
import org.moa.ledger.model.Electronics;
import org.moa.ledger.model.Ledger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedgerService {
    private final LedgerMapper ledgerMapper;
    private final ElectronicsMapper electronicsMapper;

    public LedgerService(LedgerMapper ledgerMapper, ElectronicsMapper electronicsMapper) {
        this.ledgerMapper = ledgerMapper;
        this.electronicsMapper = electronicsMapper;
    }

    public List<Ledger> getAssetLedgerByGroup(String groupId) {
        return ledgerMapper.selectLedgerByGroupAndCategory(groupId, "asset");
    }

    public boolean upsertElectronics(Electronics electronics) {
        // 먼저 시도: update
        int updated = electronicsMapper.updateElectronics(electronics);
        if (updated == 0) {
            // update 실패하면 insert
            int inserted = electronicsMapper.insertElectronics(electronics);
            return inserted > 0;
        }
        return true;
    }
}
