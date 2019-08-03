package com.fatiny.cardloginplus.common.json;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fatiny.cardloginplus.util.DateUtils;
import com.google.common.collect.Lists;

/**
 * springboot 默认使用的是jackson作为json解析工具, 因为不擅长使用jackson,
 * 所以增加这个配置把json解析工具替换成fastjson
 * 
 * @auth Jeremy
 * @date 2018年9月21日下午2:39:44
 */
//@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {
	
	/**
     * 完全覆盖Jackson
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(//
		// SerializerFeature.PrettyFormat //
		// , SerializerFeature.WriteNullStringAsEmpty //
		// , SerializerFeature.WriteNullNumberAsZero //
		// , SerializerFeature.WriteNullListAsEmpty //
		// , SerializerFeature.WriteMapNullValue//
		);
        fastJsonConfig.setDateFormat(DateUtils.FORMAT_FULL);
        List<MediaType> fastMediaTypes = Lists.newArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConvert.setSupportedMediaTypes(fastMediaTypes);
		fastConvert.setDefaultCharset(Charset.defaultCharset());
		fastConvert.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConvert);
    }

}
