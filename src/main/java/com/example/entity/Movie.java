package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neal on 26/03/2017.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Movie {
    @GraphId
    Long id;
    private String title;
    private String year;
    private String tagline;
    @Relationship(type = "ACTS_IN", direction = Relationship.INCOMING)
    private List<RoleGraph> roles = new ArrayList<>();

    public RoleGraph addRole(Actor actor, String name){
        RoleGraph roleGraph = new RoleGraph();
        roleGraph.setActor(actor);
        roleGraph.setRole(name);
        this.roles.add(roleGraph);
        return roleGraph;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<RoleGraph> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleGraph> roles) {
        this.roles = roles;
    }
}
