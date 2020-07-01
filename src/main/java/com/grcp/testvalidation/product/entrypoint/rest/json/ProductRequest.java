package com.grcp.testvalidation.product.entrypoint.rest.json;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class ProductRequest {

    @NonNull
    @Size(min = 3, max = 50)
    private String name;

    @DecimalMin("0.01")
    private double value;
}