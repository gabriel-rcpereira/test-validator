package com.grcp.testvalidation.product.entrypoint.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grcp.testvalidation.product.entrypoint.rest.json.ProductRequest;
import com.grcp.testvalidation.product.usecase.ProductActivator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {ProductController.class, ProductActivator.class})
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldCreateProduct_expectedCreatedStatus_fromBeanValidation() throws Exception {
        ProductRequest request = ProductRequest.builder().name("product").value(5.0).build();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/products")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldCreateProductWhenNameIsEmpty_expectedBadRequest_fromBeanValidation() throws Exception {
        ProductRequest request = ProductRequest.builder().name("").value(0).build();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/products")
                    .contentType(APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateProductWhenValueLessThanZeroDotOne_expectedBadRequest_fromBeanValidation() throws Exception {
        ProductRequest request = ProductRequest.builder().name("product one").value(0).build();
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/products")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldActivateProduct_expectedNoContentStatus() throws Exception {
        mockMvc.perform(put("/api/v1/products/1")
                    .queryParam("username", "usernameActivator"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldActivateProductWhenUsernameLessThanThreeLetters_expectedBadRequest_fromComponentMethodValidation() throws Exception {
        mockMvc.perform(put("/api/v1/products/1")
                .queryParam("username", "us"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldActivateProductWhenProductLessThanOne_expectedBadRequest_fromPathParamValidation() throws Exception {
        mockMvc.perform(put("/api/v1/products/0"))
                .andExpect(status().isBadRequest());
    }
}