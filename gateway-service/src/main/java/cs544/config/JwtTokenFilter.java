package cs544.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import cs544.model.Roles;
import cs544.model.User;
import cs544.model.enums.ERoles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import javax.management.relation.Role;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "enterprise";
    // private final JwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!hasAuthorizationBearer(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = getAccessToken(servletRequest);

        setAuthenticationContext(token, servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);


        // UserDetails userDetails = getUserDetails(token);
        // final HttpServletRequest request = (HttpServletRequest) servletRequest;
        // final HttpServletResponse response = (HttpServletResponse) servletResponse;
        // final String authHeader = request.getHeader("authorization");
        // if ("POST".equals(request.getMethod())) {
        //     response.setStatus(HttpServletResponse.SC_OK);
        //     filterChain.doFilter(request, response);
        // }
        // if ("GET".equals(request.getMethod())) {
        //     if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //         throw new ServletException("An exception occurred");
        //     }
        // }
        // if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //     throw new ServletException("An exception occurred");
        // }

        //    if ("GET".equals(request.getMethod())) {
        //        response.setStatus(HttpServletResponse.SC_OK);
        //        filterChain.doFilter(request, response);
        //    } else {
        //        if(authHeader == null || !authHeader.startsWith("Bearer ")){
        //            throw new ServletException("An exception occurred");
        //        }  
        //    }
        //    final String token = authHeader.substring(7);
        //    Claims claims = Jwts.parser().setSigningKey("enterprise").parseClaimsJws(token).getBody();
        //    request.setAttribute("claims", claims);
        //    request.setAttribute("blog", servletRequest.getParameter("id"));
        //    filterChain.doFilter(request, response);
    }


    //header if token if it return false, token is not valid
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }

        return true;
    }


    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            return false;
        }
    }


    private UserDetails getUserDetails(String token) {

        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        LinkedHashMap < ? , ? > userMap = (LinkedHashMap < ? , ? > ) claims.get("user");

        Long id = ((Integer) userMap.get("id")).longValue();
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        // List < Roles > roles = (List < Roles > ) userMap.get("roles");

        List < LinkedHashMap < String, String >> rolesMap = (List < LinkedHashMap < String, String >> ) userMap.get("roles");
        List < String > roles = rolesMap.stream()
            .map(roleMap -> roleMap.get("role"))
            .collect(Collectors.toList());

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        
        List<Roles> userRoles = new ArrayList<>();

        for (String roleName : roles) {
            Roles role = new Roles();
            ERoles roleEnum = Arrays.stream(ERoles.values())
            .filter(enumValue -> enumValue.name().equals(roleName))
            .findFirst()
            .orElse(null);
            role.setRole(roleEnum);
            userRoles.add(role);
        }

        user.setRoles(userRoles);
        // user.setId((Long) claims.get("userId"));
        // user.setUsername((String) claims.get("username"));
        // user.setRoles((List < Roles > ) claims.get("roles"));

        return user;
    }
    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
    //         jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    // }
}