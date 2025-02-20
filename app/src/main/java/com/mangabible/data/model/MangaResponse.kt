package com.mangabible.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KitsuResponse(
    val data: MangaData
)

@JsonClass(generateAdapter = true)
data class MangaData(
    val id: String,
    val type: String,
    val links: Links,
    val attributes: Attributes,
    val relationships: Relationships
)

@JsonClass(generateAdapter = true)
data class Links(
    val self: String,
    val related: String? = null
)

@JsonClass(generateAdapter = true)
data class Attributes(
    val createdAt: String,
    val updatedAt: String,
    val slug: String,
    val synopsis: String,
    val description: String,
    val coverImageTopOffset: Int,
    val titles: Titles,
    val canonicalTitle: String,
    val abbreviatedTitles: List<String>,
    val averageRating: String,
    val ratingFrequencies: RatingFrequencies,
    val userCount: Int,
    val favoritesCount: Int,
    val startDate: String,
    val endDate: String,
    val nextRelease: Any?,
    val popularityRank: Int,
    val ratingRank: Int,
    val ageRating: String,
    val ageRatingGuide: String,
    val subtype: String,
    val status: String,
    val tba: Any?,
    val posterImage: PosterImage,
    val coverImage: CoverImage,
    val chapterCount: Int,
    val volumeCount: Int,
    val serialization: String,
    val mangaType: String
)

@JsonClass(generateAdapter = true)
data class Titles(
    val ar: String?,
    val en: String?,
    @Json(name = "cs_cz") val csCz: String?,
    @Json(name = "en_jp") val enJp: String?,
    @Json(name = "es_es") val esEs: String?,
    @Json(name = "fa_ir") val faIr: String?,
    @Json(name = "fi_fi") val fiFi: String?,
    @Json(name = "fr_fr") val frFr: String?,
    @Json(name = "hr_hr") val hrHr: String?,
    @Json(name = "it_it") val itIt: String?,
    @Json(name = "ja_jp") val jaJp: String?,
    @Json(name = "ko_kr") val koKr: String?,
    @Json(name = "pt_br") val ptBr: String?,
    @Json(name = "ru_ru") val ruRu: String?,
    @Json(name = "th_th") val thTh: String?,
    @Json(name = "tr_tr") val trTr: String?,
    @Json(name = "vi_vn") val viVn: String?,
    @Json(name = "zh_cn") val zhCn: String?
)

@JsonClass(generateAdapter = true)
data class RatingFrequencies(
    @Json(name = "2") val two: String,
    @Json(name = "3") val three: String,
    @Json(name = "4") val four: String,
    @Json(name = "5") val five: String,
    @Json(name = "6") val six: String,
    @Json(name = "7") val seven: String,
    @Json(name = "8") val eight: String,
    @Json(name = "9") val nine: String,
    @Json(name = "10") val ten: String,
    @Json(name = "11") val eleven: String,
    @Json(name = "12") val twelve: String,
    @Json(name = "13") val thirteen: String,
    @Json(name = "14") val fourteen: String,
    @Json(name = "15") val fifteen: String,
    @Json(name = "16") val sixteen: String,
    @Json(name = "17") val seventeen: String,
    @Json(name = "18") val eighteen: String,
    @Json(name = "19") val nineteen: String,
    @Json(name = "20") val twenty: String
)

@JsonClass(generateAdapter = true)
data class PosterImage(
    val tiny: String,
    val large: String,
    val small: String,
    val medium: String,
    val original: String,
    val meta: Meta
)

@JsonClass(generateAdapter = true)
data class Meta(
    val dimensions: Dimensions
)

@JsonClass(generateAdapter = true)
data class Dimensions(
    val tiny: Tiny,
    val large: Large,
    val small: Small,
    val medium: Medium
)

@JsonClass(generateAdapter = true)
data class Tiny(
    val width: Any?,
    val height: Any?
)

@JsonClass(generateAdapter = true)
data class Large(
    val width: Any?,
    val height: Any?
)

@JsonClass(generateAdapter = true)
data class Small(
    val width: Any?,
    val height: Any?
)

@JsonClass(generateAdapter = true)
data class Medium(
    val width: Any?,
    val height: Any?
)

@JsonClass(generateAdapter = true)
data class CoverImage(
    val tiny: String,
    val large: String,
    val small: String,
    val original: String,
    val meta: CoverMeta? = null
)

@JsonClass(generateAdapter = true)
data class CoverMeta(
    val dimensions: CoverDimensions
)

@JsonClass(generateAdapter = true)
data class CoverDimensions(
    val tiny: CoverSize,
    val large: CoverSize,
    val small: CoverSize
)

@JsonClass(generateAdapter = true)
data class CoverSize(
    val width: Int,
    val height: Int
)

@JsonClass(generateAdapter = true)
data class Relationships(
    val genres: RelationshipLinks,
    val categories: RelationshipLinks,
    val castings: RelationshipLinks,
    val installments: RelationshipLinks,
    val mappings: RelationshipLinks,
    val reviews: RelationshipLinks,
    val mediaRelationships: RelationshipLinks,
    val characters: RelationshipLinks,
    val staff: RelationshipLinks,
    val productions: RelationshipLinks,
    val quotes: RelationshipLinks,
    val chapters: RelationshipLinks,
    val mangaCharacters: RelationshipLinks,
    val mangaStaff: RelationshipLinks
)

@JsonClass(generateAdapter = true)
data class RelationshipLinks(
    val links: Links
)