package pos.summer;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import pos.annotation.Resource;

public final class Summer {
	private static Summer summer;
	private List<String> classList = new ArrayList<String>();
	private Map<Class<?>, Map<String, Object>> SummerObject = new LinkedHashMap<Class<?>, Map<String, Object>>();
	public Map<Class<?>, Map<String, Object>> getSummerObject() {
		return SummerObject;
	}
	public List<String> getClassList() {
		return classList;
	}
	private Summer() {
		GetResource();
		for (String className : classList) {
			Class<?> cls;
			try {
				System.out.println(className);
				cls = Class.forName(className,false,Summer.class.getClassLoader());
				if(cls.isAnnotationPresent(Resource.class)){
					Map<String, Object> classMap = SummerObject.get(cls);
					Resource resourceClass = cls.getAnnotation(Resource.class);
					Class<?> clsClass = resourceClass.type();
					Object object = null;
					if(classMap!=null){
						if(classMap.get(resourceClass.name())!=null){
							object = classMap.get(resourceClass.name());
						}else{
							if(clsClass != Object.class){
								object = clsClass.newInstance();
							}else{
								object = cls.newInstance();
							}
							classMap.put(resourceClass.name(), object);
						}
					}else{
						classMap = new LinkedHashMap<String, Object>();
						if(clsClass != Object.class){
							object = clsClass.newInstance();
							classMap.put(resourceClass.name(), object);
							SummerObject.put(cls, classMap);
						}else{
							object = cls.newInstance();
							classMap.put(resourceClass.name(), object);
							SummerObject.put(cls, classMap);
						}
					}
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						if(field.isAnnotationPresent(Resource.class)){
							if(!Modifier.isStatic(field.getModifiers())){
								Resource resourceField = field.getAnnotation(Resource.class);
								Class<?> fieldClass = resourceField.type();
								Object value;
								if(fieldClass != Object.class){
									Map<String, Object> FieldMap = SummerObject.get(fieldClass);
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = fieldClass.newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = fieldClass.newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(fieldClass, FieldMap);
									}
								}else{
									Map<String, Object> FieldMap = SummerObject.get(field.getType());
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = field.getType().newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = field.getType().newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(field.getType(), FieldMap);
									}
								}
								field.setAccessible(true);
								field.set(object, value);
							}else{
								Resource resourceField = field.getAnnotation(Resource.class);
								Class<?> fieldClass = resourceField.type();
								Object value;
								if(fieldClass != Object.class){
									Map<String, Object> FieldMap = SummerObject.get(fieldClass);
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = fieldClass.newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = fieldClass.newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(fieldClass, FieldMap);
									}
								}else{
									Map<String, Object> FieldMap = SummerObject.get(field.getType());
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = field.getType().newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = field.getType().newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(field.getType(), FieldMap);
									}
								}
								field.setAccessible(true);
								field.set(cls, value);
							}
						}
					}
				}else{
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						if(field.isAnnotationPresent(Resource.class)){
							if(Modifier.isStatic(field.getModifiers())){
								Resource resourceField = field.getAnnotation(Resource.class);
								Class<?> fieldClass = resourceField.type();
								Object value;
								if(fieldClass != Object.class){
									Map<String, Object> FieldMap = SummerObject.get(fieldClass);
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = fieldClass.newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = fieldClass.newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(fieldClass, FieldMap);
									}
								}else{
									Map<String, Object> FieldMap = SummerObject.get(field.getType());
									if(FieldMap!=null){
										if(FieldMap.get(resourceField.name())!=null){
											value = FieldMap.get(resourceField.name());
										}else{
											value = field.getType().newInstance();
											FieldMap.put(resourceField.name(), value);
										}
									}else{
										FieldMap = new LinkedHashMap<String, Object>();
										value = field.getType().newInstance();
										FieldMap.put(resourceField.name(), value);
										SummerObject.put(field.getType(), FieldMap);
									}
								}
								field.setAccessible(true);
								field.set(cls, value);
							}
						}
					}
				}
			} catch (Throwable e) {
				continue;
			}
		}
	}
	
	public static synchronized Summer newinstance(){
		if(summer==null){
			summer = new Summer();
		}
		return summer;
	}
	private void GetResource(){
		URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		URL[] urls = classLoader.getURLs();
		URL libURL = null;
		try {
			libURL = new File(System.getProperty("user.dir")+"/lib").toURI().toURL();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (URL url : urls) {
			File file;
			String pack;
			if(!url.getPath().contains(libURL.getPath())){
				try {
					file = new File(url.toURI());
					pack = file.toURI().toURL().getPath();
					GetResource(file,pack,classList);
				} catch (Throwable e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}
	private void GetResource(File file,String pack,List<String> list) throws Exception{
		if (file.isFile()) {
			if (file.getName().endsWith(".jar")) {
				JarFile jarFile = new JarFile(file);
				Enumeration<JarEntry> enumeration = jarFile.entries();
				while (enumeration.hasMoreElements()) {
					JarEntry jarEntry = (JarEntry) enumeration.nextElement();
					if(jarEntry.getName().endsWith(".class")){
//						System.out.println(jarEntry.getName().replaceAll("/", ".").replaceAll(".class", ""));
						list.add(jarEntry.getName().replaceAll("/", ".").replaceAll(".class", ""));
					}
				}
			}else if(file.getName().endsWith(".class")){
				list.add(file.toURI().toURL().getPath().replace(pack, "").replaceAll("/", ".").replaceAll(".class", ""));
			}
		}else if(file.isDirectory()){
			File[] files = file.listFiles();
			for (File item : files) {
				GetResource(item,pack,list);
			}
		}
	}
}