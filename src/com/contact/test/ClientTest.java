package com.contact.test;

import java.util.Scanner;
import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder; 
import javax.ws.rs.client.Entity; 
import javax.ws.rs.core.Form; 
import javax.ws.rs.core.MediaType; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientTest {
	private Client client;
	private final String URL_CONTACT_SERVICE_POST =
			"http://localhost:8080/solstice-challenge/contact";
	private static final String SUCCESS = "success"; 
	private static final String FAIL = "fail";
	private static final String NOT_FOUND = "not found";
	
	//	status codes
	private final static String STATUS_CODE_SUCCESS = "200";
	private final static String STATUS_CODE_CREATED = "201";
	private final static String STATUS_CODE_NOTFOUND = "204";
	private final static String STATUS_CODE_FAILURE = "500";

	
	public void init() {
		this.client = ClientBuilder.newClient();
	}
			
	public void createContact() {
		System.out.println("Create a new contact");
		JSONObject jsonObj = fillOutContact();
		Form form = new Form(); 
	    form.param("action", "create");
	    form.param("data", jsonObj.toString());  
	    String callResult = client
	    		.target(URL_CONTACT_SERVICE_POST)
	    		.request(MediaType.APPLICATION_JSON)
	    		.put(Entity.entity(form, 
	    				MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	    				String.class); 
	    String response = parseResponse(callResult);
	    System.out.println("[Message] Create contact: " + response);
		return;
	}
	
	public void updateContact() {
		System.out.println("Update an existing contact: ");
		JSONObject jsonObj = fillOutContact();
		Scanner reader = new Scanner(System.in);
		System.out.println("Contact id to update: ");
		String id = reader.nextLine();
		jsonObj.put("id", id);
		Form form = new Form(); 
	    form.param("action", "update");
	    form.param("data", jsonObj.toString());  
	    String callResult = client
	    		.target(URL_CONTACT_SERVICE_POST)
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.entity(form, 
	    				MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	    				String.class); 
	    String response = parseResponse(callResult);
	    System.out.println("[Message] Update contact: " + response);
		return;
	}
	
	public void deleteContact() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Contact id to delete: ");
		String id = reader.nextLine();
	    String callResult = client
	    		.target(URL_CONTACT_SERVICE_POST)
	    		.path("/{id}")
	    		.resolveTemplate("id", id)
	    		.request(MediaType.APPLICATION_JSON)
	    		.delete(String.class); 
	    String response = parseResponse(callResult);
	    System.out.println("[Message] Delete contact: " + response);
		return;
	};
	public String getContactById(String id) {
		String jsonStr = client
				.target(URL_CONTACT_SERVICE_POST)
				.path("/id/{id}")
				.resolveTemplate("id", id)
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		String response = parseResponse(jsonStr);
		System.out.println("[Message] Retrive the contact: " + response);
		return response;
	}

	public String getContactByNumber(String number) {
		String jsonStr = client
				.target(URL_CONTACT_SERVICE_POST)
				.path("/number/{number}")
				.resolveTemplate("number", number)
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		String response = parseResponse(jsonStr);
		System.out.println("[Message] Retrive the contact: " + response);
		return response;
	}
	public String getContactByEmail(String email) {
		String jsonStr = client
				.target(URL_CONTACT_SERVICE_POST)
				.path("/email/{email}")
				.resolveTemplate("email", email)
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		String response = parseResponse(jsonStr);
		System.out.println("[Message] Retrive the contact: " + response);
		return response;
	}
	public String getContactsByState(String state) {
		String jsonStr = client
				.target(URL_CONTACT_SERVICE_POST)
				.path("/state/{state}")
				.resolveTemplate("state", state)
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		String response = parseResponse(jsonStr);
		System.out.println("[Message] Retrive the contacts: " + response);
		return response;
	}
	public String getContactsByCity(String city) {
		String jsonStr = client
				.target(URL_CONTACT_SERVICE_POST)
				.path("/city/{city}")
				.resolveTemplate("city", city)
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		String response = parseResponse(jsonStr);
		System.out.println("[Message] Retrive the contacts: " + response);
		return response;
	}
	
	private String parseResponse(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj;
		String message = FAIL;
		try {
			jsonObj = (JSONObject) parser.parse(jsonStr);
			message = (String) jsonObj.get("message");
			if (jsonObj.containsKey("id")) {
				System.out.println("[Message] Contact id: " + (JSONArray) jsonObj.get("id"));
			}
			if (jsonObj.containsKey("data")) {
				displayContactRecord((JSONArray) jsonObj.get("data"));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return message;
	}
	
	private void displayContactRecord(JSONArray jsonArray) throws ParseException {
		System.out.println("[Message] Number of contacts: " + jsonArray.size());
		for (Object json: jsonArray) {
			System.out.println("-------------------------------");
			JSONObject contact = (JSONObject) json;
			JSONObject name = (JSONObject) contact.get("name");
			System.out.println("First name: " + name.get("first"));
			System.out.println("Last name: " + name.get("last"));
			System.out.println("Compnay name: " + contact.get("company"));
			System.out.println("Profile link: " + contact.get("profile_image"));
			System.out.println("Email: " + contact.get("email"));
			System.out.println("Birthday: " + contact.get("birthday"));
			JSONObject number = (JSONObject) contact.get("number");
			System.out.println("Phone number");
			System.out.println("Work: " + number.get("work"));
			System.out.println("Personal: " + number.get("personal"));
			JSONObject address = (JSONObject) contact.get("address");
			System.out.println("Address");
			System.out.println("Street: " + address.get("street"));
			System.out.println("City: " + address.get("city"));
			System.out.println("State: " + address.get("state"));
			System.out.println("Zip: " + address.get("zip"));
		}
		return;
	}

	private JSONObject fillOutContact() {
		Scanner reader = new Scanner(System.in);
		System.out.println("First name: ");
		String firstName = reader.nextLine();
		System.out.println("Last name: ");
		String lastName = reader.nextLine();
		System.out.println("Company: ");
		String company = reader.nextLine();
		System.out.println("Profile Link: ");
		String profileImage = reader.nextLine();
		System.out.println("Email: ");
		String email = reader.nextLine();
		System.out.println("Birthday(YYYY-MM-DD): ");
		String birthday = reader.nextLine();
		System.out.println("Phone number");
		System.out.println("Work phone: ");
		String workNumber = reader.nextLine();
		System.out.println("Personal phone: ");
		String personalNumber = reader.nextLine();
		System.out.println("Address");
		System.out.println("Street: ");
		String street = reader.nextLine();
		System.out.println("City: ");
		String city = reader.nextLine();
		System.out.println("State: ");
		String state = reader.nextLine();
		System.out.println("Zip: ");
		String zip = reader.nextLine();
		JSONObject jsonObj = new JSONObject();
		JSONObject name = new JSONObject();
		name.put("first", firstName);
		name.put("last", lastName);
		jsonObj.put("name", name);
		jsonObj.put("company", company);
		jsonObj.put("profile_image", profileImage);
		jsonObj.put("email", email);
		jsonObj.put("birthday", birthday);
		JSONObject number = new JSONObject();
		number.put("work", workNumber);
		number.put("personal", personalNumber);
		jsonObj.put("number", number);
		JSONObject address = new JSONObject();
		address.put("street", street);
		address.put("city", city);
		address.put("state", state);
		address.put("zip", zip);
		jsonObj.put("address", address);
		return jsonObj;
	}

	public void tesGetContactByEmail() {
		// TODO Auto-generated method stub
		
	}
}
