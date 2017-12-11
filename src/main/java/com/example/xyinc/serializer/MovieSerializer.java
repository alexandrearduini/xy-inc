package com.example.xyinc.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Award;
import com.example.xyinc.model.Movie;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
@Component
public class MovieSerializer extends StdSerializer<Movie>  {

	public MovieSerializer() {
		this(null);
	}
	
	public MovieSerializer(Class<Movie> t) {
		super(t);
	}

	@Override
	public void serialize(Movie value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        jgen.writeStartObject();
        	if (value.getId() != null) jgen.writeNumberField("id", value.getId());
        	else jgen.writeNullField("id");
        	if (value.getName() != null) jgen.writeStringField("name", value.getName());
        	else jgen.writeNullField("name");
        	if (value.getDescription() != null) jgen.writeStringField("description", value.getDescription());
        	else jgen.writeNullField("description");
	        if (value.getReleasingDate() != null) jgen.writeStringField("releasingDate", dateFormat.format(value.getReleasingDate()));
	        else jgen.writeNullField("releasingDate");
        	if (value.getBudget() != null) jgen.writeNumberField("budget", value.getBudget());
        	else jgen.writeNullField("budget");
        	// category
    		jgen.writeObjectFieldStart("category");
    		if (value.getCategory() != null) {
		        if (value.getCategory().getId() != null) jgen.writeNumberField("id", value.getCategory().getId());
		        else jgen.writeNullField("id");
		        if (value.getCategory().getName() != null) jgen.writeStringField("name", value.getCategory().getName());
		        else jgen.writeNullField("releasingDate");
		        if (value.getCategory().getDescription() != null) jgen.writeStringField("description", value.getCategory().getDescription());
		        else jgen.writeNullField("description");
    		}
	        jgen.writeEndObject();
	        // actors
        	jgen.writeArrayFieldStart("actors");
        	if (value.getActors() != null) {
		        for (Actor actor : value.getActors()) {
		        	jgen.writeStartObject();
			        	if (actor.getId() != null) jgen.writeNumberField("id", actor.getId());
			            else jgen.writeNullField("id");
			        	if (actor.getName() != null) jgen.writeStringField("name", actor.getName());
			            else jgen.writeNullField("name");
			        	if (actor.getNationality() != null) jgen.writeStringField("nationality", actor.getNationality());
			            else jgen.writeNullField("nationality");
			        	if (actor.getBirthDate() != null) jgen.writeStringField("birthDate", dateFormat.format(actor.getBirthDate()));
			            else jgen.writeNullField("birthDate");
			        	if (actor.getGender() != null) jgen.writeStringField("gender", actor.getGender().getDescription());
			            else jgen.writeNullField("gender");
		            jgen.writeEndObject();
				}
        	}
	        jgen.writeEndArray();
	        // awards
        	jgen.writeArrayFieldStart("awards");
        	if (value.getAwards() != null) {
		        for (Award award : value.getAwards()) {
		        	jgen.writeStartObject();
			        	if (award.getId() != null) jgen.writeNumberField("id", award.getId());
			            else jgen.writeNullField("id");
			        	if (award.getName() != null) jgen.writeStringField("name", award.getName());
			            else jgen.writeNullField("name");
			        	if (award.getAwardDate() != null) jgen.writeStringField("awardDate", dateFormat.format(award.getAwardDate()));
			            else jgen.writeNullField("awardDate");
		            jgen.writeEndObject();
				}
        	}
	        jgen.writeEndArray();
        jgen.writeEndObject();
	}
}
