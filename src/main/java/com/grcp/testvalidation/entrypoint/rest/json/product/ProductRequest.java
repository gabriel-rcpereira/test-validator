package com.grcp.testvalidation.entrypoint.rest.json.product;

import com.grcp.testvalidation.entrypoint.rest.customvalidator.product.annotation.ProductAttribute;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class ProductRequest {

    @NotBlank(message = "{productRequest.name.required}")
    @Size(min = 3, max = 50)
    private String name;

    @DecimalMin(value = "0.01", message = "{productRequest.value.min}")
    private double value;

    @Valid
    @ProductAttribute(message = "{productRequest.attributes.error}")
    @Builder.Default
    private List<ProductAttributeRequest> attributes = new ArrayList<>();
}
