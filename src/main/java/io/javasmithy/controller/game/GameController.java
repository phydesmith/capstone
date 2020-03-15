package io.javasmithy.controller.game;

import io.javasmithy.model.entity.*;

public class GameController{
    Entity playerCharacter;

    public GameController(){
        this.playerCharacter = new CharacterEntity();
    }

    public void init(){

    }

    public CharacterEntity getPlayerCharacter(){
        return (CharacterEntity)this.playerCharacter;
    }



}