package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.PaneController;
import io.javasmithy.model.component.skill.Skill;
import io.javasmithy.model.entity.CharacterEntity;
import io.javasmithy.model.item.Equippable;
import io.javasmithy.model.item.Item;
import io.javasmithy.model.item.armor.Armor;
import io.javasmithy.model.item.armor.ArmorFactory;
import io.javasmithy.model.item.armor.ArmorType;
import io.javasmithy.model.item.weapons.Weapon;
import io.javasmithy.model.item.weapons.WeaponFactory;
import io.javasmithy.model.item.weapons.WeaponType;
import io.javasmithy.util.GameLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/** Controller to control behavior of Shop/Store panes
 * @author Peter Hyde-Smith
 */
public class ShopPaneController implements Initializable, PaneController {

    private GameController gc;
    private ObservableList<Item> storeInventoryList;

    @FXML
    AnchorPane gamePane;

    @FXML
    ListView<Item> storeInventory;
    @FXML
    ListView<Item> playerInventory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateStoreItems();
    }

    /**
     * populates Store List View with items
     */
    private void populateStoreItems(){
        this.storeInventoryList = FXCollections.observableArrayList();
        storeInventory.setItems(this.storeInventoryList);
        addWeapons();
        addArmor();
    }
    /**
     * populates Store List View with weapons
     */
    private void addWeapons(){
        for(int i = 0; i < WeaponType.values().length; i++){
            this.storeInventoryList.add(WeaponFactory.createWeapon(WeaponType.values()[i]));
        }
    }
    /**
     * populates Store List View with armors
     */
    private void addArmor(){
        for(int i = 0; i < ArmorType.values().length; i++){
            this.storeInventoryList.add(ArmorFactory.createArmor(ArmorType.values()[i]));
        }
    }

    /**
     * Sets game controller.
     * @param gc gamecontroller to set
     */
    public void setGameController(GameController gc) {
        this.gc = gc;
        setPlayerInventory();

    }
    /**
     * Used to set items of listview that observes player inventory.
     */
    public void setPlayerInventory(){
        this.playerInventory.setItems(this.gc.getPlayerCharacter().getInventory());
    }


    /**
     * Transfers items from Store to character -checks vs character gold.
     * Subtracts value of item from character gold.
     */
    @FXML
    public void buyItem(){
        CharacterEntity pc = this.gc.getPlayerCharacter();
        Item item = this.storeInventory.getSelectionModel().getSelectedItem();
        if (pc.getGold() > item.getValue()){
            pc.changeGold( -item.getValue() );
            pc.addItemToInventory(item);
            GameLog.addEntry(pc.getName() + " bought " + item + " from store.");
            this.storeInventoryList.remove(item);
            System.out.println("Log: item " + item  + " removed from store -> ");
        }
    }

    /**
     * Transfers items from character to store.
     * Adds item value to character gold.
     */
    @FXML void sellItem(){
        CharacterEntity pc = this.gc.getPlayerCharacter();
        Item item = this.playerInventory.getSelectionModel().getSelectedItem();
        if (item instanceof Equippable) ((Equippable) item).setEquipped(false);
        if (pc.getGold() > item.getValue()){
            pc.changeGold( item.getValue() );
            this.storeInventoryList.add(item);
            GameLog.addEntry(pc.getName() + " sold " + item.getName() + " to store.");
            pc.getInventory().remove(item);
            System.out.println("Log: item " + item.getName() + " removed from inventory. -> ");
        }
    }

    /**
     * Transfers items from Store to character -checks vs sleight of hand skill.
     */
    @FXML void stealItem(){
        CharacterEntity pc = this.gc.getPlayerCharacter();
        Item item = this.storeInventory.getSelectionModel().getSelectedItem();
        if (pc.hasSkill(Skill.SLEIGHT_OF_HAND) && pc.skillCheck(Skill.SLEIGHT_OF_HAND, 15)){
            pc.addItemToInventory(item);
            GameLog.addEntry(pc.getName() + " stole " + item.getName() + " from store!");
            this.storeInventoryList.remove(item);
            System.out.println("Log: item " + item.getName()  + " removed from store");
        } else GameLog.addEntry(pc.getName() + " failed to steal " + item.getName());
    }

    /**
     * Allows selected item in inventory list view to be noted as equpped (which character can then use).
     */
    @FXML void equipItem(){
        CharacterEntity pc = this.gc.getPlayerCharacter();
        Item item = this.playerInventory.getSelectionModel().getSelectedItem();
        if (item instanceof Armor) {
            pc.getArmor().setEquipped(false);
            pc.setArmor((Armor)item);
            GameLog.addEntry(pc.getName() + " equipped " + item.getName());
        } else if ( item instanceof Weapon){
            pc.getWeapon().setEquipped(false);
            pc.setWeapon((Weapon)item);
            GameLog.addEntry(pc.getName() + " equipped " + item.getName());
        } else return;
    }
}
