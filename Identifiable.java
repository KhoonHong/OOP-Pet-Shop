
/**
 * Can be implemented in classes to provide {@code identifiable} capability
 *
 * @author Chan Jia Wei
 */

public interface Identifiable {

    /**
     * The {@code Identifiable} interface has an abstract method called {@code generateID()}.
     * Any classes implementing {@code Identifiable} are able to generate a unique identification code.
     * {@code Displayable} acts as a capability of a class as some classes are able to have unique identification code while some may not.
     *
     * @param count The current class object total count
     * @return A formatted ID with class name in abbreviation at the front, current class object count at the back
     */
    String generateID(int count);
}
