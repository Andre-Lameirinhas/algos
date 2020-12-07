package andrelameirinhas.algos.controllers

import andrelameirinhas.algos.dto.SortingRequest
import andrelameirinhas.algos.services.AlgRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sorting")
class SortingController {

    @Autowired
    lateinit var algRunner: AlgRunner

    @PostMapping("/gen")
    fun generateSortReport(
        @RequestBody sortingRequest: SortingRequest
    ): ResponseEntity<Any> {

        if (sortingRequest.arraySize < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Array size must be greater than zero.")
        }

        algRunner.run(
                arrayType = sortingRequest.arrayType,
                arraySize = sortingRequest.arraySize,
                algType = sortingRequest.algType
        )
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sort started successfully.")
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalField(e: HttpMessageNotReadableException) = "Invalid or missing field."
}
