
/**
 * Can be implemented in classes to provide {@code Displayable} capability
 *
 * @author Lee Khoon Hong
 */

public interface Displayable {

    /**
     * The {@code Displayable} interface has an abstract method called {@code displayRow()}.
     * Any classes implementing {@code Displayable} will be able to display information in table form.
     * {@code Displayable} acts as a capability of a class as some classes are able to display while some may not.
     *
     * @return The attributes of the implemented class as output in row form
     */
    String displayRow();
}
