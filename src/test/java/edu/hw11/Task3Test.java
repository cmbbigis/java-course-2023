//package edu.hw11;
//
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.description.method.MethodDescription;
//import net.bytebuddy.description.type.TypeDescription;
//import net.bytebuddy.dynamic.ClassFileLocator;
//import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
//import net.bytebuddy.implementation.FixedValue;
//import net.bytebuddy.implementation.Implementation;
//import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
//import net.bytebuddy.jar.asm.Label;
//import net.bytebuddy.jar.asm.MethodVisitor;
//import net.bytebuddy.jar.asm.Opcodes;
//import net.bytebuddy.jar.asm.Type;
//import net.bytebuddy.matcher.ElementMatchers;
//import net.bytebuddy.pool.TypePool;
//import org.junit.jupiter.api.Test;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//public class Task3Test {
//    @Test
//    void testRedefine()
//        throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class<?> dynamicType = new ByteBuddy()
//            .subclass(Object.class)
//            .name("MyClass")
//            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
//            .withParameters(int.class)
//            .intercept(new Implementation.Simple(new ByteCodeAppender() {
//                @Override
//                public Size apply(
//                    MethodVisitor mv,
//                    Implementation.Context ctx,
//                    MethodDescription instrumentedMethod
//                ) {
//                    mv.visitCode();
//                    Label label0 = new Label();
//                    mv.visitLabel(label0);
//                    mv.visitVarInsn(Opcodes.ILOAD, 0);
//                    Label label1 = new Label();
//                    mv.visitJumpInsn(Opcodes.IFNE, label1);
//                    mv.visitInsn(Opcodes.LCONST_0);
//                    mv.visitInsn(Opcodes.LRETURN);
//                    mv.visitLabel(label1);
//                    mv.visitVarInsn(Opcodes.ILOAD, 0);
//                    mv.visitInsn(Opcodes.ICONST_1);
//                    Label label2 = new Label();
//                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, label2);
//                    mv.visitInsn(Opcodes.LCONST_1);
//                    mv.visitInsn(Opcodes.LRETURN);
//                    mv.visitLabel(label2);
//                    mv.visitInsn(Opcodes.ICONST_2);
//                    mv.visitVarInsn(Opcodes.ISTORE, 1);
//                    mv.visitInsn(Opcodes.LCONST_1);
//                    mv.visitVarInsn(Opcodes.LSTORE, 2);
//                    mv.visitInsn(Opcodes.LCONST_1);
//                    mv.visitVarInsn(Opcodes.LSTORE, 4);
//                    Label label3 = new Label();
//                    mv.visitLabel(label3);
//                    Label label4 = new Label();
//                    mv.visitJumpInsn(Opcodes.GOTO, label4);
//                    Label label5 = new Label();
//                    mv.visitLabel(label5);
//                    mv.visitFrame(Opcodes.F_APPEND,3, new Object[] {Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG}, 0, null);
//                    mv.visitVarInsn(Opcodes.LLOAD, 4);
//                    mv.visitVarInsn(Opcodes.LLOAD, 2);
//                    mv.visitInsn(Opcodes.LADD);
//                    mv.visitVarInsn(Opcodes.LSTORE, 6);
//                    mv.visitVarInsn(Opcodes.LLOAD, 4);
//                    mv.visitVarInsn(Opcodes.LSTORE, 2);
//                    mv.visitVarInsn(Opcodes.LLOAD, 6);
//                    mv.visitVarInsn(Opcodes.LSTORE, 4);
//                    mv.visitIincInsn(1, 1);
//                    mv.visitLabel(label4);
//                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//                    mv.visitVarInsn(Opcodes.ILOAD, 1);
//                    mv.visitVarInsn(Opcodes.ILOAD, 0);
//                    mv.visitJumpInsn(Opcodes.IF_ICMPLT, label5);
//                    mv.visitVarInsn(Opcodes.LLOAD, 4);
//                    mv.visitInsn(Opcodes.LRETURN);
//                    Label label6 = new Label();
//                    mv.visitLabel(label6);
//                    mv.visitMaxs(4, 8);
//                    mv.visitEnd();
//                    return new ByteCodeAppender.Size(4, instrumentedMethod.getStackSize());
//                }
//            }))
//            .make()
//            .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
//            .getLoaded();
//
//        Method fibMethod = dynamicType.getMethod("fib", int.class);
//        long result = (long) fibMethod.invoke(null, 10);
//        System.out.println("Результат: " + result);
//    }
//}
