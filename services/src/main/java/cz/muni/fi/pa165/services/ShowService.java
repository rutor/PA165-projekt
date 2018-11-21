package cz.muni.fi.pa165.services;

import java.util.List;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Show;
public interface ShowService {
public Long create(Show show);
public Show findById(Long id);
public List<Show> findAll();
public List<Show> findAllByGenre(Genre genre);
public void remove(Show show);
public void update(Show show);
}
