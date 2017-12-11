package com.example.xyinc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="MOVIE")
public class Movie {

	@Id
	@GeneratedValue
	@Column(name="ID")
	@JsonView(value=Movie.class)
	private Long id;

	@Column(name="NAME")
	@JsonView(value=Movie.class)
	private String name;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="RELEASINGDATE")
	@JsonView(value=Movie.class)
	private Date releasingDate;

	@Column(name="BUDGET")
	@JsonView(value=Movie.class)
	private Double budget;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	@JsonView(value=Movie.class)
	private Category category;

	@ManyToMany
    @JoinTable(
        name = "MOVIE_ACTOR", 
        joinColumns = { @JoinColumn(name = "MOVIE_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "ACTOR_ID") }
    )
	@JsonView(value=Movie.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Actor> actors;

    @OneToMany(mappedBy="movie")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Award> awards;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getReleasingDate() {
		return releasingDate;
	}

	public Double getBudget() {
		return budget;
	}

	public Category getCategory() {
		return category;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReleasingDate(Date releasingDate) {
		this.releasingDate = releasingDate;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
}
