//package com.kpi.system.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
//import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableOpenApi
//public class SwaggerConfig {
//    // 创建Docket存入容器，Docket代表一个接口文档
//    @Bean
//    public Docket webApiConfig() {
//
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                // 创建接口文档的具体信息
//                .apiInfo(webApiInfo())
//                // 创建选择器，控制哪些接口被加入文档
//                .select()
//                // 指定@ApiOperation标注的接口被加入文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .build();
//    }
//
//    // 创建接口文档的具体信息，会显示在接口文档页面中
//    private ApiInfo webApiInfo() {
//        return new ApiInfoBuilder()
//                // 文档标题
//                .title("后台接口管理")
//                // 文档描述
//                .description("后台接口文档")
//                // 版本
//                .version("1.0")
//                // 联系人信息
//                // 版权
//                .license("CHINIAN")
//                // 版权地址
//                .build();
//
//    }
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
//}
