package com.knowmadmood.technicaltest.repositories.models.login.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VivolibreLoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("token")
	private String accessToken;
}
