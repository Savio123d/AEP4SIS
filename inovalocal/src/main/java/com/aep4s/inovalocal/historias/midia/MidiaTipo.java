package com.aep4s.inovalocal.historias.midia;

public enum MidiaTipo {
    FOTO("Fotografia"),
    VIDEO("Vídeo"),
    AUDIO("Áudio");

    private final String descricao;

    MidiaTipo(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }
}
