package andrelameirinhas.algos.algorithms.sorting

import andrelameirinhas.algos.utils.isSorted
import andrelameirinhas.algos.utils.swapValues
import kotlin.random.Random

class RandomSort : Sort {
    override val name = "RandomSort"

    override suspend fun sort(arr: IntArray) {
        while (!arr.isSorted()) {
            val index1 = Random.nextInt(arr.size)
            val index2 = Random.nextInt(arr.size)
            arr.swapValues(index1, index2)
        }
    }
}
