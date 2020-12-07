package andrelameirinhas.algos.controllers

import andrelameirinhas.algos.converters.SortingRequestMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    // TODO Find out why the custom converter isn't being used
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(SortingRequestMessageConverter())
        super.configureMessageConverters(converters)
    }
}