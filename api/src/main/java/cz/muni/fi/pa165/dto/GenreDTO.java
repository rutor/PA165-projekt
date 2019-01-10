package cz.muni.fi.pa165.dto;

import java.util.Objects;

public class GenreDTO {

	private Long id;

	private String name;

	private String description;

	/**
	 * @return the id of this genre
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the Genre's id. Usually probably not needed, but...
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
		return Objects.hash(id, name, description);
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
		if (!(obj instanceof GenreDTO)) {
			return false;
		}
		GenreDTO other = (GenreDTO) obj;
		return Objects.equals(id, other.getId()) && Objects.equals(name, other.getName())
				&& Objects.equals(description, other.getDescription());
	}

	@Override
	public String toString() {
		return this.getName();
	}
}