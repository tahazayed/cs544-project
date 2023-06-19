// package cs544.configuration.Filters;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.web.filter.GenericFilterBean;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// public class JwtFilter extends OncePerRequestFilter {
//     @Override
//     protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         final HttpServletRequest request = (HttpServletRequest) servletRequest;
//         final HttpServletResponse response = (HttpServletResponse) servletResponse;
//         final String authHeader = request.getHeader("authorization");
//         if ("POST".equals(request.getMethod())) {
//             response.setStatus(HttpServletResponse.SC_OK);
//             filterChain.doFilter(request, response);
//         }
//         if ("GET".equals(request.getMethod())) {
//             if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                 throw new ServletException("An exception occurred");
//             }
//         }
//         // if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//         //     throw new ServletException("An exception occurred");
//         // }

//         //    if ("GET".equals(request.getMethod())) {
//         //        response.setStatus(HttpServletResponse.SC_OK);
//         //        filterChain.doFilter(request, response);
//         //    } else {
//         //        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//         //            throw new ServletException("An exception occurred");
//         //        }  
//         //    }
//         //    final String token = authHeader.substring(7);
//         //    Claims claims = Jwts.parser().setSigningKey("enterprise").parseClaimsJws(token).getBody();
//         //    request.setAttribute("claims", claims);
//         //    request.setAttribute("blog", servletRequest.getParameter("id"));
//         //    filterChain.doFilter(request, response);
//     }
//     // @Override
//     // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//     //         jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
//     //     // TODO Auto-generated method stub
//     //     throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
//     // }
// }