package br.com.victor.eglicemia.producer.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.victor.eglicemia.producer.model.Glicemia;

@RestController
@RequestMapping("/glicemias")
public class GlicemiaResource {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${message.topic.name}")
	private String topicName;

	@PostMapping
	public String criar(@RequestBody List<Glicemia> glicemias, HttpServletResponse response) throws JsonProcessingException {
		for (Glicemia glicemia : glicemias) {
			System.out.println(" valor = " + glicemia.getValor());
			if (glicemia.getPessoa() != null) {
				System.out.println(" usuario id = " + glicemia.getPessoa().getCodigo());
			} else {
				System.out.println("sem usuario!");
			}

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(glicemia);
			send(jsonString);
		}
		return "ok";
	}

	private void send(String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
	}
}
