package priv.muchia.newwork_core

data class BaseData <T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)
