package com.hackathonproject.Search;

public class QueryOption {
    protected final String urlBase = "http://spatial.virtualearth.net/REST/v1/data/f22876ec257b474b82fe2ffcb8393150/NavteqNA/NavteqPOIs";
    protected final String appKey = "AhtUFC-zH4ygRkYunzUpBYky6-W8pPQ88NDZGPqk9WNdGweRvfYk6ks_aQ8GdHw7";

    protected String entityTypeID;
    protected String entityID;
    // nearby option1: atitude, longitude, distance
    protected String atitude;
    protected String longitude;
    // maximum distance to search from that location, by kilometers. Mile is not
    // supported by Bing API
    protected String distance;
    // nearby option2: address, distance
    protected String address;
    protected String top;

    protected String orderby;// order by the fields
    protected String selected = "*,__distance";

    // public void setUrlBase(String url){
    // this.urlBase = url;
    // }
    public String getUrlBase() {
        return this.urlBase;
    }

    public void setEntityTypeId(int entityTypeId) {
        this.entityTypeID = String.valueOf(entityTypeId);
    }

    public String getEntityTypeId() {
        return this.entityTypeID;
    }

    public void setEntityId(int id) {
        this.entityID = String.valueOf(id);
    }

    public String getEntityId() {
        return this.entityID;
    }

    public void setAtitude(Double atitude) {
        this.atitude = String.valueOf(atitude);
    }

    public String getAtitude() {
        return this.atitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = String.valueOf(longitude);
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setDistance(Double distance) {
        this.distance = String.valueOf(distance);
    }

    public String getDistance() {
        return this.distance;
    }

    public void setAddress(String addrs) {
        this.address = addrs;
    }

    public String getAddress() {
        return this.address;
    }

    public void setOrderBy(String order) {
        this.orderby = order;
    }

    public String getOrder() {
        return this.orderby;
    }

    public void setTop(int top) {
        this.top = String.valueOf(top);
    }

    public String getTop() {
        return this.top;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.urlBase);
        if ((this.atitude != null && !this.atitude.isEmpty()) || (this.longitude != null && !this.longitude.isEmpty()) || (this.distance != null && !this.distance.isEmpty())
                || (this.address != null && !this.address.isEmpty())) {
            sb.append("?spatialFilter=nearby(");
            if (this.atitude != null && !this.atitude.isEmpty()) {
                sb.append(this.atitude);
                sb.append(",");
                sb.append(this.longitude);
            } else if (this.address != null && !this.address.isEmpty()) {
                sb.append(this.address);
            }
            if (this.distance != null && !this.distance.isEmpty()) {
                sb.append(",");
                sb.append(this.distance);
            }
            sb.append(")");
        }
        if (this.entityTypeID != null && !this.entityTypeID.isEmpty()) {
            sb.append("&$filter=EntityTypeID eq '");
            sb.append(this.entityTypeID);
            sb.append("'");
        }
        if (this.entityID != null && !this.entityID.isEmpty()) {
            if (sb.indexOf("filter") == -1) {
                sb.append("&$filter=EntityID eq '");
                sb.append(this.entityID);
                sb.append("'");
            } else {
                sb.append("and EntityID eq '");
                sb.append(this.entityID);
                sb.append("'");
            }
        }
        sb.append("&$format=json");
        sb.append("&$select=");
        sb.append(this.selected);
        if (!this.top.isEmpty()) {
            sb.append("&$top=");
            sb.append(this.top);

        }
        sb.append("&key=");
        sb.append(this.appKey);
        return sb.toString();
    }
}