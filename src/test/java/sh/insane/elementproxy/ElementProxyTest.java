package sh.insane.elementproxy;

import org.junit.jupiter.api.Test;
import sh.insane.elementproxy.interfaces.ObjectType;
import sh.insane.elementproxy.interfaces.PrimitiveType;
import sh.insane.elementproxy.method.MethodDescriptor;

import static org.hamcrest.MatcherAssert.assertThat;

class ElementProxyTest {

	@Test
	public void objectTypeTest() {
		ElementProxyContext elementProxyContext = new ElementProxyContext();

		MethodDescriptor methodDescriptor = elementProxyContext.getMethod(ObjectType.class, ObjectType::getBoolean);

		assertThat("invalid fetched name", methodDescriptor.getName().equals("getBoolean"));
		assertThat("invalid fetched return type", methodDescriptor.getReturnType().equals(Boolean.class));
	}

	@Test
	public void primitiveTypeTest() {
		ElementProxyContext elementProxyContext = new ElementProxyContext();

		MethodDescriptor methodDescriptor = elementProxyContext.getMethod(PrimitiveType.class, PrimitiveType::getBoolean);

		assertThat("invalid fetched name", methodDescriptor.getName().equals("getBoolean"));
		assertThat("invalid fetched return type", methodDescriptor.getReturnType().equals(boolean.class));
	}
}
