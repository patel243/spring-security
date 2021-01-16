/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.web.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;

/**
 * {@link CsrfLogoutHandler} is in charge of removing the {@link CsrfToken} upon logout. A
 * new {@link CsrfToken} will then be generated by the framework upon the next request.
 *
 * @author Rob Winch
 * @since 3.2
 */
public final class CsrfLogoutHandler implements LogoutHandler {

	private final CsrfTokenRepository csrfTokenRepository;

	/**
	 * Creates a new instance
	 * @param csrfTokenRepository the {@link CsrfTokenRepository} to use
	 */
	public CsrfLogoutHandler(CsrfTokenRepository csrfTokenRepository) {
		Assert.notNull(csrfTokenRepository, "csrfTokenRepository cannot be null");
		this.csrfTokenRepository = csrfTokenRepository;
	}

	/**
	 * Clears the {@link CsrfToken}
	 *
	 * @see org.springframework.security.web.authentication.logout.LogoutHandler#logout(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		this.csrfTokenRepository.saveToken(null, request, response);
	}

}
