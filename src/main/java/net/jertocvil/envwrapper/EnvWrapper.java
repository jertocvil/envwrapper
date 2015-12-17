package net.jertocvil.envwrapper;

import net.jertocvil.envwrapper.annotations.OptionalVar;
import net.jertocvil.envwrapper.annotations.VarName;
import net.jertocvil.envwrapper.exceptions.InvalidMethodException;
import net.jertocvil.envwrapper.exceptions.MissingVariableException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EnvWrapper {

    /**
     * Get generated instance implementing the provided interface.
     * This is the main method of the library. All method calls to the generated instance are routed to a function that examines annotations and return types and acts accordingly, for example, checking for existence or parsing.
     * @param clazz The class object of the interface
     * @param <T> The type of the interface
     * @return The generated instace
     */
    public static <T> T getConfig(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] { clazz },
                EnvWrapper.envWrapperInvocationHandler);

    }


    private static InvocationHandler envWrapperInvocationHandler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            VarName annotation = method.getAnnotation(VarName.class);

            if(annotation == null) {
                // TODO Possible enhancement: get env var name from method name
                throw new InvalidMethodException(InvalidMethodException.Reason.NOT_ANNOTATED);
            }

            boolean optional = method.getAnnotation(OptionalVar.class) != null;

            String envVarName = annotation.value();

            Class<?> returnType = method.getReturnType();

            if(String.class.equals(returnType)) {

                String value = System.getenv(envVarName);

                if(value == null && !optional) {
                    throw new MissingVariableException(envVarName);
                }

                return value;

            } else {
                throw new NotImplementedException();
            }
            // TODO
            // if(int.class.equals(returnType) || Integer.class.equals(returnType))
        }
    };
}
