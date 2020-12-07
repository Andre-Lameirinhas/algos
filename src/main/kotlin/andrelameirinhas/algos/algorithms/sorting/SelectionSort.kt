package andrelameirinhas.algos.algorithms.sorting

class SelectionSort : Sort {
    override val name = "SelectionSort"

    override suspend fun sort(arr: IntArray) {
        var minIndex = -1

        for (lap in 0 until arr.size - 1) {
            var min = Int.MAX_VALUE
            for (index in lap until arr.size) {
                val current = arr[index]
                if (min > current) {
                    min = current
                    minIndex = index
                }
            }
            if (lap != minIndex) {
                arr[minIndex] = arr[lap]
                arr[lap] = min
            }
        }
    }
}
