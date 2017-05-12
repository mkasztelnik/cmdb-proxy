package pl.cyfronet.fid.cmdbproxy.web.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.RequestMatchResult;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import pl.cyfronet.fid.cmdbproxy.pdp.Pdp;

@Component
@Order(3)
public class AuthorizationFilter extends CmdbCrudAwareFilter {

    @Value("${proxy.cmdb-crud.servlet_url}")
    private String cmdbCrudUrl;

    @Autowired
    private Pdp pdp;

    private RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (isMethod(request, HttpMethod.GET)) {
            chain.doFilter(request, response);
        } else if (cmdbCrudRequest(request)) {
            if (isCreate(request)) {
                doCreateFilter(request, response, chain);
            } else if(isMethod(request, HttpMethod.PUT, HttpMethod.DELETE)) {
                doManageFilter(request, response, chain);
            } else {
                permissionDenied(response);
            }
        } else {
            permissionDenied(response);
        }
    }

    private void doCreateFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (mapping.match(request, "/") == null && mapping.match(request, "/{id}") == null) {
            badRequest(response);
        } else {
            if (canCreate(request)) {
                chain.doFilter(request, response);
            } else {
                permissionDenied(response);
            }
        }
    }

    private void doManageFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String itemId = getItemId(request);
        if (itemId != null) {
            if (canManage(itemId)) {
                chain.doFilter(request, response);
            } else {
                permissionDenied(response);
            }
        } else {
            badRequest(response);
        }
    }

    private String getItemId(HttpServletRequest request) {
        RequestMatchResult match = mapping.match(request, "/{id}");
        if (match != null) {
            Map<String, String> variables = match.extractUriTemplateVariables();
            return variables.get("id");
        } else {
            return null;
        }
    }

    private boolean isCreate(HttpServletRequest httpRequest) {
        return isMethod(httpRequest, HttpMethod.POST, HttpMethod.PUT) && isCreateRequestBody(httpRequest);
    }

    private boolean canCreate(ServletRequest request) {
        try {
            return pdp.canCreate(getCurrentUser(), request.getInputStream());
        } catch (IOException e) {
            return false;
        }
    }

    private boolean canManage(String itemId) {
        return pdp.canManage(getCurrentUser(), itemId);
    }

    private boolean isCreateRequestBody(ServletRequest request) {
        try {
            return !IOUtils.toString(request.getInputStream(), Charset.defaultCharset()).contains("\"_rev\":");
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isMethod(HttpServletRequest request, HttpMethod... methods) {
        return Arrays.asList(methods).stream().anyMatch(m -> m.toString().equals(request.getMethod()));
    }

    private void permissionDenied(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }

    private void badRequest(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
    }

    private String getCurrentUser() {
        return (String) ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
                .getPrincipal();
    }
}