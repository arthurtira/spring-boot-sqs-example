package com.arthurtira.sbs.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID orderId;
    private String reference;
    private Date orderDate;
    private String userId;
    private String coupon;
    private List<ProductDto> products;

}
