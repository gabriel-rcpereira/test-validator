package com.grcp.testvalidation.entrypoint.rest;

import com.grcp.testvalidation.entrypoint.rest.json.product.ProductRequest;
import com.grcp.testvalidation.usecase.ProductActivator;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductActivator productActivator;

    @PostMapping(value = "/api/v1/products", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody
                                                  @Valid ProductRequest request) {
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping(value = "/api/v1/products/{id}")
    public ResponseEntity<Void> updateProductToActivated(@PathVariable("id")
                                                             @Min(1) long id,
                                                         @RequestParam("username")
                                                                 String username) {
        productActivator.execute(id, username);
        return ResponseEntity.noContent().build();
    }
}
