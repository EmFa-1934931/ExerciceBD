package com.w32.exercicebd2.repository;

public interface Repository<T>
{
    boolean insert(T dataObject);
    T find(int id);
    T findLast();
    boolean update(T dataObject);
    boolean delete(T dataObject);
    boolean delete(int id);
}
