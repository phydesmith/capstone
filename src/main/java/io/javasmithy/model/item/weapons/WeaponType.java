package io.javasmithy.model.item.weapons;

import io.javasmithy.model.item.ItemType;

/** Types of weapons used by GSON to load entries from json in weapon factory and weapon config
 * @author Peter Hyde-Smith
 */
public enum WeaponType implements ItemType {
    LONGSWORD,
    SPEAR,
    AXE;
}
