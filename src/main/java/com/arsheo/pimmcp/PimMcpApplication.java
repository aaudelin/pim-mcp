package com.arsheo.pimmcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PimMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PimMcpApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider tool(CatalogService catalogService) {
        return MethodToolCallbackProvider.builder().toolObjects(catalogService).build();
    }

}
