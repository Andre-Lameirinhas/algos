package andrelameirinhas.algos.algorithms.sorting

import andrelameirinhas.algos.utils.swapValues

class BubbleSort : Sort {
    override val name = "BubbleSort"

    override suspend fun sort(arr: IntArray) {
        for (lap in 1 until arr.size) {
            for (index in 0 until arr.size - lap) {
                if (arr[index] > arr[index + 1]) {
                    arr.swapValues(index, index + 1)
                }
            }
        }
    }
}
