/**
 * 
 */
package str.govern.governors;

/**
 * @author Jason Holmberg
 *
 */
public enum Governors {
  dephault(new NullMaskingGovernor()),
  mask(new StringMaskingGovernor());
  public Governor strategy;
  Governors(Governor strategy) {
    this.strategy = strategy;
  }
}
