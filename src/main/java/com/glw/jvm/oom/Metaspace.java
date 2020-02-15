package com.glw.jvm.oom;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : glw
 * @date : 2020/2/15
 * @time : 0:43
 * @Description : Metaspace类
 */
public class Metaspace extends ClassLoader {
    private final static int COUNT = 10000000;

    public static List<Class<?>> createClass() {
        List<Class<?>> classes = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            ClassWriter cw = new ClassWriter(0);
            // 定义一个类名为Class{i}，访问权限为public，父类为java.lang.Object，且不实现任何接口的类
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            // 定义构造函数
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null ,null);
            // 第一个指令为加载this
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            // 第二个指令为调用父类Object的构造函数
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            // 第三条指令为return
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
            Metaspace metaspace = new Metaspace();
            byte[] code = cw.toByteArray();
            // 定义类
            Class<?> exampleClass = metaspace.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }
}
