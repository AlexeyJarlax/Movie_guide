package com.example.moviesapp.data.dto

class MovieCastResponse(
    val actors: List<ActorResponse>,
    val directors: DirectorsResponse,
    val fullTitle: String,
    val imDbId: String,
    val others: List<OtherResponse>,
    val title: String,
    val writers: WritersResponse
) : Response()

class ActorResponse(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)

class DirectorsResponse(
    val items: List<CastItemResponse>,
    val job: String
)

class OtherResponse(
    val items: List<CastItemResponse>,
    val job: String
)

class WritersResponse(
    val items: List<CastItemResponse>
)

class CastItemResponse(
    val description: String,
    val id: String,
    val name: String
)