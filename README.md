# Application to test the audit event

This application sends a event to the rabbitmq queue

The default host is **docker.local**

You can change it with **--spring.rabbitmq.host=localhost**


Arguments:

* **spring.rabbitmq.host** Host to connect
* **spring.rabbitmq.password** Password
* **spring.rabbitmq.username** Password
* **spring.rabbitmq.port** Port

See [Spring Boot Properties Reference] for more configuration

[Spring Boot Properties Reference]: http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html