package com.arsheo.pimmcp;

public record CatalogInputDto(
        String name,
        String description,
        Brand brand,
        Integer storage
) {
}
