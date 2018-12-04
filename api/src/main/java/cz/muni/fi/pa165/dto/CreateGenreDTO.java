package cz.muni.fi.pa165.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class CreateGenreDTO {

	@NotNull
	private String name;

	private String description;

	/**
	 * Gets the Genre's name - a short label like Opera or similar.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name for this instance.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the Genre's description, a longer text, for example a paragraph on a
	 * genre summary page.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of this Genre.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CreateGenreDTO)) {
			return false;
		}
		CreateGenreDTO other = (CreateGenreDTO) obj;
		return Objects.equals(name, other.getName()) && Objects.equals(description, other.getDescription());
	}
}