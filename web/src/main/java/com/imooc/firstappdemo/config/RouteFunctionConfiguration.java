package com.imooc.firstappdemo.config;

import com.imooc.firstappdemo.repository.UserRepository;
import com.imooc.firstappdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * 路由器函数 配置
 */
@Configuration
public class RouteFunctionConfiguration {
    /**
     * Servlet
     * 请求接口：ServletRequest 或者 HttpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring 5.0 重新定义了服务端请求和响应接口
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 既可以支持Servlet规范，也可以支持自定义，比如 Netty (Web Server)
     * 以本例：
     * 定义GET请求，并且返回所有用户对象 URI：/person/find/all
     * Flux 是 0-N个对象集合
     * Mono 是 0-1个对象集合
     * Reactive 中的Flux 或者 Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux 或者 Mono 都是 Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    // 返回所有的用户对象
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }
}