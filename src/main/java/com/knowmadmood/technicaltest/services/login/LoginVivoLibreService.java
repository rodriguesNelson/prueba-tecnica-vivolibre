package com.knowmadmood.technicaltest.services.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowmadmood.technicaltest.businessdtos.login.LoginDto;
import com.knowmadmood.technicaltest.controllers.ports.in.login.LoginUseCase;
import com.knowmadmood.technicaltest.controllers.ports.out.login.LoginRepositoryPort;

@Service
public class LoginVivoLibreService implements LoginUseCase {

	private final LoginRepositoryPort loginRepositoryPort;

	public LoginVivoLibreService(@Autowired LoginRepositoryPort loginRepositoryPort) {

		super();
		this.loginRepositoryPort = loginRepositoryPort;
	}

	@Override
	public LoginDto login() {
		return loginRepositoryPort.login();
	}
}