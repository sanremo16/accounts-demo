package org.san.home.accounts.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class PersonDto {
    private Long globalId;
    private String firstName;
    private String secondName;
    private String thirdName;

    public String toUrlParams() {
        return globalId != null ? "globalId=" + globalId : ""
               + firstName != null ? "&firstName=" + firstName : ""
               + secondName != null ? "&secondName=" + secondName : ""
               + thirdName != null ? "&thirdName=" + thirdName : "";
    }
}
