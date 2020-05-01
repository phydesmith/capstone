package io.javasmithy.controller.scene.creation;

import io.javasmithy.controller.game.GameController;
import io.javasmithy.controller.scene.SceneController;
import io.javasmithy.model.component.skill.Skill;
import io.javasmithy.model.entity.CharacterEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.*;

public class SkillChoiceController implements Initializable, SceneController {

    private GameController gc;
    private List<Skill> availableSkills;

    @FXML
    ListView<Skill> skillChoices;
    @FXML
    ListView<Skill> skillSelections;
    @FXML
    TextArea skillDescription;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSkillSelectionsListener();
        setSkillChoicesListener();
    }
    public void initSkillEnums(){
        CharacterEntity pc = this.gc.getPlayerCharacter();
        List<Skill> classSkills = pc.getCClass().getSkillList();
        List<Skill> backgroundSkills = pc.getBackground().getSkillList();

        List<Skill> availableSkills = new ArrayList<Skill>();
        for (int i = 0; i < classSkills.size(); i++){
            if (!backgroundSkills.contains(classSkills.get(i))){
                availableSkills.add(classSkills.get(i));
            }
        }

        this.skillChoices.setItems(FXCollections.observableArrayList(availableSkills));

        for (int i = 0; i < backgroundSkills.size();i++){
            this.skillSelections.getItems().add(backgroundSkills.get(i));
        }
    }


    private void setSkillChoicesListener(){
        skillChoices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Skill>() {
            @Override
            public void changed(ObservableValue<? extends Skill> observable, Skill oldValue, Skill newValue) {
                skillDescription.setText(newValue.getDescription());
            }
        });
    }
    private void setSkillSelectionsListener(){
        skillSelections.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Skill>() {
            @Override
            public void changed(ObservableValue<? extends Skill> observable, Skill oldValue, Skill newValue) {
                skillDescription.setText(newValue.getDescription());
            }
        });
    }

    @Override
    public void setGameController(GameController gc) {
        this.gc = gc;
    }

    @FXML
    public void submitSkills(){
        this.gc.getPlayerCharacter().setSkillList(this.skillSelections.getItems());
    }
    @FXML
    public void addSkill(){
        int backgroundSkillCount = this.gc.getPlayerCharacter().getBackground().getSkillList().size();
        if (this.skillSelections.getItems().size() - backgroundSkillCount >= this.gc.getPlayerCharacter().getCClass().getSkillCount()){
            skillDescription.setText("Max number of skill choices reached!");
            return;
        }
        if (skillChoices.getSelectionModel().getSelectedItem()!=null ){
            skillSelections.getItems().add(skillChoices.getSelectionModel().getSelectedItem());
            skillChoices.getItems().remove(skillChoices.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    public void removeSkill(){
        Skill item = skillSelections.getSelectionModel().getSelectedItem();
        if (item != null && !this.gc.getPlayerCharacter().getBackground().getSkillList().contains(item)){
            skillChoices.getItems().add(skillSelections.getSelectionModel().getSelectedItem());
            skillSelections.getItems().remove(skillSelections.getSelectionModel().getSelectedItem());
        } else {
            skillDescription.setText("Cannot remove background skill.");
        }
    }

}
