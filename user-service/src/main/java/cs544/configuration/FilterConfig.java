// package cs544.configuration;

// import cs544.configuration.Filters.JwtFilter;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.configuration;

// @configuration
// public class FilterConfig {
//     @Bean
//     public FilterRegistrationBean jwtFilter() {
//         FilterRegistrationBean filter= new FilterRegistrationBean();
//         filter.setFilter(new JwtFilter());

//         filter.addInitParameter("/api/users/", "POST");
//         // Set the URL patterns that need to be restricted with their corresponding HTTP methods
//         // filter.addUrlPatterns("/api/users/*");;
//         // filter.addInitParameter("/api/user", "POST");
//         return filter;
//     }
// }
