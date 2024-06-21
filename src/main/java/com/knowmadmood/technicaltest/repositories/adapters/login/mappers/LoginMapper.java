package com.knowmadmood.technicaltest.repositories.adapters.login.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.knowmadmood.technicaltest.businessdtos.login.LoginDto;

import org.openapitools.model.TokenResponse;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoginMapper {

	@Mapping(target = "authVivelibreToken", source = "token")
	@Mapping(target = "date", expression = "java(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern(\"MMMM d, u\", java.util.Locale.ENGLISH)))")
	public TokenResponse mapToTokenResponse(LoginDto dto);
}
