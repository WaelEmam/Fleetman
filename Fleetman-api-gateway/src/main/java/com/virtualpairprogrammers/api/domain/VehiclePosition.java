package com.virtualpairprogrammers.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehiclePosition implements Comparable<VehiclePosition>
{
	private String id;
	private String name;
	private geo latLong;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="UTC")
	private Date timestamp;
	private BigDecimal speed;
	
	VehiclePosition() {}
	
	@Override
	public int compareTo(VehiclePosition o) 
	{
		return o.timestamp.compareTo(this.timestamp);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehiclePosition other = (VehiclePosition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	public String getId() { return id; }

	public String getName() {
		return this.name;
	}

	public geo getLatLong() {
		return this.latLong;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public BigDecimal getSpeed() {
		return this.speed;
	}

}
