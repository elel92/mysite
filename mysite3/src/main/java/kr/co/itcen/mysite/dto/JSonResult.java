package kr.co.itcen.mysite.dto;

public class JSonResult {
	private String result; // success or fail
	private Object data; // if success -> set
	private String message; // if fail - > set
	
	public static JSonResult success(Object data) {
		return new JSonResult(data);
	}
	
	public static JSonResult fail(String message) {
		return new JSonResult(message);
	}
	
	private JSonResult() {
		
	}
	
	private JSonResult(Object data) {
		this.result = "success";
		this.data = data;
	}

	private JSonResult(String message) {
		this.result = "fail";
		this.message = message;
	}
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
}
