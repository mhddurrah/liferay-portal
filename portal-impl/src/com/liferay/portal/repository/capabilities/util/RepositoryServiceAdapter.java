/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.repository.capabilities.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Repository;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.RepositoryLocalService;
import com.liferay.portal.service.RepositoryService;

/**
 * @author Iván Zaera
 */
public class RepositoryServiceAdapter {

	public RepositoryServiceAdapter(
		RepositoryLocalService repositoryLocalService) {

		this(repositoryLocalService, null);
	}

	public RepositoryServiceAdapter(
		RepositoryLocalService repositoryLocalService,
		RepositoryService repositoryService) {

		_repositoryLocalService = repositoryLocalService;
		_repositoryService = repositoryService;
	}

	public Repository fetchRepository(long repositoryId)
		throws PortalException {

		Repository repository = null;

		if (_repositoryService != null) {
			repository = _repositoryLocalService.fetchRepository(repositoryId);

			if (repository != null) {
				repository = _repositoryService.getRepository(repositoryId);
			}
		}
		else {
			repository = _repositoryLocalService.fetchRepository(repositoryId);
		}

		return repository;
	}

	public Repository getRepository(long repositoryId) throws PortalException {
		Repository repository = null;

		if (_repositoryService != null) {
			repository = _repositoryService.getRepository(repositoryId);
		}
		else {
			repository = _repositoryLocalService.getRepository(repositoryId);
		}

		return repository;
	}

	public Repository updateRepository(Repository repository)
		throws PrincipalException {

		if (_repositoryService != null) {
			throw new PrincipalException(
				"Update is forbidden when using the Repository interface");
		}

		return _repositoryLocalService.updateRepository(repository);
	}

	private final RepositoryLocalService _repositoryLocalService;
	private final RepositoryService _repositoryService;

}