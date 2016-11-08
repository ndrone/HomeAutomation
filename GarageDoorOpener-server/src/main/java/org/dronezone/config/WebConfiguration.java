package org.dronezone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    //one year
    private static final Integer CACHE_PERIOD = 31536000;

    private static final String[] NON_CACHE_PATHS = {"/index.html"};
    private static final String[] CACHE_PATHS     = {"/**"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        log.info("adding resource handlers");
        super.addResourceHandlers(registry);

        String[] locations = new String[]{"classpath:static/"};

        log.debug("preventing caching of these pathPatterns: {} for locations: {}", NON_CACHE_PATHS,
                locations);
        registry.addResourceHandler(NON_CACHE_PATHS).addResourceLocations(locations)
                .setCachePeriod(0).resourceChain(true);

        log.debug("setting pathPatterns: {} for locations: {} with a cache time: {} seconds",
                CACHE_PATHS, locations, CACHE_PERIOD);
        registry.addResourceHandler("/**").addResourceLocations(locations)
                .setCachePeriod(CACHE_PERIOD).resourceChain(true);
    }
}