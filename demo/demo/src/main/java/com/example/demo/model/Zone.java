package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Zone {

    @Id
    private String id;
    @Field
    private String zoneName;
    @Field
    private int nodeNumber;
    @Field
    private int linkNumber;
    @Field
    private String geometry;

    public Zone() {
    }

    public Zone(String zoneName, int nodeNumber, int linkNumber, String geometry) {
        this.zoneName = zoneName;
        this.nodeNumber = nodeNumber;
        this.linkNumber = linkNumber;
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public int getLinkNumber() {
        return linkNumber;
    }

    public void setLinkNumber(int linkNumber) {
        this.linkNumber = linkNumber;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return String.format("Zone[id='%s', zoneName='%s', nodeNumber='%d', linkNumber= '%d', geometry = '%s']"
                ,id,zoneName,nodeNumber,linkNumber, geometry);
    }
}
