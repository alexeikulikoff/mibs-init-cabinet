package mibs.init.cabinet;

public class RabbitmqCommandMessage<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String command;
	private String routingKey;
	private T content;
	public RabbitmqCommandMessage(String command, String routingKey, T content) {
		super();
		this.command = command;
		this.content = content;
		this.routingKey = routingKey;
	}
	public String getCommand() {
		return command;
	}
	public T getContent() {
		return content;
	}
	@Override
	public String toString() {
		return "RabbitmqCommandMessage [command=" + command + ", routingKey=" + routingKey + ", content=" + content
				+ "]";
	}


	
}
