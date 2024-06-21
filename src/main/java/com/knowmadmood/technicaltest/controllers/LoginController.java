package com.knowmadmood.technicaltest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowmadmood.technicaltest.businessdtos.books.BooksFilterDto;
import com.knowmadmood.technicaltest.businessdtos.login.LoginDto;
import com.knowmadmood.technicaltest.controllers.ports.in.login.LoginUseCase;
import com.knowmadmood.technicaltest.repositories.adapters.login.mappers.LoginMapper;

import org.openapitools.api.GetTokenApi;
import org.openapitools.model.TokenResponse;

@Validated
@RestController
public class LoginController implements GetTokenApi {

	private final LoginMapper mapper;

	private final LoginUseCase loginUseCase;

	public LoginController(@Autowired LoginMapper mapper, @Autowired LoginUseCase loginUserUseCase) {

		super();
		this.mapper = mapper;
		this.loginUseCase = loginUserUseCase;
	}

	@Override
	public ResponseEntity<TokenResponse> getToken() {

		LoginDto loginDto = loginUseCase.login();

		return ResponseEntity.ok(mapper.mapToTokenResponse(loginDto));
	}
}
