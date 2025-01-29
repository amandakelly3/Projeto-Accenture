package ProcessamentoAPI.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

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
    public DirectExchange pagamentoExchange() {
        return new DirectExchange(pagamentoExchange);
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
        return new Jackson2JsonMessageConverter();
    }

}
