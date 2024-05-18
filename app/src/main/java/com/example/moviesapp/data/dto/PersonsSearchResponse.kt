package com.example.moviesapp.data.dto

class PersonsSearchResponse(val searchType: String,
                            val expression: String,
                            val results: List<PersonDto>) : Response()
