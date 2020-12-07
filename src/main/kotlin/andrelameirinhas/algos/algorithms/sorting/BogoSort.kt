package andrelameirinhas.algos.algorithms.sorting

import andrelameirinhas.algos.utils.isSorted
import andrelameirinhas.algos.utils.shuffle

class BogoSort : Sort {
    override val name = "BogoSort"

    override suspend fun sort(arr: IntArray) {
        while (!arr.isSorted()) arr.shuffle()
    }
}
