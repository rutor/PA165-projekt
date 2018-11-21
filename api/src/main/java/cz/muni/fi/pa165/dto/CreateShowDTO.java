package cz.muni.fi.pa165.dto;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class CreateShowDTO {

@NotNull
private String name;

@NotNull
private String description;

@NotNull
private int duration;

@NotNull
private Long genreId;

/**
 * Gets the name of this show.
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * Sets the name of this show.
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * Gets the description of this show.
 * @return the description
 */
public String getDescription() {
	return description;
}

/**
 * Sets the description.
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}

/**
 * Gets the duration of this show, in minutes.
 * The reasoning for the implicit unit assumption is that you usually don't need finer duration in this context and a few billion minutes is enough.
 * @return the duration
 */
public int getDuration() {
	return duration;
}

/**
 * Sets the duration.
 * @param duration the duration to set, in minutes
 */
public void setDuration(int duration) {
	this.duration = duration;
}

/**
 * Returns the genre id of this show.
 * @return the genre id
 */
public Long getGenreId() {
	return this.genreId;
}

/**
 * Sets this show's genre id.
 * @param genreId the genre id to set
 */
public void setGenreId(Long genreId) {
	this.genreId = genreId;
}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	return Objects.hash(name);
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (!(obj instanceof CreateShowDTO)) {
		return false;
	}
	CreateShowDTO other = (CreateShowDTO) obj;
	return Objects.equals(name,  other.getName());
}
}