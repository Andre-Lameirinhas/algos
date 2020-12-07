package andrelameirinhas.algos.services

import andrelameirinhas.algos.algorithms.sorting.BubbleSort
import andrelameirinhas.algos.algorithms.sorting.InsertionSort
import andrelameirinhas.algos.algorithms.sorting.MergeSort
import andrelameirinhas.algos.algorithms.sorting.QuickSort
import andrelameirinhas.algos.algorithms.sorting.SelectionSort
import andrelameirinhas.algos.algorithms.sorting.Sort
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

enum class AlgType {
    FAST, SLOW, ALL, QUICK
}

enum class ArrayType {
    RANDOM, REVERSED, FEW_UNIQUE
}

data class SortResult(val algName: String, val success: Boolean, val duration: Long? = null, val message: String? = null)

@Service
class AlgRunner {

    @Autowired
    lateinit var arrayBuilder: ArrayBuilder

    private val logger = LoggerFactory.getLogger(this::class.java)

    private companion object {
        val fastAlgs = listOf(QuickSort(), MergeSort())
        val slowAlgs = listOf(InsertionSort(), SelectionSort(), BubbleSort())
        val allAlgs = fastAlgs + slowAlgs
        val quickSorts = listOf(
            QuickSort(QuickSort.PivotType.FIRST),
            QuickSort(QuickSort.PivotType.LAST),
            QuickSort(QuickSort.PivotType.RANDOM),
            QuickSort(QuickSort.PivotType.MIDDLE)
        )
    }

    fun run(arrayType: ArrayType, arraySize: Int, algType: AlgType) {

        val arrayToSort = when (arrayType) {
            ArrayType.RANDOM -> arrayBuilder.buildRandomArray(arraySize, 0 until arraySize)
            ArrayType.REVERSED -> arrayBuilder.buildReversedArray(arraySize)
            ArrayType.FEW_UNIQUE -> arrayBuilder.buildFewUniqueArray(arraySize, sqrt(arraySize.toFloat()).toInt())
        }

        val algList = when (algType) {
            AlgType.FAST -> fastAlgs
            AlgType.SLOW -> slowAlgs
            AlgType.ALL -> allAlgs
            AlgType.QUICK -> quickSorts
        }

        logger.info("Running $algType algorithms with $arrayType array of size $arraySize")

        val sortResults = launchJobs(algList, arrayToSort)
        println("Sort Results:")
        for (result in sortResults) {
            println(result)
        }
    }

    private fun launchJobs(algList: List<Sort>, arrayToSort: IntArray): List<SortResult> {
        val sortResults = mutableListOf<SortResult>()

        val sortJobs = algList.map {
            GlobalScope.async {
                val arrayCopy = arrayToSort.copyOf()
                try {
                    val duration = measureTimeMillis {
                        it.sort(arrayCopy)
                    }
                    println("${it.name} -> $duration ms")
                    SortResult(
                        algName = it.name,
                        success = true,
                        duration = duration)
                } catch (e: Error) {
                    println("${it.name} -> $e")
                    SortResult(
                        algName = it.name,
                        success = false,
                        message = e.toString()
                    )
                }
            }
        }
        val totalDuration = measureTimeMillis {
            runBlocking {
                sortJobs.map {
                    sortResults.add(it.await())
                }
            }
        }

        println("---------------------------------")
        println("Total duration: $totalDuration ms")

        return sortResults
    }

    private fun launchJobsWithoutReturn(algList: List<Sort>, arrayToSort: IntArray) = GlobalScope.launch {
        algList.map {
            launch {
                val arrayCopy = arrayToSort.copyOf()
                try {
                    val duration = measureTimeMillis {
                        it.sort(arrayCopy)
                    }
                    println("${it.name} -> $duration ms")
                } catch (e: Error) {
                    println("${it.name} -> $e")
                }
            }
        }
    }
}
