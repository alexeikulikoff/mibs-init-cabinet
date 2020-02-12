package mibs.init.cabinet;

import java.io.Serializable;

import org.apache.commons.validator.routines.EmailValidator;

public class Exploration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String uniqueID ;
	private String folderName;
	public Exploration(String email, String uniqueID, String folderName) {
		super();
		this.uniqueID = uniqueID;
		this.folderName = folderName;
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
	public String getUniqueID() {
		return uniqueID;
	}
	public String getFolderName() {
		return folderName;
	}
	
}
