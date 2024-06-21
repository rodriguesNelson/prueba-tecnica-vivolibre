package com.knowmadmood.technicaltest.repositories.adapters.login.mappers;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.knowmadmood.technicaltest.businessdtos.login.LoginDto;
import com.knowmadmood.technicaltest.repositories.models.login.response.VivolibreLoginResponse;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VivolibreLoginMapper {

	@Mapping(target = "token", source = "accessToken")
	public LoginDto mapToLoginDto(VivolibreLoginResponse fedIdModel);
}
