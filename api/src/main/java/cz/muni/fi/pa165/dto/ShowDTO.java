package cz.muni.fi.pa165.dto;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ShowDTO {

	private Long id;

	private String name;

	private String description;

	private int duration;

	private GenreDTO genre;

	/**
	 * Gets the id of this show.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the show's id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name of this show.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this show.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of this show.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the duration of this show, in minutes. The reasoning for the implicit
	 * unit assumption is that you usually don't need finer duration in this context
	 * and a few billion minutes is enough.
	 * 
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 * 
	 * @param duration the duration to set, in minutes
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns the genre of this show.
	 * 
	 * @return the genre
	 */
	public GenreDTO getGenre() {
		return this.genre;
	}

	/**
	 * Sets this show's genre, also updating the genre's show set accordingly.
	 * 
	 * @param genre the genre to set
	 */
	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, duration, genre);
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
		if (!(obj instanceof ShowDTO)) {
			return false;
		}
		ShowDTO other = (ShowDTO) obj;
		return Objects.equals(id, other.getId()) && Objects.equals(name, other.getName())
				&& Objects.equals(description, other.getDescription()) && Objects.equals(duration, other.getDuration())
				&& Objects.equals(genre, other.getGenre());
	}

	@Override
	public String toString() {
		return "ShowDTO{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", duration=" + duration +
				", genre=" + genre +
				'}';
	}
}