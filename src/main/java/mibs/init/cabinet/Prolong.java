package mibs.init.cabinet;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.validator.routines.EmailValidator;

public class Prolong implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private LocalDate prolongDate;
	public Prolong(String email, LocalDate prolongDate) {
		super();
		
		this.prolongDate = prolongDate;
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
	public LocalDate getProlongDate() {
		return prolongDate;
	} 
	
}
