/**
 * 
 */
package str.govern.governors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Governor} that return a string of X's instead of the original value.
 * 
 * @author Jason Holmberg
 *
 */
public class StringMaskingGovernor implements Governor {

  private static final Logger log = LoggerFactory.getLogger(StringMaskingGovernor.class);
  private static final String MASK = "XXXXXXXXXXXXXXXXXX";
  
  @Override
  public String govern(Object input) {
    log.debug("Masking the string: {} to {}",(String)input,MASK);
    return MASK;
  }

}
