//package com.wemirr.framework.security.client.exception;
//
//import com.wemirr.framework.commons.exception.CheckedException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
//import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//
///**
// * @author Levin
// */
//@Slf4j
//@Component
//public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
//
//
//    @Override
//    public ResponseEntity<OAuth2Exception> translate(Exception e) {
//        if (e.getCause() != null && e.getCause() instanceof CheckedException) {
//            CheckedException exception = (CheckedException) e.getCause();
//            int code = exception.getCode();
//            String message = e.getMessage();
//            HttpHeaders headers = new HttpHeaders();
//            headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
//            headers.set(HttpHeaders.PRAGMA, "no-cache");
//            return new ResponseEntity<>(new Auth2Exception(message, code), headers,
//                    HttpStatus.valueOf(code));
//        }
//        if (e instanceof AuthenticationException) {
//            return handleOAuth2Exception(new UnauthorizedException(e.getMessage()));
//        }
//        if (e instanceof AccessDeniedException) {
//            return handleOAuth2Exception(new ForbiddenException(e.getMessage()));
//        }
//        if (e instanceof InvalidGrantException) {
//            return handleOAuth2Exception(new InvalidException(e.getMessage()));
//        }
//        if (e instanceof HttpRequestMethodNotSupportedException) {
//            return handleOAuth2Exception(new MethodNotAllowedException(e.getMessage()));
//        }
//        if (e instanceof OAuth2Exception) {
//            return handleOAuth2Exception(new OAuth2Exception(e.getMessage(), e));
//        }
//        return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
//
//    }
//
//    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
//        log.warn("[异常信息] - [{}]", e.getMessage());
//        int status = e.getHttpErrorCode();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
//        headers.set(HttpHeaders.PRAGMA, "no-cache");
//        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
//            headers.set(HttpHeaders.WWW_AUTHENTICATE, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
//        }
//        // 客户端异常直接返回客户端,不然无法解析
//        if (e instanceof ClientAuthenticationException) {
//            return new ResponseEntity<>(e, headers, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new Auth2Exception(e.getOAuth2ErrorCode(), e.getHttpErrorCode()), headers, HttpStatus.OK);
//    }
//}
