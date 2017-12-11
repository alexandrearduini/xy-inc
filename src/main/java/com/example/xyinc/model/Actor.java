package com.example.xyinc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.xyinc.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="ACTOR")
public class Actor {

	@Id
	@GeneratedValue
	@Column(name="ID")
	@JsonView(value=Actor.class)
	private Long id;

	@Column(name="NAME")
	@JsonView(value=Actor.class)
	private String name;

	@Column(name="BIRTHDATE")
	@JsonView(value=Actor.class)
	private Date birthDate;

	@Column(name="NATIONALITY")
	@JsonView(value=Actor.class)
	private String nationality;

    @Enumerated(EnumType.STRING)
	@JsonView(value=Actor.class)
	private GenderEnum gender;

    @ManyToMany(mappedBy = "actors")
	@JsonView(value=Actor.class)
    private List<Movie> movies;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
