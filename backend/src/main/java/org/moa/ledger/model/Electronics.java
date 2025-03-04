package org.moa.ledger.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Electronics {
    private int electronicsId;
    private String groupId;
    private int ledgerId;
    private String content;
    private Date warrantyExpiry;
    private Date freeRepairExpiry;
    private Date lastCleanedDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDeleted;

    // Getter & Setter
    public int getElectronicsId() { return electronicsId; }
    public void setElectronicsId(int electronicsId) { this.electronicsId = electronicsId; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public int getLedgerId() { return ledgerId; }
    public void setLedgerId(int ledgerId) { this.ledgerId = ledgerId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getWarrantyExpiry() { return warrantyExpiry; }
    public void setWarrantyExpiry(Date warrantyExpiry) { this.warrantyExpiry = warrantyExpiry; }

    public Date getFreeRepairExpiry() { return freeRepairExpiry; }
    public void setFreeRepairExpiry(Date freeRepairExpiry) { this.freeRepairExpiry = freeRepairExpiry; }

    public Date getLastCleanedDate() { return lastCleanedDate; }
    public void setLastCleanedDate(Date lastCleanedDate) { this.lastCleanedDate = lastCleanedDate; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
