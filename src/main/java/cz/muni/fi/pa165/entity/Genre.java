package cz.muni.fi.pa165.entity;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotNull
	private String name;
	
	@Column(nullable=false)
	@NotNull
	private String description;
	
	@OneToMany(mappedBy="show", fetch=FetchType.LAZY)
		private Set<Show> shows;

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
	
	/**
	 * Returns the set of shows of this genre. Note that due to the lazy nature of the reverse association, a query may occur. To prevent this use fetch join or similar.
	 * @return an immutable set of shows
	 */
	public Set<Show> getShows() {
		return Collections.unmodifiableSet(this.shows);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(name);
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
		return Objects.equals(name,  other.getName());
}
}