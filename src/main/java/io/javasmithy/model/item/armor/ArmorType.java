package io.javasmithy.model.item.armor;

import io.javasmithy.model.item.ItemType;

/**Types of armor used by GSON to load entries from json in armor factory and armor config
 * @author Peter Hyde-Smith
 */
public enum ArmorType implements ItemType {
    LEATHER,
    CHAINMAIL,
    PLATE;
}
