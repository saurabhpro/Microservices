package com.saurabh.eventmanagement.entities.projections;

import com.saurabh.eventmanagement.entities.Venue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "virtualStreetAddress", types = {Venue.class})
public interface StreetAddressProjection {

	@Value("#{target.streetAddress} #{target.streetAddress2}")
	String getCompleteStreetAddress();
}
