/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.codingjam.github.ui.repo

import it.codingjam.github.core.GithubInteractor
import it.codingjam.github.core.OpenForTesting
import it.codingjam.github.core.RepoId
import it.codingjam.github.util.NavigationSignal
import it.codingjam.github.vo.lce
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class RepoUseCase @Inject constructor(
        private val githubInteractor: GithubInteractor
) {

    fun CoroutineScope.reload(repoId: RepoId) = lce {
        githubInteractor.loadRepo(repoId.owner, repoId.name)
    }

    fun openUserDetail(login: String) = NavigationSignal("user", login)
}
