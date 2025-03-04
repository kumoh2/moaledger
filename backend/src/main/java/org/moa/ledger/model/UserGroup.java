package org.moa.ledger.model;

public class UserGroup {
    private String userId;
    private String groupId;
    private String role; // "leader" or "member"

    // Getter & Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
