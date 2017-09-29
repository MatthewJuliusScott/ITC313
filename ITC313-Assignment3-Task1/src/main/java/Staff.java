

public class Staff {
	
	private int id = 0;
	
	private String lastName;
	
	private String firstName;
	
	private String middleInitial;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String telephoneNumber;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Staff)) {
			return false;
		}
		Staff other = (Staff) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (middleInitial == null) {
			if (other.middleInitial != null) {
				return false;
			}
		} else if (!middleInitial.equals(other.middleInitial)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (telephoneNumber == null) {
			if (other.telephoneNumber != null) {
				return false;
			}
		} else if (!telephoneNumber.equals(other.telephoneNumber)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return the middleInitial
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
		        + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result
		        + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
		        + ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
		        + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
		return result;
	}
	
	/**
	 * @param address the address to set
	 * @throws Exception 
	 */
	public void setAddress(String address) throws Exception {
		if (address.length() > 100) {
			throw new Exception("Address is too long. It must be 100 characters or less.");
		} else if (JavaDatabaseProgram.isBlank(address)) {
			throw new Exception("Address must not be blank.");
		}
		this.address = address;
	}
	
	/**
	 * @param city the city to set
	 * @throws Exception 
	 */
	public void setCity(String city) throws Exception {
		if (city.length() > 30) {
			throw new Exception("City is too long. It must be 100 characters or less.");
		} else if (JavaDatabaseProgram.isBlank(city)) {
			throw new Exception("City must not be blank.");
		}
		this.city = city;
	}
	/**
	 * @param firstName the firstName to set
	 * @throws Exception 
	 */
	public void setFirstName(String firstName) throws Exception {
		if (firstName.length() > 30) {
			throw new Exception("First name is too long. It must be 30 characters or less.");
		} else if (JavaDatabaseProgram.isBlank(firstName)) {
			throw new Exception("First name must not be blank.");
		}
		this.firstName = firstName;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param lastName the lastName to set
	 * @throws Exception 
	 */
	public void setLastName(String lastName) throws Exception {
		if (lastName.length() > 30) {
			throw new Exception("Last name is too long. It must be 30 characters or less.");
		} else if (JavaDatabaseProgram.isBlank(lastName)) {
			throw new Exception("Last name must not be blank.");
		}
		this.lastName = lastName;
	}
	/**
	 * @param middleInitial the middleInitial to set
	 * @throws Exception 
	 */
	public void setMiddleInitial(String middleInitial) throws Exception {
		if (middleInitial.length() > 2) {
			throw new Exception("Middle initial is too long. It must be 2 characters or less.");
		} else if (JavaDatabaseProgram.isBlank(middleInitial)) {
			throw new Exception("Middle initial must not be blank.");
		}
		this.middleInitial = middleInitial;
	}
	/**
	 * @param state the state to set
	 * @throws Exception 
	 */
	public void setState(String state) throws Exception {
		if (!state.matches("ACT|NSW|NT|QLD|SA|TAS|VIC|WA")) {
			throw new Exception("Ensure the state is valid. (ACT, NSW, NT, QLD, SA, TAS, VIC, WA)");
		} else if (JavaDatabaseProgram.isBlank(state)) {
			throw new Exception("State must not be blank.");
		}
		this.state = state;
	}
	/**
	 * @param telephoneNumber the telephoneNumber to set
	 * @throws Exception 
	 */
	public void setTelephoneNumber(String telephoneNumber) throws Exception {
		if (telephoneNumber.length() > 10) {
			throw new Exception("Telephone number is too long. It must be 10 digits or less.");
		} else if (JavaDatabaseProgram.isBlank(telephoneNumber)) {
			throw new Exception("Telephone number must not be blank.");
		} else if (!telephoneNumber.matches("\\d+")) {
			throw new Exception("Telephone number must contain digits only.");
		}
		this.telephoneNumber = telephoneNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Staff [id=" + id + ", lastName=" + lastName + ", firstName="
		        + firstName + ", middleInitial=" + middleInitial + ", address="
		        + address + ", city=" + city + ", state=" + state
		        + ", telephoneNumber=" + telephoneNumber + "]";
	}

}
