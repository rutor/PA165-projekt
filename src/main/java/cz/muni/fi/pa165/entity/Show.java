package cz.muni.fi.pa165.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Show {
@Id
private Long id;

@Column(nullable=false, unique=true)
private String name;

@Column(nullable=false)
private String description;

@Column(nullable=false)
private int duration; // in minutes, 131 years should be enough for every show you could thing of

/**
 * Gets the id of this show. 
 * @return the id
 */
public Long getId() {
	return id;
}

/**
 * Sets the show's id.
 * @param id the id to set
 */
public void setId(Long id) {
	this.id = id;
}

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
	if (!(obj instanceof Show)) {
		return false;
	}
	Show other = (Show) obj;
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
