/**
 * 
 */
package str.govern.governors;

/**
 * @author Jason Holmberg
 *
 */
public class StringMaskingGovernor implements Governor {

  @Override
  public String govern(Object input) {
    return "XXXXXXXXXXXXXXXXXX";
  }

}
