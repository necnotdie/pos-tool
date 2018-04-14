package pos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @作者 张栋
 * @描述 此annotation在window加载各个组件完毕时需要加载的方法，方法必须为无参方法，返回值不会进行任何操作和赋值。
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {
	
}
