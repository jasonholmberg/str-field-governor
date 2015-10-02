/**
 * 
 */
package str.govern.governors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Governor} the simply returns null.
 * 
 * @author Jason Holmberg
 *
 */
public class NullMaskingGovernor implements Governor {
  
  private static final Logger log = LoggerFactory.getLogger(NullMaskingGovernor.class);

  @Override
  public Object govern(Object input) {
    log.debug("Masking the string: {} to null",(String) input);
    return null;
  }
  
}
