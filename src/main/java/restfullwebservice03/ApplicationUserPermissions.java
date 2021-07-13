package restfullwebservice03;

public enum ApplicationUserPermissions {
	STUDENT_READ("student:read"),
	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write");

	private final String permission;

	ApplicationUserPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
