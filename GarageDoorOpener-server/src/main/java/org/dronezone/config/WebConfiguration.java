package org.dronezone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web MVC Configuration
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        super.addResourceHandlers(registry);

        String[] locations = new String[]{"classpath:static/"};
        // Max cache period allowed
        Integer cachePeriod = 31556926;

        // prevent caching index.html, login.html, relogin.html
        registry.addResourceHandler("/index.html").addResourceLocations(locations).setCachePeriod(0)
                .resourceChain(true);

        // Everything else cache for as long as possible if its not local
        registry.addResourceHandler("/**").addResourceLocations(locations)
                .setCachePeriod(cachePeriod).resourceChain(true);
    }
}