package com.example.RollCall.mapper;

import com.example.RollCall.dto.repon.RequestRepon;
import com.example.RollCall.dto.request.RequestsRequest;
import com.example.RollCall.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    //@Mapping(target = "userId", ignore = true)
    Request toRequest(RequestsRequest request);

    RequestRepon toRequestRepon(Request request);

}
