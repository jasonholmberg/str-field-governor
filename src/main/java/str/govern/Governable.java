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
 * @author Jason Holmberg
 *
 */
public interface Governable {
  
  static final Logger log = LoggerFactory.getLogger(Governable.class);
  
  List<String> governed = new ArrayList<>();
  default List<String> getGovernedMethods() {
    if (governed.isEmpty()) {
    System.out.println(this.getClass().getName());
    for(Method m : this.getClass().getMethods()) {
      if (m.isAnnotationPresent(Governed.class)) {
        log.debug("Annotation on " + m.getName() + ": " + Arrays.toString(m.getAnnotations()));
        governed.add(m.getName());
      }
    }
    }
    return governed;
  }
  
  default <T extends Object> T govern(Object o, Class<T> type) {
    String currentMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
      Method m = null;
      Governor governor = new NullMaskingGovernor();
      try {
        m = this.getClass().getMethod(currentMethod, new Class[] {});
        Governed governed = m.getAnnotation(Governed.class);
        if(governed != null) {
          Governor fromValue = m.getAnnotation(Governed.class).value().strategy;
          Governor fromGovernor = m.getAnnotation(Governed.class).governor().newInstance();
          if(fromGovernor.getClass() != fromValue.getClass()) {
            log.debug("Using specified Governor: {}",fromGovernor.getClass().getName());
            governor = fromGovernor;
          } else {
            log.debug("User default Governor: "+fromValue.getClass().getName());
            governor = fromValue;
          }
        }
      } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException e) {
        log.error("Error governing method: {}. Returning null just to be safe.", m.getName(),e);
      }
      return type.cast(governor.govern(o));
    }
  
}
