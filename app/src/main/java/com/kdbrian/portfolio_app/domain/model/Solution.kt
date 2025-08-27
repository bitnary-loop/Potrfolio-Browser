package com.kdbrian.portfolio_app.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Solution(
    val id: String,
    val name: String,
    val owner: String,
    val link: String,
    val logo: String,
    val infoFile: String,
    val addedOn: Long = System.currentTimeMillis()
){
    //make constructor private
    private constructor(
        id: String,
        name: String,
        owner: String,
        link: String,
        logo: String,
        infoFile: String
    ) : this(
        id = id,
        name = name,
        owner = owner,
        link = link,
        logo = logo,
        infoFile = infoFile,
        addedOn = System.currentTimeMillis()
    )

    class Builder{
        private var id: String = ""
        private var name: String = ""
        private var owner: String = ""
        private var link: String = ""
        private var logo: String = ""
        private var infoFile: String = ""

        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun owner(owner: String) = apply { this.owner = owner }
        fun link(link: String) = apply { this.link = link }
        fun logo(logo: String) = apply { this.logo = logo }
        fun infoFile(infoFile: String) = apply { this.infoFile = infoFile }

        fun build() = Solution(
            id = id,
            name = name,
            owner = owner,
            link = link,
            logo = logo,
            infoFile = infoFile
        )

    }


}
