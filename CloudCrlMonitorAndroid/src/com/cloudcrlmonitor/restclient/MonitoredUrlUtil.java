package com.cloudcrlmonitor.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.crlmonitor.entity.MonitoredUrl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MonitoredUrlUtil {
	
	public List<MonitoredUrl> getUrlStatus() throws Exception {
		String json = readUrl("http://crlmonitor-victormaehira.rhcloud.com/rest/monitoredurls/status");
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray urlArray = jsonParser.parse(json).getAsJsonArray();

		List<MonitoredUrl> urlList = new ArrayList<MonitoredUrl>();
		for (JsonElement url : urlArray) {
			MonitoredUrl courseObj = gson.fromJson(url, MonitoredUrl.class);
			urlList.add(courseObj);
		}
		return urlList;
	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}

	}
}
