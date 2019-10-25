package com.sucl.async.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * @author sucl
 * @since 2019/9/26
 */
public class UnsafeOperate {
    private static final Unsafe UNSAFE;

    static{
        try {
            final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
                public Unsafe run() throws Exception {
                    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                    theUnsafe.setAccessible(true);
                    return (Unsafe) theUnsafe.get(null);
                }
            };
            UNSAFE = AccessController.doPrivileged(action);
        }catch (Exception e){
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    /**
     * 无权获取
     * @return
     */
    public static Unsafe getUnsafe(){
        Unsafe unsafe = null;//bootClassloader能调用该方法
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return unsafe;
    }

    /**
     * 反射 实例属性获取
     * @return
     */
    public static Unsafe getUnsafeFromField(){
        Class<Unsafe> unsafeClass = Unsafe.class;
        try {
            Field filed = unsafeClass.getDeclaredField("theUnsafe");
            if(!filed.isAccessible()){
                filed.setAccessible(true);
            }
            return (Unsafe) filed.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 反射 构造函数获取
     * @return
     */
    public static Unsafe getUnsafeFromConstruct(){
        Class<Unsafe> unsafeClass = Unsafe.class;
        try {
            Constructor<Unsafe> constructor = unsafeClass.getDeclaredConstructor(null);
            if(!constructor.isAccessible()){
                constructor.setAccessible(true);
            }
            return constructor.newInstance(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        System.out.println(UNSAFE);
        System.out.println(getUnsafe());
        System.out.println(getUnsafeFromField());
        System.out.println(getUnsafeFromConstruct());
    }
}
