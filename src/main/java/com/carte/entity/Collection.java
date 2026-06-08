package com.carte.entity;

import java.util.*;

public class Collection {

    private List<Album> albums = new ArrayList<>();

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(String nome) {
        albums.removeIf(a -> a.getName().equalsIgnoreCase(nome));
    }

    public Album getAlbum(String nome) {
        return albums.stream()
            .filter(a -> a.getName().equalsIgnoreCase(nome))
            .findFirst()
            .orElse(null);
    }

    /**
     * ✅ Defensive Copy: restituisce una copia della lista
     */
    public List<Album> getAlbums() {
        return new ArrayList<>(albums);
    }

    public boolean containsAlbum(String nome) {
        return getAlbum(nome) != null;
    }

    public void setAlbums(List<Album> newAlbums) {
        this.albums = new ArrayList<>(newAlbums);
    }
}
