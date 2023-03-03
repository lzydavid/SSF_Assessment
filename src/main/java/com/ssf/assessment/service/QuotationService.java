package com.ssf.assessment.service;

import java.io.StringReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssf.assessment.model.Item;
import com.ssf.assessment.model.Quotation;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {

    public final static String URL = "http://quotation.chuklee.com";

    private Map<String, Float> quotations = new HashMap<>();

    public Quotation geQuotation(List<Item> items){

        Quotation quotation = new Quotation();

        String url = UriComponentsBuilder.fromUriString(URL).toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JsonArray array = Json.createArrayBuilder().add("apple").add("orange").build();
        
        // RequestEntity<Void> requestEntity = new RequestEntity<JsonArray>(array, headers, HttpMethod.POST, URL, getClass());

        RequestEntity<Void> req = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON)
				.build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);


        String body = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(body));
		JsonObject obj = reader.readObject();
		JsonObject arr = obj.getJsonObject("quotations");
        String id = obj.getString("quoteId");

        //float apple = Float.parseFloat(arr.getString("apple"));

        return null;
    }

    //hardcode
    public Quotation geQuotation2(List<Item> items) {
        Quotation quotation = new Quotation();
        setPrice();
        quotation.setQuotations(quotations);
        quotation.setQuoteId(UUID.randomUUID().toString().substring(0, 8));
        return quotation;
    }

    //hardcode
    public void setPrice() {

        quotations.put("apple", 0.5f);
        quotations.put("orange", 0.4f);
        quotations.put("bread", 2.0f);
        quotations.put("cheese", 5.0f);
        quotations.put("chicken", 6.0f);
        quotations.put("mineral_water", 0.5f);
        quotations.put("instant_noodles", 8.0f);

    }

    public float getTotal(List<Item> items,Quotation q) {

        float total=0.0f;

        for (Item item : items) {
            int quantity = Integer.parseInt(item.getQuantity());
            total+=((q.getQuotation(item.getItem()))*quantity);
        }

        return total;
    }
}
