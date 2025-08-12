package com.arsheo.pimmcp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
class CatalogServiceImplTest {

    @Autowired
    private CatalogServiceImpl catalogService;

    @Test
    void testCreateCatalogEntry_Success(){
        // Arrange
        CatalogInputDto inputDto = new CatalogInputDto(
            "iPhone 15 Test",
            "Latest iPhone model",
            Brand.APPLE,
            256
        );

        // Act
        CatalogOutputDto result = catalogService.createCatalogEntry(inputDto);

        // Assert
        assertNotNull(result);

    }

}
