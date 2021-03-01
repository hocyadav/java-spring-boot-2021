package june24;
//create model -> make variable as volatile
public class Message {
	volatile String msg;//volatile guarantees latest value

	public Message() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
