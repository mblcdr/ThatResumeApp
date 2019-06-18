package com.samsaz.thatresumeapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AboutMe(
    val name: String, val occupation: String, val imageLink: String?,
    val aboutMe: String, val socials: Array<Social>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AboutMe

        if (name != other.name) return false
        if (occupation != other.occupation) return false
        if (imageLink != other.imageLink) return false
        if (aboutMe != other.aboutMe) return false
        if (socials != null) {
            if (other.socials == null) return false
            if (!socials.contentEquals(other.socials)) return false
        } else if (other.socials != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + occupation.hashCode()
        result = 31 * result + (imageLink?.hashCode() ?: 0)
        result = 31 * result + aboutMe.hashCode()
        result = 31 * result + (socials?.contentHashCode() ?: 0)
        return result
    }

}