package andrelameirinhas.algos.converters

import andrelameirinhas.algos.dto.SortingRequest
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter

class SortingRequestMessageConverter : HttpMessageConverter<SortingRequest> {
    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean {
        TODO("Not yet implemented")
    }

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return clazz == String::class
    }

    override fun getSupportedMediaTypes(): MutableList<MediaType> {
        return mutableListOf(MediaType.APPLICATION_JSON)
    }

    override fun read(clazz: Class<out SortingRequest>, inputMessage: HttpInputMessage): SortingRequest {
        TODO("Not yet implemented")
    }

    override fun write(t: SortingRequest, contentType: MediaType?, outputMessage: HttpOutputMessage) {
        TODO("Not yet implemented")
    }


}