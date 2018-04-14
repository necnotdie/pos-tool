package pos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @作者 张栋
 * @描述 此annotation在程序结束时进行操作，用于修饰pos.xml配置文件中加载的类的方法，方法必须为无参方法，返回值不会进行任何操作和赋值。
 * @相关操作 被标注的方法可以进行如下操作：程序关闭时关闭数据库。
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Close {
	
}
