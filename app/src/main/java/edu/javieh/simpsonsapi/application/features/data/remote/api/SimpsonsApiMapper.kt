package edu.javieh.simpsonsapi.application.features.data.remote.api

import edu.javieh.simpsonsapi.application.features.domain.Simpson

fun CharacterApiModel.toModel(): Simpson {
    return Simpson(
        this.id,
        this.name,
        this.occupation,
        this.phrases,
        "https://cdn.thesimpsonsapi.com/200" + this.imageUrl
    )
}