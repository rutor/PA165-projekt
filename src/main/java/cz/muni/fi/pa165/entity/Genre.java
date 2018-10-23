package cz.muni.fi.pa165.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genre {
	@Id
	Long id;
	@Column(nullable=false, unique=true)
	@NotNull
	String name;
	@Column(nullable=false)
	@NotNull
	String description;
	/**
	 * @return the id of this genre
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Sets the Genre's id. Usually probably not needed, but...
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Gets the Genre's name - a short label like Opera or similar.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name for this instance.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the Genre's description, a longer text, for example a paragraph on a genre summary page.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description of this Genre.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
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
		if (!(obj instanceof Genre)) {
			return false;
		}
		Genre other = (Genre) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		return true;
	}

}
