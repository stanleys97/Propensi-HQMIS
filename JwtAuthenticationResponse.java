package com.project.propensib8.payload;

import java.util.HashSet;
import java.util.Set;

import com.project.propensib8.model.Role;

public class JwtAuthenticationResponse {
	private Set<Role> roles = new HashSet<>();
	private Long id;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, Set<Role> roles, Long id) {
        this.accessToken = accessToken;
        this.roles = roles;
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}