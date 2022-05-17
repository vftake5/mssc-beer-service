package guru.springframework.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

public class JmsConfig
{
	public static final String BREWING_REQ_Q = "brewing-request";
	public static final String INVENTORY_REQ_Q = "inventory-request";

	@Bean
	public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper)
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		converter.setObjectMapper(objectMapper);

		return converter;
	}
}
