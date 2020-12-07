package andrelameirinhas.algos.algorithms.sorting

interface Sort {
    val name: String

    suspend fun sort(arr: IntArray)
}
