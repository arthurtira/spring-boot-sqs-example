package com.arthurtira.sbs.service;

import com.arthurtira.sbs.dto.OrderDto;
import com.arthurtira.sbs.dto.ProductDto;
import com.arthurtira.sbs.enums.MessageType;
import com.arthurtira.sbs.producer.SqsMessageProducer;
import com.arthurtira.sbs.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderServiceTests {

    @Mock
    private SqsMessageProducer producer;

    private OrderService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new OrderServiceImpl(producer);
    }

    @Test
    public void placeOrder_TestSuccess_RequestSuccessful() {
        OrderDto orderDto = buildOrderDto();

        Map<String, Object> headers = new HashMap<>();
        headers.put("Message-Type", MessageType.ORDER.name());
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        //when(service.placeOrder(orderDto)).thenReturn(orderDto);
        doNothing().when(producer).send(orderDto, headers);

        OrderDto result = service.placeOrder(orderDto);

        assertNotNull(result);
        assertNotNull(result.getOrderId());
        assertEquals(result, orderDto);
        verify(producer, times(1)).send(orderDto, headers);
    }

    private OrderDto buildOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(UUID.fromString("3eef8f81-6b48-4468-916f-5407802f05bd"));
        orderDto.setCoupon("COUPON");
        orderDto.setReference("ORDER001");
        orderDto.setUserId("TEST_USER");
        orderDto.setProducts(Collections.singletonList(ProductDto.builder()
                .code("PRODCT001")
                .price(new BigDecimal(100))
                .productId("PRODUCTID")
                .productName("Some bag jeans")
                .build()));

        return orderDto;
    }

}
