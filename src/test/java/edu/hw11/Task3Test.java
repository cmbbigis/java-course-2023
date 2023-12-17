package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void testFib()
        throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("MyClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new ByteCodeAppender() {
                @Override
                public @NotNull Size apply(@NotNull MethodVisitor mv,
                    Implementation.@NotNull Context ctx,
                    @NotNull MethodDescription instrumentedMethod) {
                    Label label0 = new Label();
                    Label label1 = new Label();
                    mv.visitCode();
                    mv.visitInsn(Opcodes.LCONST_0);
                    mv.visitVarInsn(Opcodes.LSTORE, 1);
                    mv.visitInsn(Opcodes.LCONST_1);
                    mv.visitVarInsn(Opcodes.LSTORE, 3);
                    mv.visitInsn(Opcodes.LCONST_1);
                    mv.visitVarInsn(Opcodes.LSTORE, 5);
                    mv.visitIincInsn(0, -1);
                    mv.visitVarInsn(Opcodes.ILOAD, 0);
                    mv.visitJumpInsn(Opcodes.IFEQ, label1);
                    mv.visitLabel(label0);
                    mv.visitFrame(Opcodes.F_FULL, 4,
                        new Object[] {
                            Opcodes.INTEGER,
                            Opcodes.LONG,
                            Opcodes.LONG,
                            Opcodes.LONG
                        }, 0,  null);
                    mv.visitVarInsn(Opcodes.LLOAD, 3);
                    mv.visitVarInsn(Opcodes.LLOAD, 1);
                    mv.visitInsn(Opcodes.LADD);
                    mv.visitVarInsn(Opcodes.LSTORE, 5);
                    mv.visitIincInsn(0, -1);
                    mv.visitVarInsn(Opcodes.ILOAD, 0);
                    mv.visitVarInsn(Opcodes.LLOAD, 5);
                    mv.visitVarInsn(Opcodes.LLOAD, 3);
                    mv.visitVarInsn(Opcodes.LSTORE, 1);
                    mv.visitVarInsn(Opcodes.LSTORE, 3);
                    mv.visitJumpInsn(Opcodes.IFNE, label0);
                    mv.visitFrame(Opcodes.F_FULL, 4,
                        new Object[] {
                            Opcodes.INTEGER,
                            Opcodes.LONG,
                            Opcodes.LONG,
                            Opcodes.LONG
                        }, 0,  null);
                    mv.visitLabel(label1);
                    mv.visitVarInsn(Opcodes.LLOAD, 5);
                    mv.visitInsn(Opcodes.LRETURN);
                    mv.visitEnd();
                    return new Size(5, 7);
                }
            }))
            .make()
            .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        Method fib = dynamicType.getMethod("fib", int.class);
        assertThat(fib.invoke(dynamicType.newInstance(), 10)).isEqualTo(55L);
    }
}
