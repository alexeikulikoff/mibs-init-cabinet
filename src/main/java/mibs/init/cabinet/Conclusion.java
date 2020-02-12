package mibs.init.cabinet;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.validator.routines.EmailValidator;

public class Conclusion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String conclusionName;
	private byte[] conclusionContent;
	private String uniqueID;
	public Conclusion(String email, String conclusionName, byte[] conclusionContent, String uniqueID) {
		super();
		
		this.conclusionName = conclusionName;
		this.conclusionContent = conclusionContent;
		this.uniqueID = uniqueID;
		boolean isValid = EmailValidator.getInstance().isValid(email);
		if (isValid) {
			this.email = email.toLowerCase();
		}else {
			throw new IllegalArgumentException( "Email " + email + " is incorrect!"  );	
		}
	}
	public String getEmail() {
		return email;
	}
	public String getConclusionName() {
		return conclusionName;
	}
	public byte[] getConclusionContent() {
		return conclusionContent;
	}
	public String getUniqueID() {
		return uniqueID;
	}
	@Override
	public String toString() {
		return "Conclusion [email=" + email + ", conclusionName=" + conclusionName + ", conclusionContent="
				+ Arrays.toString(conclusionContent) + ", uniqueID=" + uniqueID + "]";
	}
	
}
