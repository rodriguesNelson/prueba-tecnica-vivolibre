package com.knowmadmood.technicaltest.repositories.adapters.login.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knowmadmood.technicaltest.businessdtos.login.LoginDto;
import com.knowmadmood.technicaltest.controllers.ports.out.login.LoginRepositoryPort;
import com.knowmadmood.technicaltest.exceptions.VivolibreBusinessException;
import com.knowmadmood.technicaltest.exceptions.VivolibreGeneralException;
import com.knowmadmood.technicaltest.exceptions.VivolibreRuntimeException;
import com.knowmadmood.technicaltest.repositories.adapters.login.mappers.VivolibreLoginMapper;
import com.knowmadmood.technicaltest.repositories.models.login.request.VivolibreLoginRequest;
import com.knowmadmood.technicaltest.repositories.models.login.response.VivolibreLoginResponse;

@Service
@Component("loginRepositoryAdapter")
class VivelibreLoginReporitoryAdapter implements LoginRepositoryPort {

	private final VivolibreLoginMapper mapper;

	private final RestTemplate restTemplate;

	private final String url;

	private final String username;

	private final String password;

	public VivelibreLoginReporitoryAdapter(@Autowired VivolibreLoginMapper mapper, @Autowired RestTemplate restTemplate,
			@Value("${vivolibre.login.url}") String url, @Value("${vivolibre.login.username}") String username,
			@Value("${vivolibre.login.password}") String password) {

		super();
		this.mapper = mapper;
		this.restTemplate = restTemplate;
		this.username = username;
		this.password = password;
		this.url = url;
	}

	@Override
	public LoginDto login() {

		try {

			VivolibreLoginRequest payload = VivolibreLoginRequest.builder().username(username).password(password)
					.build();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<VivolibreLoginRequest> requestEntity = new HttpEntity<>(payload, headers);
			ResponseEntity<VivolibreLoginResponse> login = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					VivolibreLoginResponse.class);
			if (login.getStatusCode().is4xxClientError()) {
				throw new VivolibreGeneralException("It was not possible to get the token due an error",
						String.valueOf(login.getStatusCode()));
			}
			return mapper.mapToLoginDto(login.getBody());
		} catch (Exception e) {
			if (e instanceof VivolibreBusinessException) {
				throw e;
			} else {
				throw new VivolibreRuntimeException(
						String.format("There is an error when tries to recover the access token: %s", e.getMessage()),
						String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
			}
		}
	}
}
