package com.saurabh.eventmanagement.entities;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Participant extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	private Boolean checkedIn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			referencedColumnName = "ID",
			nullable = false,
			updatable = false
	)
	private Event event;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Long getResourceId() {
		return getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant participant = (Participant) o;

		return Objects.equals(getId(), participant.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

}
