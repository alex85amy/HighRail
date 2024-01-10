package com.example.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceTDXApi {

	public static void main(String[] args) throws Exception {
		String tokenUrl = "https://tdx.transportdata.tw/auth/realms/TDXConnect/protocol/openid-connect/token";
		String tdxUrl = "https://tdx.transportdata.tw/api/basic/v2/Rail/THSR/ODFare/{OriginStationID}/to/{DestinationStationID}";
        List<NameValuePair> params = new ArrayList<>();
        Map<String,String> headers = new HashMap<>();
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_id", "alex85amy-518c2d78-70b9-4801")); //your clientId
        params.add(new BasicNameValuePair("client_secret", "02dc5d1a-8876-4c89-9de5-7269efa87c25")); //your clientSecret
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String tokenInfo = postJsonString(tokenUrl, params);
        JsonNode tokenElem = objectMapper.readTree(tokenInfo);
        
		String accessToken = tokenElem.get("access_token").asText();
		headers.put("authorization", String.format("Bearer %s", accessToken));
		String resultJson = getJsonString(tdxUrl, headers);
		System.out.println(resultJson);
	}

	private static String getJsonString(String tdxUrl, Map<String, String> headers) throws Exception {
		HttpGet httpGet = new HttpGet(tdxUrl);
		if (Objects.nonNull(headers)) headers.forEach(httpGet::addHeader);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGet);
				InputStream content = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8))) {
//			System.out.println("ResponseStatus：" + response.getStatusLine().getStatusCode());
			return EntityUtils.toString(response.getEntity());
		}
	}

	private static String postJsonString(String url, List<NameValuePair> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpPost);
				InputStream content = response.getEntity().getContent();) {
//			System.out.println("ResponseStatus：" + response.getStatusLine().getStatusCode());
			return EntityUtils.toString(response.getEntity());
		}
	}

}