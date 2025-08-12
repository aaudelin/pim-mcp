package com.arsheo.pimmcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.HashMap;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Value("${contentful.url}")
    private String contentfulUrl;

    @Value("${contentful.key}")
    private String contentfulKey;

    @Value("${contentful.space}")
    private String contentfulSpace;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CatalogServiceImpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    @Tool(name = "create_catalog_entry", description = "Creates a new catalog entry in Contentful. Data can be fetched on the web before if needed.")
    public CatalogOutputDto createCatalogEntry(@ToolParam(description = "Product informations") CatalogInputDto catalogInputDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + contentfulKey);
            headers.set("Content-Type", "application/vnd.contentful.management.v1+json");
            headers.set("X-Contentful-Content-Type", "product");

            Map<String, Object> entryData = new HashMap<>();
            Map<String, Object> fields = new HashMap<>();

            fields.put("name", Map.of("en-US", catalogInputDto.name()));
            fields.put("description", Map.of("en-US", catalogInputDto.description()));
            fields.put("brand", Map.of("en-US", catalogInputDto.brand().toString()));
            fields.put("storage", Map.of("en-US", catalogInputDto.storage()));

            entryData.put("fields", fields);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(entryData, headers);

            String url = contentfulUrl + "/spaces/"+ contentfulSpace + "/environments/master/entries";
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
            );

            JsonNode responseJson = objectMapper.readTree(response.getBody());
            String entryId = responseJson.get("sys").get("id").asText();

            return new CatalogOutputDto(entryId);
        } catch (Exception e) {
            return new CatalogOutputDto(e.getMessage());
        }
    }

}
