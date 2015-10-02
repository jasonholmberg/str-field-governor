/**
 * 
 */
package str.govern.governors;

/**
 * @author Jason Holmberg
 *
 */
public class NullMaskingGovernor implements Governor {

  @Override
  public Object govern(Object o) {
    return null;
  }
  
}
