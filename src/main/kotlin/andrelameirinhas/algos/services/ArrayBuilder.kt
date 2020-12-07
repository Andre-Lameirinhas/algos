package andrelameirinhas.algos.services

import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class ArrayBuilder {
    fun buildRandomArray(size: Int, range: IntRange): IntArray {
        return IntArray(size) { Random.nextInt(range) }
    }

    fun buildReversedArray(size: Int): IntArray {
        return IntArray(size) { size - it }
    }

    fun buildFewUniqueArray(size: Int, unique: Int): IntArray {
        val uniques = IntArray(unique) { it }
        return IntArray(size) { uniques[Random.nextInt(unique)] }
    }

    fun buildSortedArray(size: Int): IntArray {
        return IntArray(size) { it }
    }

    fun buildZerosArray(size: Int): IntArray {
        return IntArray(size)
    }

    fun buildEmptyArray(): IntArray {
        return IntArray(0)
    }
}
