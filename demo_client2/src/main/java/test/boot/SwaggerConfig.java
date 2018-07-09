package test.boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String packageName = "test.boot.controller";

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// .host("http://localhost:8080/swagger-ui.html#/")
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(packageName))
				.paths(PathSelectors.any()).build().globalOperationParameters(setHeaderToken());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("demo-api文档").description("测试2").version("1.0").build();
	}

	private List<Parameter> setHeaderToken() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(tokenPar.build());
		return pars;
	}
}
