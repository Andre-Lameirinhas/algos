package andrelameirinhas.algos.utils

import kotlin.random.Random

fun IntArray.prettyString(): String {
    return "[${this.joinToString(", ")}]"
}

fun IntArray.shuffle() {
    for (index in this.indices) {
        val randomIndex = Random.nextInt(this.size)
        this.swapValues(index, randomIndex)
    }
}

fun IntArray.isSorted(): Boolean {
    val it = this.iterator()

    if (it.hasNext()) {
        var previous = it.next()
        for (current in it) {
            if (previous > current) return false
            previous = current
        }
    }
    return true
}

fun IntArray.swapValues(indexA: Int, indexB: Int) {
    val valueA = this[indexA]
    this[indexA] = this[indexB]
    this[indexB] = valueA
}
