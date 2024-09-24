package com.example.RollCall.dto.repon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// cái nào null thì k hiện thị
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsiData<T> {

    private String code;
    private String message;
    private T data;

    public ResponsiData(String message) {
        this.message = message;
    }

}
