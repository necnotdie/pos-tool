package pos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @���� �Ŷ�
 * @���� ��annotation�ڳ������ʱ���в�������������pos.xml�����ļ��м��ص���ķ�������������Ϊ�޲η���������ֵ��������κβ����͸�ֵ��
 * @��ز��� ����ע�ķ������Խ������²���������ر�ʱ�ر����ݿ⡣
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Close {
	
}
