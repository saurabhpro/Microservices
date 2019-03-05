package com.saurabh.eventmanagement.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@JsonPropertyOrder({"resourceId", "started"})
@Entity
public class Event extends AbstractEntity {

	private String name;
	private String description;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private ZoneId zoneId;

	@JsonProperty("started")
	private Boolean isStarted;

	@ManyToOne(
			fetch = FetchType.EAGER // fetch organizer only when needed not when event is loaded
	)
	@JoinColumn(nullable = false)
	private Organizer organizer;

	@OneToMany(
			mappedBy = "event",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true // event was not deleted, but associated participant was removed - delete the participants
	)
	private Set<Participant> participants;

	@ManyToOne(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL // whenever Event is deleted - all his venue is deleted
	)
	@RestResource(exported = false)
	//create venue as part of event body not as link, without it we expect link to be passed
	private Venue venue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}

	public ZoneId getZoneId() {
		return zoneId;
	}

	public void setZoneId(ZoneId zoneId) {
		this.zoneId = zoneId;
	}

	public Boolean getStarted() {
		return isStarted;
	}

	public void setStarted(Boolean started) {
		isStarted = started;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Long getResourceId() {
		return getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;

		return Objects.equals(getId(), event.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
