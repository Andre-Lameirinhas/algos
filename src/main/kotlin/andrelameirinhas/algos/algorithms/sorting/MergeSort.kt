package andrelameirinhas.algos.algorithms.sorting

class MergeSort : Sort {
    override val name = "MergeSort"

    override suspend fun sort(arr: IntArray) {
        if (arr.size < 2) return

        val midpoint = arr.size / 2
        val left = arr.copyOfRange(0, midpoint)
        val right = arr.copyOfRange(midpoint, arr.size)
        sort(left)
        sort(right)

        var arrIndex = 0
        var leftIndex = 0
        var rightIndex = 0

        while (leftIndex < left.size && rightIndex < right.size) {
            val leftValue = left[leftIndex]
            val rightValue = right[rightIndex]
            if (leftValue < rightValue) {
                arr[arrIndex++] = leftValue
                leftIndex++
            } else {
                arr[arrIndex++] = rightValue
                rightIndex++
            }
        }
        while (leftIndex < left.size) {
            arr[arrIndex++] = left[leftIndex++]
        }
        while (rightIndex < right.size) {
            arr[arrIndex++] = right[rightIndex++]
        }
    }
}
