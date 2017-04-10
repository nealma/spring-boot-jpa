package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by neal on 26/03/2017.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Actor {
    @GraphId
    Long id;
    private String name;
    private int burn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurn() {
        return burn;
    }

    public void setBurn(int burn) {
        this.burn = burn;
    }
}
