package mibs.init.cabinet;

import java.io.Serializable;
import java.time.LocalDate;

public class Prolong implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private LocalDate prolongDate;
	public Prolong(String email, LocalDate prolongDate) {
		super();
		this.email = email;
		this.prolongDate = prolongDate;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getProlongDate() {
		return prolongDate;
	} 
	
}
