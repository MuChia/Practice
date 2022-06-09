package priv.muchia.practice.model

data class BaseData <T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)
