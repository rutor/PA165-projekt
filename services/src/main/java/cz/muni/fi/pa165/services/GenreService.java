package cz.muni.fi.pa165.services;

import java.util.List;

import cz.muni.fi.pa165.entity.Genre;

public interface GenreService {
public Long create(Genre genre);
public Genre findById(Long id);
public List<Genre> findAll();
public Genre findByName(String name);
public void remove(Genre genre);
public void update(Genre genre);
}