package com.authority.annotation;  
  
import java.lang.annotation.*;  
  
/** 
 *自定义注解 拦截Controller 
 */  
  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
  
	String module()  default "";  //模块名称  eg:系统管理
	String methods()  default "";  //操作行为 eg:用户管理-新增用户
    String description()  default "";  //操作结果
  
  
}  
  
