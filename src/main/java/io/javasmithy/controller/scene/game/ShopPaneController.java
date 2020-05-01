package io.javasmithy.controller.scene.game;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.PaneController;
import io.javasmithy.model.item.Item;
import io.javasmithy.model.item.armor.Armor;
import io.javasmithy.model.item.armor.ArmorFactory;
import io.javasmithy.model.item.armor.ArmorType;
import io.javasmithy.model.item.weapons.WeaponFactory;
import io.javasmithy.model.item.weapons.WeaponType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShopPaneController implements Initializable, PaneController {

    private GameController gc;
    private ObservableList<Item> storeInventoryList;
    private ObservableList<Item> playerInventoryList;

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
    private void populateStoreItems(){
        this.storeInventoryList = FXCollections.observableArrayList();
        storeInventory.setItems(this.storeInventoryList);
        addWeapons();
        addArmor();
    }
    private void addWeapons(){
        for(int i = 0; i < WeaponType.values().length; i++){
            this.storeInventoryList.add(WeaponFactory.createWeapon(WeaponType.values()[i]));
        }
    }
    private void addArmor(){
        for(int i = 0; i < ArmorType.values().length; i++){
            this.storeInventoryList.add(ArmorFactory.createArmor(ArmorType.values()[i]));
        }
    }
    public void setGameController(GameController gc) {
        this.gc = gc;
        setPlayerInventory();

    }
    public void setPlayerInventory(){
        this.playerInventoryList = FXCollections.observableArrayList(this.gc.getPlayerCharacter().getInventory());
        this.playerInventory.setItems(this.playerInventoryList);
    }


    @FXML
    public void buyItem(){
        System.out.println("DEBUG: bought item");
    }

    @FXML void sellItem(){
        System.out.println("DEBUG: sold item");
    }

    @FXML void stealItem(){
        System.out.println("DEBUG: steal item");
    }
}
