package NotificacaoAPI.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class RabbitmqConfig {

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.routing.key.name}")
	private String routingkey;

	@Value("${rabbitmq.pagamento.queue.name}")
	private String pagamentoQueue;

	@Value("${rabbitmq.pagamento.exchange.name}")
	private String pagamentoExchange;

	@Value("${rabbitmq.pag.routing.key.name}")
	private String pagroutingkey;


	@Bean
    public DirectExchange pedidosExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue notificacaoQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(notificacaoQueue()).to(pedidosExchange()).with(routingkey);
    }

    @Bean
    public TopicExchange pagamentoExchange() {
        return new TopicExchange(pagamentoExchange);
    }

    @Bean
    public Queue pagamentoQueue() {
        return new Queue(pagamentoQueue);
    }

    @Bean
    public Binding pagbinding() {
        return BindingBuilder.bind(pagamentoQueue()).to(pagamentoExchange()).with(pagroutingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignora campos desconhecidos
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true); // Serializa enums como strings
        return new Jackson2JsonMessageConverter(mapper);
    }

}