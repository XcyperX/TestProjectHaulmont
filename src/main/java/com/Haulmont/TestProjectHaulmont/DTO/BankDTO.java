package com.Haulmont.TestProjectHaulmont.DTO;

import com.Haulmont.TestProjectHaulmont.model.Credit;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BankDTO implements Serializable {
    @JsonProperty("bank_id")
    private String id;

    @NotEmpty
    private String name;

    private List<UserDTO> users = new ArrayList<>();

    private List<CreditDTO> credits = new ArrayList<>();
}
