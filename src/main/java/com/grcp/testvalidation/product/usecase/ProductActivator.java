package com.grcp.testvalidation.product.usecase;

import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Slf4j
@Validated
public class ProductActivator {

    public void execute(long id, @Size(min = 3, max = 25) String username) {
        log.info("Activating product [{}] username [{}]", id, username);
    }
}
