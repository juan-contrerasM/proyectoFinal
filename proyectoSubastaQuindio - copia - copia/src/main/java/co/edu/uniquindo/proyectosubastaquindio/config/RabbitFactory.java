package co.edu.uniquindo.proyectosubastaquindio.config;

import com.rabbitmq.client.ConnectionFactory;


public class RabbitFactory {

    private ConnectionFactory connectionFactory;
    public RabbitFactory() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost("localhost");
        this.connectionFactory.setPort(5672);
        this.connectionFactory.setUsername("juan");
        this.connectionFactory.setPassword("123");
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}

