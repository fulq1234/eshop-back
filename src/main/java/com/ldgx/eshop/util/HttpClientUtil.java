package com.ldgx.eshop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {	
	
	private static RequestConfig config = RequestConfig.custom()
		    .setCookieSpec("standard-strict")
		    .setExpectContinueEnabled(true)
		    .setTargetPreferredAuthSchemes(Arrays.asList(new String[] { "NTLM", "Digest" }))
		    .setProxyPreferredAuthSchemes(Arrays.asList(new String[] { "Basic" }))
		    .setConnectTimeout(5000)
		    .setSocketTimeout(5000)
		    .build();
	 private static final CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	
	 /**
	  * 发送get请求
	  * @param url
	  * @param params:参数
	  * @throws IOException
	  */
	public static StringBuffer sendGet(String url,Map<String,String> params) throws IOException{
		
		//参数
		  if ((params != null) && (!params.isEmpty()))
	      {
	        List<NameValuePair> pairs = new ArrayList(params.size());
	        for (Map.Entry<String, String> entry : params.entrySet())
	        {
	          String value = (String)entry.getValue();
	          if (value != null) {
	            pairs.add(new BasicNameValuePair((String)entry.getKey(), value));
	          }
	        }
	        url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
	      }
		  
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(url);
		response = httpClient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode != 200) {
			httpGet.abort();//中止
			throw new RuntimeException("HttpClient,error status code :" + statusCode);
		}
		InputStream entityContent = response.getEntity().getContent();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(entityContent));
		
		String inputline;
		StringBuffer responsesb = new StringBuffer();
		while((inputline = reader.readLine() )!= null) {
			responsesb.append(inputline);
		}
		reader.close();
		httpClient.close();
		return responsesb;
		
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer sendPost(String url,Map<String,String> params) throws IOException{
		if((params != null) &&(!params.isEmpty())) {
			List<NameValuePair> pairs = new ArrayList(params.size());
			for(Map.Entry<String, String> entry : params.entrySet()) {
				String value = (String)entry.getValue();
				if(value != null) {
					pairs.add(new BasicNameValuePair((String)entry.getKey(),value));
				}
			}
	        url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
		}
		
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		response = httpClient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode != 200) {
			httpPost.abort();//中止
			throw new RuntimeException("HttpClient,error status code :" + statusCode);			
		}
		
		InputStream entityContent = response.getEntity().getContent();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(entityContent));
		
		String inputline;
		StringBuffer responsesb = new StringBuffer();
		while((inputline = reader.readLine() )!= null) {
			responsesb.append(inputline);
		}
		reader.close();
		httpClient.close();
		return responsesb;
	}
	
	public static void main(String[] args) {
		
	}
}
