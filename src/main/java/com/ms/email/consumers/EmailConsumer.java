package com.ms.email.consumers;

import com.ms.email.domain.Email;
import com.ms.email.dto.EmailDTO;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

	@Autowired
	private EmailService service;

	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailDTO emailDTO){
		var email = new Email();
		BeanUtils.copyProperties(emailDTO, email);
		service.sendEmail(email);
	}
}
