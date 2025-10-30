package edu.javieh.simpsonsapi.application.features.data.remote.api

import edu.javieh.simpsonsapi.application.features.domain.Simpson

fun SimpsonApiModel.toModel(): Simpson{
    return Simpson(
                this.id,
            this.name,
        this.occupation,
                    this.phrases,
        "https://cdn.thesimpsonsapi.com/500" + this.imageUrl
    )
}