/**
 * 
 */
package str.govern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import str.govern.governors.Governor;
import str.govern.governors.Governors;
import str.govern.governors.NullMaskingGovernor;

/**
 * The Governed annotation for marking a getter method with some governing meta-data.
 * 
 * @author Jason Holmberg
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Governed {
  Governors value() default Governors.dephault;
  Class<? extends Governor> governor() default NullMaskingGovernor.class;
}
