/**
 * 
 */
package str.govern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import str.govern.governors.Governor;
import str.govern.governors.NullMaskingGovernor;

/**
 * An interface marking a class as "govern-able". This interface contains some
 * default implementations.
 * 
 * @author Jason Holmberg
 *
 */
public interface Governable {

	static final Logger log = LoggerFactory.getLogger(Governable.class);

	List<String> governed = new ArrayList<>();

	/**
	 * Returns a list of the "governed" methods in the class.
	 * <p>
	 * I originally thought I would use this to determine which classes were
	 * "governed", but turns out I don't need it to do that. But I decided to
	 * keep it around because this stuff is still half-baked.
	 * 
	 * @return a list of governed methods
	 */
	default List<String> getGovernedMethods() {
		if (governed.isEmpty()) {
			System.out.println(this.getClass().getName());
			for (Method m : this.getClass().getMethods()) {
				if (m.isAnnotationPresent(Governed.class)) {
					log.debug("Annotation on " + m.getName() + ": " + Arrays.toString(m.getAnnotations()));
					governed.add(m.getName());
				}
			}
		}
		return governed;
	}

	/**
	 * Attempts to applied the specified or default {@link Governor} to the
	 * incoming object. The return type of the {@link Governor} should be the
	 * same as the {@code type} parameter or at least be cast-able to that type.
	 * 
	 * @param o
	 *            - this incoming value to govern
	 * @param type
	 *            - the type to which the return value is cast
	 * @return
	 */
	default <T extends Object> T govern(Object o, Class<T> type) {
		String currentMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		Method m = null;
		Governor governor = (findGovernor(currentMethod, m));
		return type.cast(governor.govern(o));
	}

	default Governor findGovernor(String currentMethod, Method m) {
		Governor governor = new NullMaskingGovernor();
		try {
			m = this.getClass().getMethod(currentMethod, new Class[] {});
			Governed governed = m.getAnnotation(Governed.class);
			if (governed != null) {
				Governor fromValue = m.getAnnotation(Governed.class).value().strategy;
				Governor fromGovernor = m.getAnnotation(Governed.class).governor().newInstance();
				if (fromGovernor.getClass() != fromValue.getClass()) {
					log.debug("Using specified Governor: {}", fromGovernor.getClass().getName());
					governor = fromGovernor;
				} else {
					log.debug("User default Governor: " + fromValue.getClass().getName());
					governor = fromValue;
				}
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
			log.error("Error governing method: {}. Returning null just to be safe.", m.getName(), e);
		}
		return governor;
	}

}
