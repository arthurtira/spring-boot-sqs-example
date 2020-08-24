package com.arthurtira.sbs.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private String productId;
    private String productName;
    private BigDecimal price;
    private String code;
}
