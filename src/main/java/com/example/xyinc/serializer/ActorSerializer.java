package com.example.xyinc.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Movie;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
@Component
public class ActorSerializer extends StdSerializer<Actor>  {

	public ActorSerializer() {
		this(null);
	}
	
	public ActorSerializer(Class<Actor> t) {
		super(t);
	}

	@Override
	public void serialize(Actor value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        jgen.writeStartObject();
        	if (value.getId() != null) jgen.writeNumberField("id", value.getId());
        	else jgen.writeNullField("id");
        	if (value.getName() != null) jgen.writeStringField("name", value.getName());
        	else jgen.writeNullField("name");
	        if (value.getBirthDate() != null) jgen.writeStringField("birthDate", dateFormat.format(value.getBirthDate()));
	        else jgen.writeNullField("birthDate");
        	if (value.getNationality() != null) jgen.writeStringField("nationality", value.getNationality());
        	else jgen.writeNullField("nationality");
        	if (value.getGender() != null) jgen.writeStringField("gender", value.getGender().getDescription());
        	else jgen.writeNullField("gender");
	        // movies
        	jgen.writeArrayFieldStart("movies");
        	if (value.getMovies() != null) {
		        for (Movie movie : value.getMovies()) {
		        	jgen.writeStartObject();
			        	if (movie.getId() != null) jgen.writeNumberField("id", movie.getId());
			            else jgen.writeNullField("id");
			        	if (movie.getName() != null) jgen.writeStringField("name", movie.getName());
			        	else jgen.writeNullField("name");
			        	if (movie.getDescription() != null) jgen.writeStringField("description", movie.getDescription());
			        	else jgen.writeNullField("description");
				        if (movie.getReleasingDate() != null) jgen.writeStringField("releasingDate", dateFormat.format(movie.getReleasingDate()));
				        else jgen.writeNullField("releasingDate");
			        	if (movie.getBudget() != null) jgen.writeNumberField("budget", movie.getBudget());
			        	else jgen.writeNullField("budget");
			        	// category
			    		jgen.writeObjectFieldStart("category");
			    		if (movie.getCategory() != null) {
					        if (movie.getCategory().getId() != null) jgen.writeNumberField("id", movie.getCategory().getId());
					        else jgen.writeNullField("id");
					        if (movie.getCategory().getName() != null) jgen.writeStringField("name", movie.getCategory().getName());
					        else jgen.writeNullField("releasingDate");
					        if (movie.getCategory().getDescription() != null) jgen.writeStringField("description", movie.getCategory().getDescription());
					        else jgen.writeNullField("description");
			    		}
				        jgen.writeEndObject();
		            jgen.writeEndObject();
				}
        	}
	        jgen.writeEndArray();
        jgen.writeEndObject();
	}
}
