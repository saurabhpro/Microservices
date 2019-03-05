package com.saurabh.eventmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Organizer extends AbstractEntity {
	private String name;

	@OneToMany(mappedBy = "organizer")
	private Set<Event> setOfEvents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Event> getSetOfEvents() {
		return setOfEvents;
	}

	public void setSetOfEvents(Set<Event> setOfEvents) {
		this.setOfEvents = setOfEvents;
	}

	public Long getResourceId() {
		return getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Organizer organizer = (Organizer) o;

		return Objects.equals(getId(), organizer.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
