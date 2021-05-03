package ee.bcs.valiit.solution.webSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtTokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String token=resolveToken((HttpServletRequest) servletRequest);
        if(token!=null){
            Authentication authentication=validateToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    private String resolveToken(HttpServletRequest servletRequest){
        String attribute= servletRequest.getHeader("Authorization");
        if(attribute==null){
            return null;
        }
        if(attribute.startsWith("Bearer ")){
            return attribute.substring(7);
        }
        return attribute;
    }

    private Authentication validateToken(String token){
        Claims claims= Jwts.parser().setSigningKey("c2VjcmV0").parseClaimsJws(token).getBody();
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails=new User((String) claims.get("username"), "", authorityList);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }
}
