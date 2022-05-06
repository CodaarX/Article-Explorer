package com.decagon.edconnect.data.datasources.remoteSource

import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.utils.GenericResponseClass
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectApiService {
    @GET("api/project/get/all")
    suspend fun getAllProjects(): GenericResponseClass<List<DomainProject>>

    @GET("api/project/get/{project_id}")
    suspend fun getSingleProject(@Path("project_id") project_id: String): GenericResponseClass<ApiProject>

    @POST("api/project/create")
    suspend fun createProject(project: ApiProject): GenericResponseClass<List<ApiProject>>

    @POST("api/project/edit/{projectId}")
    suspend fun updateProject(
        @Path("projectId") projectId: String,
        @Body project: ApiProject
    ): GenericResponseClass<List<ApiProject>>

    @POST("api/project/delete/{project_id}")
    suspend fun deleteProject(
        @Path("projectId") projectId: String
    ): GenericResponseClass<List<ApiProject>>

    @POST("api/project/favourites/add/{projectId}")
    suspend fun addFavouriteProject(
        @Path("projectId") projectId: String
    ): GenericResponseClass<List<ApiProject>>

    @POST("api/project/favourites/delete/{projectId}")
    suspend fun deleteFavouriteProject(
        @Path("projectId") projectId: String
    ): GenericResponseClass<List<ApiProject>>

    @GET("api/project/favourites/get")
    suspend fun getFavouriteProjects(): GenericResponseClass<List<ApiProject>>

    @POST("api/project/search/{text}")
    suspend fun searchProject(
        @Path("text") text: String,
    ): GenericResponseClass<List<ApiProject>>

    @GET("api/projects/mine")
    suspend fun projectsCreatedByMe(): GenericResponseClass<List<ApiProject>>
}
