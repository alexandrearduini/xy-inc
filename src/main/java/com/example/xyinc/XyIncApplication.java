package com.example.xyinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Movie;
import com.example.xyinc.serializer.ActorSerializer;
import com.example.xyinc.serializer.MovieSerializer;

@SpringBootApplication
public class XyIncApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyIncApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		return new Jackson2ObjectMapperBuilder()
			.serializerByType(Movie.class, new MovieSerializer())
			.serializerByType(Actor.class, new ActorSerializer());
	}
}
