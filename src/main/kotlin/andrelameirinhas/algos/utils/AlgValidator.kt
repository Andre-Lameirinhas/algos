package andrelameirinhas.algos.utils

import andrelameirinhas.algos.algorithms.sorting.Sort
import andrelameirinhas.algos.services.ArrayBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class AlgValidator {
    private companion object {
        val arrayBuilder = ArrayBuilder()
        var evenArray = arrayBuilder.buildRandomArray(100, 0 until 50)
        var oddArray = arrayBuilder.buildRandomArray(101, 0 until 50)
        var oneArray = arrayBuilder.buildRandomArray(1, 0 until 50)
        var twoArray = arrayBuilder.buildRandomArray(2, 0 until 50)
        var threeArray = arrayBuilder.buildRandomArray(3, 0 until 50)
        var reversedArray = arrayBuilder.buildReversedArray(100)
        var fewUniqueArray = arrayBuilder.buildFewUniqueArray(100, 10)
        var sortedArray = arrayBuilder.buildSortedArray(100)
        var zerosArray = arrayBuilder.buildZerosArray(100)
        var emptyArray = arrayBuilder.buildEmptyArray()
        val arrays = listOf(
            NamedArray("even_array", evenArray),
            NamedArray("odd_array", oddArray),
            NamedArray("one_array", oneArray),
            NamedArray("two_array", twoArray),
            NamedArray("three_array", threeArray),
            NamedArray("reversed_array", reversedArray),
            NamedArray("few_unique_array", fewUniqueArray),
            NamedArray("sorted_array", sortedArray),
            NamedArray("zeros_array", zerosArray),
            NamedArray("empty_array", emptyArray)
        )
    }

    fun validate(alg: Sort): ValidationReport {

        val validationJobs = arrays.map {
            GlobalScope.async {
                alg.sort(it.array)
                ValidationDetail(it.name, it.array.isSorted())
            }
        }
        val validationResults = mutableListOf<ValidationDetail>()
        runBlocking {
            validationJobs.forEach {
                validationResults.add(it.await())
            }
        }
        val valid = !validationResults.any {
            !it.sorted
        }
        return ValidationReport(alg.name, valid, validationResults.toList())
    }
}

data class NamedArray(val name: String, val array: IntArray)

data class ValidationDetail(val arrayName: String, val sorted: Boolean)

data class ValidationReport(val algName: String, val valid: Boolean, val details: List<ValidationDetail>) {

    fun printResults() {
        println("Report subject: $algName")
        println("Validation result: ${if (valid) "SUCCESS" else "FAILURE"}")
        println("Details:")
        details.forEach {
            println("[${if (it.sorted) "✅" else "❌"}] ${it.arrayName}")
        }
    }
}
