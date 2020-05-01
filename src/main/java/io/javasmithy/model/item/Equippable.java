package io.javasmithy.model.item;
/** Interface that tells if something is equpped or not
 * @author Peter Hyde-Smith
 */
public interface Equippable {
    public void setEquipped(boolean status);
    public boolean isEquipped();
}
