package com.example.xyinc.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.xyinc.model.Award;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
@Component
public class AwardSerializer extends StdSerializer<Award>  {

	public AwardSerializer() {
		this(null);
	}
	
	public AwardSerializer(Class<Award> t) {
		super(t);
	}

	@Override
	public void serialize(Award value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        jgen.writeStartObject();
        	if (value.getId() != null) jgen.writeNumberField("id", value.getId());
        	else jgen.writeNullField("id");
        	if (value.getName() != null) jgen.writeStringField("name", value.getName());
        	else jgen.writeNullField("name");
	        if (value.getAwardDate() != null) jgen.writeStringField("awardDate", dateFormat.format(value.getAwardDate()));
	        else jgen.writeNullField("awardDate");
	        // movie
        	if (value.getMovie() != null) {
	        	jgen.writeStartObject();
		        	if (value.getMovie().getId() != null) jgen.writeNumberField("id", value.getMovie().getId());
		            else jgen.writeNullField("id");
		        	if (value.getMovie().getName() != null) jgen.writeStringField("name", value.getMovie().getName());
		        	else jgen.writeNullField("name");
		        	if (value.getMovie().getDescription() != null) jgen.writeStringField("description", value.getMovie().getDescription());
		        	else jgen.writeNullField("description");
			        if (value.getMovie().getReleasingDate() != null) jgen.writeStringField("releasingDate", dateFormat.format(value.getMovie().getReleasingDate()));
			        else jgen.writeNullField("releasingDate");
		        	if (value.getMovie().getBudget() != null) jgen.writeNumberField("budget", value.getMovie().getBudget());
		        	else jgen.writeNullField("budget");
		        	// category
		    		jgen.writeObjectFieldStart("category");
		    		if (value.getMovie().getCategory() != null) {
				        if (value.getMovie().getCategory().getId() != null) jgen.writeNumberField("id", value.getMovie().getCategory().getId());
				        else jgen.writeNullField("id");
				        if (value.getMovie().getCategory().getName() != null) jgen.writeStringField("name", value.getMovie().getCategory().getName());
				        else jgen.writeNullField("releasingDate");
				        if (value.getMovie().getCategory().getDescription() != null) jgen.writeStringField("description", value.getMovie().getCategory().getDescription());
				        else jgen.writeNullField("description");
		    		}
			        jgen.writeEndObject();
	            jgen.writeEndObject();
        	}
	        jgen.writeEndArray();
        jgen.writeEndObject();
	}
}
