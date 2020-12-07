package andrelameirinhas.algos.algorithms.sorting

import andrelameirinhas.algos.utils.swapValues
import kotlin.random.Random

class QuickSort(val pivotType: PivotType = PivotType.LAST) : Sort {
    override val name = "QuickSort[$pivotType]"

    var count = 0

    enum class PivotType {
        FIRST, LAST, RANDOM, MIDDLE, MEDIAN_OF_THREE
    }

    override suspend fun sort(arr: IntArray) {
        quick(arr, 0, arr.size)
    }

    private fun quick(arr: IntArray, low: Int, high: Int) {
        if (low == high || low == high - 1) return
        count++

        var pivotIndex = when (pivotType) {
            PivotType.FIRST -> low
            PivotType.LAST -> high - 1
            PivotType.RANDOM -> Random.nextInt(low, high)
            PivotType.MIDDLE -> (low + high) / 2
            PivotType.MEDIAN_OF_THREE -> TODO("Implement median of three")
        }

        arr.swapValues(pivotIndex, high - 1)
        pivotIndex = high - 1

        var pivotMidpoint = low

        for (index in low until pivotIndex) {
            if (arr[index] < arr[pivotIndex]) {
                arr.swapValues(index, pivotMidpoint)
                pivotMidpoint++
            }
        }
        arr.swapValues(pivotIndex, pivotMidpoint)

        quick(arr, low, pivotMidpoint)
        quick(arr, pivotMidpoint + 1, high)
    }
}
