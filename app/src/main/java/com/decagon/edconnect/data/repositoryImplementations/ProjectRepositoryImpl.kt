package com.decagon.edconnect.data.repositoryImplementations

import androidx.room.withTransaction
import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheProjectMapper
import com.decagon.edconnect.data.datasources.remoteSource.ProjectApiService
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiProjectMapper
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.domain.repository.ProjectRepository
import com.decagon.edconnect.utils.Constants
import com.decagon.edconnect.utils.Constants.PROJECT_API_SERVICE
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.singleSourceManager
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class ProjectRepositoryImpl @Inject constructor (
    private val database: EdConnectDatabase,
    private val projectApiService: ProjectApiService,
    private val cacheProjectMapper: CacheProjectMapper,
    private val apiProjectMapper: ApiProjectMapper
    )
    : ProjectRepository {

    @InternalCoroutinesApi
    override suspend fun getAllProjects(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow {
                    emit(cacheProjectMapper.toDomainListMapper(database.projectDao().getAllProjects()))
                }
            },
            shouldFetchFromRemote = {
                    fetchFromRemote
            },
            fetchFromRemote = {
                projectApiService.getAllProjects()
            },
            saveToLocalDB = {
                database.withTransaction {
                    it.payload.forEach {
                        database.projectDao().insertProject(cacheProjectMapper.mapFromDomainModel(it))
                    }
                }
            }
    )

    override suspend fun getSingleProject(id: String, fetchFromRemote: Boolean): Flow<Resource<DomainProject>> =
        singleSourceManager(
          fetchFromLocal = {
              flow {
                  emit (
                      cacheProjectMapper.mapToDomainModel(database.projectDao().getSingleProject(id))
                  )
              }
          }, shouldFetchFromRemote = {
                fetchFromRemote
          },
          fetchFromRemote = {
              projectApiService.getSingleProject(id)
          },
          saveToLocalDB = {
              database.projectDao().insertProject(apiProjectMapper.mapFromApiToCacheModel(it.payload))
          }
      )

    override suspend fun createProject(project: DomainProject): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow {
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.projectDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                projectApiService.createProject(apiProjectMapper.mapFromDomainModel(project))
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.projectDao().deleteAllMyProjects()
                    it.payload.forEach {
                        database.projectDao()
                            .insertProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun updateProject(id: String, project: DomainProject): Flow<Resource<DomainProject>> =
        singleSourceManager(
            fetchFromLocal = {
                flow {
                    emit (
                        cacheProjectMapper.mapToDomainModel(database.projectDao().getSingleProject(id))
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                projectApiService.updateProject(id, apiProjectMapper.mapFromDomainModel(project))
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.projectDao().deleteAllMyProjects()
                    it.payload.forEach {
                        database.projectDao()
                            .insertProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun deleteProject(id: String): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.projectDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                projectApiService.deleteProject(id)
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.projectDao().deleteAllMyProjects()
                    it.payload.forEach {
                        database.projectDao()
                            .insertProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun addFavouriteProject(id: String): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.favouriteProjectDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                projectApiService.addFavouriteProject(id)
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.favouriteProjectDao().deleteAllProjects()
                    it.payload.forEach {
                        database.favouriteProjectDao()
                            .insertFavouriteProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun deleteFavouriteProject(id: String): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.favouriteProjectDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                projectApiService.deleteFavouriteProject(id)
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.favouriteProjectDao().deleteAllProjects()
                    it.payload.forEach {
                        database.favouriteProjectDao()
                            .insertFavouriteProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun getFavouriteProjects(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.favouriteProjectDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                    fetchFromRemote
            },
            fetchFromRemote = {
                projectApiService.getFavouriteProjects()
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.favouriteProjectDao().deleteAllProjects()
                    it.payload.forEach {
                        database.favouriteProjectDao()
                            .insertFavouriteProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )

    override suspend fun searchProject(text: String, fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>> =

        flow{
            emit (
                cacheProjectMapper.toDomainListMapper(database.searchProjectsDao().getAllProjects(text))
            )
        }
//        singleSourceManager(
//            fetchFromLocal = {
//                flow{
//                    emit (
//                        cacheProjectMapper.toDomainListMapper(database.searchProjectsDao().getAllProjects())
//                    )
//                }
//            }, shouldFetchFromRemote = {
//                fetchFromRemote
//            },
//            fetchFromRemote = {
//                projectApiService.searchProject(text)
//            },
//            saveToLocalDB = {
//                database.withTransaction {
//                    database.searchProjectsDao().deleteAllSearchedProjects()
//                    it.payload.forEach {
//                        database.searchProjectsDao()
//                            .insertProject(apiProjectMapper.mapFromApiToCacheModel(it))
//                    }
//                }
//            }
//        )

    override suspend fun projectsCreatedByMe(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheProjectMapper.toDomainListMapper(database.myProjectsDao().getAllProjects())
                    )
                }
            }, shouldFetchFromRemote = {
                    fetchFromRemote
            },
            fetchFromRemote = {
                projectApiService.projectsCreatedByMe()
            },
            saveToLocalDB = {
                database.withTransaction {
                    database.myProjectsDao().deleteAllMyProjects()
                    it.payload.forEach {
                        database.myProjectsDao()
                            .insertProject(apiProjectMapper.mapFromApiToCacheModel(it))
                    }
                }
            }
        )
    }





