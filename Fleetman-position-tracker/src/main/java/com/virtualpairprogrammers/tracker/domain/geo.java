package com.virtualpairprogrammers.tracker.domain;

import com.couchbase.client.java.repository.annotation.Field;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class geo implements Comparable<geo>{

    @NotNull
    @Field
    private BigDecimal lat;

    @NotNull
    @Field
    private BigDecimal lon;

    geo(BigDecimal lat, BigDecimal lon){
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public int compareTo(geo o)
    {
        return o.compareTo(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lon == null) ? 0 : lon.hashCode());
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
        geo other = (geo) obj;
        if (lat == null) {
            if (other.lat != null)
                return false;
        } else if (!lat.equals(other.lat))
            return false;
        if (lon == null) {
            if (other.lon != null)
                return false;
        } else if (!lon.equals(other.lon))
            return false;
        return true;
    }

    public static geo buildLatLong(BigDecimal lat,BigDecimal lon){
       return new geo(lat,lon);
    }

    public BigDecimal getLat() {
        return this.lat;
    }

    public BigDecimal getlon() {
        return this.lon;
    }
}
