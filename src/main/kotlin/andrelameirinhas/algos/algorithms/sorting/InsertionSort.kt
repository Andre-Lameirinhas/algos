package andrelameirinhas.algos.algorithms.sorting

class InsertionSort : Sort {
    override val name = "InsertionSort"

    override suspend fun sort(arr: IntArray) {
        for (index in 1 until arr.size) {
            val current = arr[index]
            for (backIndex in index downTo 1) {
                val previous = arr[backIndex - 1]
                if (current < previous) {
                    arr[backIndex] = previous
                    arr[backIndex - 1] = current
                } else break
            }
        }
    }
}
