package com.example.moviesapp.domain.models

import android.media.tv.PesResponse
import com.example.moviesapp.data.dto.PersonDto

class PersonsListResult(val persons: List<PersonDto>?, val codeResponse: Int?) {
}