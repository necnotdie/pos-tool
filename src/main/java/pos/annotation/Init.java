package pos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @���� �Ŷ�
 * @���� ��annotation��window���ظ���������ʱ��Ҫ���صķ�������������Ϊ�޲η���������ֵ��������κβ����͸�ֵ��
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {
	
}
