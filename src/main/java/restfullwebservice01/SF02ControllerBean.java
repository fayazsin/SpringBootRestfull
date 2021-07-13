package restfullwebservice01;




public class SF02ControllerBean {
	private String msg;

	SF02ControllerBean(String msg){
		this.msg=msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message=" + msg;
	}
}
