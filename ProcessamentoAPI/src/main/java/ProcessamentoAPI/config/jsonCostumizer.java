package ProcessamentoAPI.config;

import java.beans.JavaBean;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class jsonCostumizer {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
	    return builder -> builder
	        .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
	        .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
	        .deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
	}
}
