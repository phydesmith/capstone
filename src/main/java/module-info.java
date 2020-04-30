module Capstone {
    requires com.google.gson;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens io.javasmithy to javafx.fxml;

    opens io.javasmithy.model.entity.monster to com.google.gson;
    opens io.javasmithy.model.component.hitpoints to com.google.gson;
    opens io.javasmithy.model.position to com.google.gson;
    opens io.javasmithy.view to com.google.gson;

    opens io.javasmithy.model.item.weapons to com.google.gson;
    opens io.javasmithy.model.item.armor to com.google.gson;
    opens io.javasmithy.model.component.Attack to com.gooogle.gson;

    opens io.javasmithy.controller.scene.main;
    opens io.javasmithy.controller.scene.creation;
    opens io.javasmithy.controller.scene.game;

    exports io.javasmithy.controller.scene.main;
    exports io.javasmithy.controller.scene.creation;
    exports io.javasmithy.controller.scene.game;

    exports io.javasmithy.model.entity.monster;
    exports io.javasmithy.model.position;

    exports io.javasmithy to javafx.graphics;

}