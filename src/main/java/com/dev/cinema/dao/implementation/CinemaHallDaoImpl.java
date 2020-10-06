package com.dev.cinema.dao.implementation;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.interfaces.CinemaHallDao;
import com.dev.cinema.exceptions.DatabaseDataExchangeErrorException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    @Override
    public List<CinemaHall> getAll() {
        try (Session session = factory.openSession()) {
            Query<CinemaHall> getAllCinemaHallsQuery =
                    session.createQuery("from CinemaHall", CinemaHall.class);
            return getAllCinemaHallsQuery.getResultList();
        } catch (Exception exception) {
            throw new DatabaseDataExchangeErrorException(GET_ALL_MESSAGE, exception);
        }
    }
}