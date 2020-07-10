package com.grcp.testvalidation.entrypoint.rest.json.person;

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
public class PersonRequest {

    @NotBlank()
    @Size(min = 5, max = 30)
    private String name;
    @NotBlank()
    @Size(min = 8)
    private String username;
    private JobPositionRequest jobPositionRequest;
}
