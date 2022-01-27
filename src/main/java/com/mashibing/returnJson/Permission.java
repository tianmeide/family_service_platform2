package com.mashibing.returnJson;

public class Permission {
    private String permissionId;

    public Permission() {
    }

    public String getPermissionId() {
        return permissionId;
    }

    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                '}';
    }
}
