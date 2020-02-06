package mibs.init.cabinet;

public class Person implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String patronymic;
	private String dateOfBirth;
	private String emeil;
	
	public Person(String firstName, String lastName, String patronymic, String dateOfBirth, String emeil) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.dateOfBirth = dateOfBirth;
		this.emeil = emeil;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getEmeil() {
		return emeil;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", patronymic=" + patronymic + ", dateOfBirth="
				+ dateOfBirth + ", emeil=" + emeil + "]";
	}
	
	
	

}
