package com.virtualpairprogrammers.tracker.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleBuilder 
{
	private String id;
	private String name;
	private LatLong latLong;
	private Date timestamp;
	private BigDecimal speed;


	
	public VehicleBuilder withTimestamp(Date date)
	{
		this.timestamp = date;
		return this;
	}
	
	public VehicleBuilder withName(String name)
	{
		this.name = name;
		return this;
	}
	
	public VehicleBuilder withLatLong(LatLong latLong)
	{
		this.latLong = latLong;
		return this;
	}
	
	public VehicleBuilder withSpeed(BigDecimal speed) {
		this.speed = speed;
		return this;
	}

	public VehicleBuilder withId(String id){
		this.id = id;
		return this;
	}
	
	public VehiclePosition build()
	{
		return new VehiclePosition(id, name, latLong, timestamp, speed);
	}

	public VehicleBuilder withLatLong(String lat,String lng) {
		return this.withLatLong(new LatLong(new BigDecimal(lat),new BigDecimal(lng)));
	}


	public VehicleBuilder withVehiclePostion(VehiclePosition data) {
		this.id = data.getId();
		this.name = data.getName();
		this.latLong = data.getLatLong();
		this.timestamp = data.getTimestamp();
		this.speed = data.getSpeed();

		return this;
	}

}
