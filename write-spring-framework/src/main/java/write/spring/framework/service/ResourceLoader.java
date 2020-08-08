package write.spring.framework.service;

import write.spring.framework.application.BeanApplication;
import write.spring.framework.annotation.Bean;
import write.spring.framework.annotation.InitMethod;
import write.spring.framework.domain.BeanDefinition;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description： 资源加载
 * Created By tanghao on 2020/8/1
 */
public class ResourceLoader {

    private static List<String> classPaths = new ArrayList<String>();

    public static List<BeanDefinition> getAllBeans(String... scanPackages) throws ClassNotFoundException {
        List<BeanDefinition> res = new ArrayList<>();
        for(String basePack:scanPackages){
            String classpath = BeanApplication.class.getResource("/").getPath();
            basePack = basePack.replace(".", File.separator);
            String searchPath = classpath + basePack;
            doPath(new File(searchPath));
            for (String s : classPaths) {
                s = s.replace(classpath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
                Class<?> cls = Class.forName(s);
                Bean anno = cls.getAnnotation(Bean.class);
                if(null!=anno){
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(cls);
                    beanDefinition.setName(anno.name());
                    res.add(beanDefinition);
                    Method[] methods = cls.getDeclaredMethods();
                    for(Method method:methods){
                        InitMethod initMethodAnnotation = method.getAnnotation(InitMethod.class);
                        if(null != initMethodAnnotation){
                            beanDefinition.setInitMethod(method.getName());
                        }
                    }
                }
            }
        }
        return res;
    }

    private static void doPath(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {
            if (file.getName().endsWith(".class")) {
                classPaths.add(file.getPath());
            }
        }
    }
}
