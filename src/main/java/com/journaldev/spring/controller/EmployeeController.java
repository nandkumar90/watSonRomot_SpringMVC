package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.journaldev.spring.discovery.Discovery;
//import com.google.gson.Gson;
import com.journaldev.spring.model.Employee;
import com.journaldev.spring.model.Inputtext;
import com.journaldev.spring.model.LangTransLation;
import com.journaldev.spring.model.Language;
import com.journaldev.spring.model.Output;
import com.journaldev.spring.model.RequestBodyText;
import com.journaldev.spring.model.RevResponse;
import com.journaldev.spring.model.Robot;
import com.journaldev.spring.model.Text;
import com.journaldev.spring.model.Translation;
import com.journaldev.spring.model.Translationresponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	Map<String, String> contextMap = new HashMap<String, String>();
	
	
	@RequestMapping(value = "watson/workspaceDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> workSpaceDetail() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic YjhkYTdhYjUtNTdhYy00MjQxLTk0YTktNmE1MDM0YmYyOWEzOndnTUZoeTZJVnl3aQ==");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate=new RestTemplate();
	    ResponseEntity<String> result = restTemplate.exchange("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/5cc74062-e7ec-4057-a94d-4ae112ca6462?version=2017-05-06&include_count=false", HttpMethod.GET, request, String.class);
		
		return result;
	}	
	
	@RequestMapping(value = "watson/message", method = RequestMethod.POST)
	public @ResponseBody Robot messages(@RequestBody RequestBodyText bodyText) {
		Text input=new Text();
		input.setText(bodyText.getText());
		Inputtext inputtext =new Inputtext(); 
		String conversationId=bodyText.getConversationId();
		inputtext.setAlternate_intents(true);
		Gson gson =new Gson();
		inputtext.setText(input);
		System.out.println(gson.toJson(inputtext));
       // String body="{\r\n  \"input\": {\r\n    \"text\": \""+str.getText()+"\"\r\n  },\r\n \"alternate_intents\": true\r\n}\r\n";
		//HttpHeaders headers = new HttpHeaders();
		String body=gson.toJson(inputtext);
		if(null !=conversationId && !conversationId.isEmpty() && contextMap.get(conversationId)!=null){
			//System.out.print("\"Hello\"");
			String[] jsonelement=body.split(",");
			String context=contextMap.get(conversationId);
			body=jsonelement[0]+", \"context\":"+context+","+jsonelement[1];
		}
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YjhkYTdhYjUtNTdhYy00MjQxLTk0YTktNmE1MDM0YmYyOWEzOndnTUZoeTZJVnl3aQ==");
        
		HttpEntity request = new HttpEntity(body,headers);
        
		RestTemplate restTemplate=new RestTemplate();
	    String result = restTemplate.postForObject("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/5cc74062-e7ec-4057-a94d-4ae112ca6462/message?version=2017-05-06&include_count=false", request, String.class);
        JSONObject jsonObject = new JSONObject(result);
        ObjectMapper oMapper = new ObjectMapper();
        Object object=jsonObject.get("output");
        Object context=jsonObject.get("context");
        JSONObject obj = new JSONObject(context.toString());
        String key=(String) obj.get("conversation_id");
        String json=jsonObject.get("output").toString();
        Output output = gson.fromJson(json, Output.class);
        List<String> arrayList= output.getText();
        String response=arrayList.get(0);
        contextMap.put(key, context.toString());
        Robot robot=new Robot();
        //robot.setMsg("<a href=http://localhost:8080/SpringRestExample/#/newform/>");
       String selectedLang=bodyText.getLang();
       
        if(null !=selectedLang && !selectedLang.isEmpty() &&selectedLang.equalsIgnoreCase("hi"))
        {
        	 MultiValueMap<String, Object> headersLang = new LinkedMultiValueMap<String, Object>();
        	 headersLang.add("Content-Type", "application/json");
        	 headersLang.add("rev-api-key", "452663dff9d0f0334aa92c72dce4c679");
        	 headersLang.add("rev-app-id", "testApp");
        	 headersLang.add("cache-control", "no-cache");
             List<String> lasString=new ArrayList<String>();
             lasString.add(response);
             Language language=new Language();
             language.setData(lasString);
        	 Gson gs=new Gson();
        	 System.out.println(gs.toJson(language));
        	 HttpEntity requestLang = new HttpEntity(language,headersLang);

     		
    		RestTemplate restTemplateLang=new RestTemplate();
    	    String resultLang = restTemplate.postForObject("https://api-gw.revup.reverieinc.com/apiman-gateway/ThekaDesiKhana201/localization/1.0?source_lang=english&target_lang=hindi&domain=3&mt_context=generic_english_proper", requestLang, String.class);
    	    JSONObject jsonObj = new JSONObject(resultLang);
            
    	    Object responseStr=jsonObj.get("responseList");
            String res=responseStr.toString().substring(1, responseStr.toString().length()-1);

    	    System.out.println(res);
    		Gson gsonn=new Gson();
            RevResponse revResponse=gson.fromJson(res,RevResponse.class);
    	   
    	    response=revResponse.getOutString();
        }
        
        if(null !=selectedLang && !selectedLang.isEmpty() &&selectedLang.equalsIgnoreCase("fr"))
        {
        	 MultiValueMap<String, Object> headersFr = new LinkedMultiValueMap<String, Object>();
        	 headersFr.add("Accept", "application/json");
        	 headersFr.add("Content-Type", "application/json");
        	 headersFr.add("Authorization", "Basic NjVmMGM2ODMtZGI3MC00NDJjLTllMmUtY2M0ODJmYzMyZTdlOktxdEtXUnBXdGxEWg==");
        	 headersFr.add("cache-control", "no-cache");
        	 
     		LangTransLation langFr=new LangTransLation();
     		 langFr.setModel_id("en-fr");
     		 langFr.setSource("en");
     		 langFr.setTarget("fr");
     		 langFr.setText(response);
             String strUser="{\"model_id\":\"en-fr\",\"source\":\"en\",\"target\":\"fr\",\"text\":[\""+response+"\"]}";
        	 Gson gs=new Gson();
        	 System.out.println(gs.toJson(langFr));
        	 HttpEntity requestLang = new HttpEntity(strUser,headersFr);

     		
    		RestTemplate restTemplateLang=new RestTemplate();
    		String resultLang = restTemplateLang.postForObject("https://watson-api-explorer.mybluemix.net/language-translator/api/v2/translate", requestLang, String.class);
    		Translationresponse tranresponse=gson.fromJson(resultLang,Translationresponse.class);
    		List<Translation> translation=tranresponse.getTranslations();
    		Translation tresponse=translation.get(0);
    		response =tresponse.getTranslation();
        }
        
        if(response.equalsIgnoreCase("discovery")){
        	 MultiValueMap<String, Object> headersdis = new LinkedMultiValueMap<String, Object>();
        	 
        	 headersdis.add("Accept", "application/json");
        	 headersdis.add("Content-Type", "application/json");
        	 headersdis.add("Authorization", "Basic MTg0ZjU2NGUtOWM3Yy00OTA5LWEwY2QtMGYzYjkwMmUzM2Y3OnVza0JWWlpBaUsyQQ==");
        	 headersdis.add("cache-control", "no-cache");
        	 HttpEntity requestdis= new HttpEntity(headersdis);

     		RestTemplate restTemplateLang=new RestTemplate();

     		ResponseEntity<String> resultLang = restTemplateLang.exchange("https://gateway.watsonplatform.net/discovery/api/v1/environments/02e67059-ac04-4490-a165-f2b2fd7c75be/collections/a6f88ca5-97b0-429b-957e-58f950770b1d/query?version=2016-11-07&query=apply%20for%20visa&count=&offset=&aggregation=&filter=&passages=false&highlight=true&return=", HttpMethod.GET,requestdis, String.class);
     		String objects = resultLang.getBody();
     		Discovery discovery=gson.fromJson(objects, Discovery.class);
     		response=discovery.getResults()[0].getHtml();
            Document doc = Jsoup.parse(response);
            String tablecells=doc.toString();
            Elements tds=doc.select("title");  // select the tds from your table
            for(Element td : tds) {  // loop through them
                if(td.text().contains("no title")) {  // found the one you want
                    td.text("Best Matched Document");          // Replace with your text
                }
            }
          
            response=doc.toString();

     		
       }
        robot.setMsg(response);
        robot.setSender("ROBOT");
        robot.setConversation_id(key);
		return robot; 
	}	
	
	@RequestMapping(value = "watson/mes", method = RequestMethod.POST)
	public HttpResponse<String> watsonMessage() throws UnirestException{
		HttpResponse<String> response = Unirest.post("https://gateway.watsonplatform.net/conversation/api/v1/workspaces/5cc74062-e7ec-4057-a94d-4ae112ca6462/message?version=2017-04-21")
				  .header("content-type", "application/json")
				  .header("authorization", "Basic YjhkYTdhYjUtNTdhYy00MjQxLTk0YTktNmE1MDM0YmYyOWEzOndnTUZoeTZJVnl3aQ==")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "312f496a-5ef0-f449-5a33-d275df85bb3f")
				  .body("{\r\n  \"input\": {\r\n    \"text\": \"hello\"\r\n  },\r\n \"alternate_intents\": true\r\n}\r\n")
				  .asString();
		return response;
	}
	
	public static JSONObject objectToJSONObject(Object object){
	    Object json = null;
	    JSONObject jsonObject = null;
	    try {
	        json = new JSONTokener(object.toString()).nextValue();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    if (json instanceof JSONObject) {
	        jsonObject = (JSONObject) json;
	    }
	    return jsonObject;
	}

	public static JSONArray objectToJSONArray(Object object){
	    Object json = null;
	    JSONArray jsonArray = null;
	    try {
	        json = new JSONTokener(object.toString()).nextValue();
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    if (json instanceof JSONArray) {
	        jsonArray = (JSONArray) json;
	    }
	    return jsonArray;
	}
	
	
}
