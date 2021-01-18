package com.virtualpairprogrammers.tracker.domain;

import com.couchbase.client.java.repository.annotation.Field;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LatLong implements Comparable<LatLong>{

    @NotNull
    @Field
    private BigDecimal lat;

    @NotNull
    @Field
    private BigDecimal lng;

    LatLong(BigDecimal lat, BigDecimal lng){
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public int compareTo(LatLong o)
    {
        return o.compareTo(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
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
        LatLong other = (LatLong) obj;
        if (lat == null) {
            if (other.lat != null)
                return false;
        } else if (!lat.equals(other.lat))
            return false;
        if (lng == null) {
            if (other.lng != null)
                return false;
        } else if (!lng.equals(other.lng))
            return false;
        return true;
    }

    public static LatLong buildLatLong(BigDecimal lat,BigDecimal lon){
       return new LatLong(lat,lon);
    }

    public BigDecimal getLat() {
        return this.lat;
    }

    public BigDecimal getLng() {
        return this.lng;
    }
}
