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
        String res = (globalId != null ? "globalId=".concat(globalId.toString()) : "")
                .concat(firstName != null ? "&firstName=" + firstName : "")
                .concat(secondName != null ? "&secondName=" + secondName : "")
                .concat(thirdName != null ? "&thirdName=" + thirdName : "");
        return res.startsWith("&") ? res.substring(1) : res;
    }
}
