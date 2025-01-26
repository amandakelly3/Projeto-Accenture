package estoque.config;

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
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Bean
    public DirectExchange pedidosExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue pedidosQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(pedidosQueue()).to(pedidosExchange()).with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}