package andrelameirinhas.algos.dto

import andrelameirinhas.algos.services.AlgType
import andrelameirinhas.algos.services.ArrayType
import com.fasterxml.jackson.annotation.JsonProperty

data class SortingRequest(

    @get:JsonProperty("array_type")
    val arrayType: ArrayType,

    @get:JsonProperty("array_size")
    val arraySize: Int,

    @get:JsonProperty("alg_type")
    val algType: AlgType
)
