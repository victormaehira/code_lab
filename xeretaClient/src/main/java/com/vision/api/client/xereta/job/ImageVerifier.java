package com.vision.api.client.xereta.job;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vision.api.client.xereta.domain.Image;

@Component
@EnableScheduling
public class ImageVerifier {
	
	@Value("${filesPath}")
	private String filesPath;

	@Value("${imageURLApi}")
	private String imageURLApi;

	@Scheduled(cron = "0 * * * * *")
	public void verifica() throws IOException, JSONException {
		System.out.println(">verifica");
		System.out.println(LocalDateTime.now());
		Set<String> files = listFilesUsingJavaIO(filesPath);

		for (String fileName : files) {
			if (fileName.endsWith(".jpg")) {
				String description = DetectLabels.detectLabelsFirstOccurrence(filesPath + "\\" + fileName);
				System.out.println("description = " + description);

				byte[] fileContent = FileUtils.readFileToByteArray(new File(filesPath + "\\" + fileName));
				String encodedString = Base64.getEncoder().encodeToString(fileContent);

				Image image = new Image();
				image.setDescription(description);
				image.setImageBase64(encodedString);

				post(image, imageURLApi);
			}
		}
		System.out.println("< end of verifica");
	}

	private void post(Image image, String imageURLApi) throws JSONException, JsonMappingException, JsonProcessingException {
		System.out.println("posting image");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject imageJsonObject = new JSONObject();
		imageJsonObject.put("description", image.getDescription());
		imageJsonObject.put("imageBase64", image.getImageBase64());

		HttpEntity<String> request = new HttpEntity<String>(imageJsonObject.toString(), headers);

		String resposta = restTemplate.postForObject(imageURLApi, request, String.class);
		System.out.println(resposta);
		//ObjectMapper objectMapper = new ObjectMapper();
		//JsonNode root = objectMapper.readTree(resposta);
	}

	private Set<String> listFilesUsingJavaIO(String dir) {
		return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
				.collect(Collectors.toSet());
	}
}
