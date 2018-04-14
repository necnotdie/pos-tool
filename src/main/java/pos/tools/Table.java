package pos.tools;

import java.awt.Component;

/**
 * 
 * @作者 张栋
 * @描述 实现此接口，进行功能加载和数据交换
 *
 */
public interface Table {
	/**
	 * @描述 此方法用于自行创建各个插件想要展示的内容
	 * @返回 返回值为一个Component对象，用于整个窗体的加载。
	 */
	public Component view();
}
