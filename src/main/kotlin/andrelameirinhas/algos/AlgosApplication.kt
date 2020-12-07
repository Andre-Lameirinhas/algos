package andrelameirinhas.algos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlgosApplication

fun main(args: Array<String>) {
    runApplication<AlgosApplication>(*args)

    //val report = AlgValidator().validate(QuickSort())
    //report.printResults()
}
