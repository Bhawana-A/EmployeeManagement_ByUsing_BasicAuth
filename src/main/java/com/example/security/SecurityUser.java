package com.example.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.Employee;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class SecurityUser implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Employee employee;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays
				.stream(employee.getRole().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
	}

	@Override
	public @Nullable String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

}
