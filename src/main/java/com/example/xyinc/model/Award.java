package com.example.xyinc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="AWARD")
public class Award {

	@Id
	@GeneratedValue
	@Column(name="ID")
	@JsonView(value=Award.class)
	private Long id;

	@Column(name="NAME")
	@JsonView(value=Award.class)
	private String name;

	@Column(name="AWARDDATE")
	@JsonView(value=Award.class)
	private Date awardDate;

    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
	@JsonView(value=Award.class)
	private Movie movie;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
