package com.grcp.testvalidation.entrypoint.rest.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.util.StringUtils.*;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class DependentRequest {

    private long parentId;
    private String name;

    public boolean hasName() {
        return !isEmpty(this.name) && !this.name.isBlank();
    }
}
