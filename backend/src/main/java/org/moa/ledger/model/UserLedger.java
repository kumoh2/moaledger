package org.moa.ledger.model;

public class UserLedger {
    private String userId;
    private String mainLedgerGroupId;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMainLedgerGroupId() { return mainLedgerGroupId; }
    public void setMainLedgerGroupId(String mainLedgerGroupId) { this.mainLedgerGroupId = mainLedgerGroupId; }
}
