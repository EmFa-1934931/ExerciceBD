package com.w32.exercicebd2.database;

public final class CoursTable {

    public static final String CREATE_TABLE_SQL = "" +
            "CREATE TABLE cours (" +
            "       id                     INTEGER          PRIMARY KEY       AUTOINCREMENT, " +
            "       code                   TEXT, " +
            "       nom                    TEXT, " +
            "       prof                   TEXT, " +
            "       session                TEXT, " +
            "       technique              INT " +
            ")";

    public static final String DROP_TABLE_SQL = "" +
            "DROP TABLE cours";

    public static final String INSERT_SQL = "" +
            "INSERT INTO cours ( " +
            "        code, " +
            "        nom, " +
            "        prof, " +
            "        session, " +
            "        technique " +
            ") VALUES ( " +
            "        ?, " +
            "        ?, " +
            "        ?, " +
            "        ?, " +
            "        ? " +
            ")";

    public static final String SELECT_ALL_OF_SQL = "" +
            "SELECT " +
            "        cours.id, " +
            "        cours.code, " +
            "        cours.nom, " +
            "        cours.prof, " +
            "        cours.session, " +
            "        cours.technique " +
            "FROM " +
            "        cours ";



    public static final String SELECT_ONE_OF_SQL = "" +
            "SELECT " +
            "        cours.id, " +
            "        cours.code, " +
            "        cours.nom, " +
            "        cours.prof, " +
            "        cours.session, " +
            "        cours.technique " +
            "FROM " +
            "        cours " +
            "WHERE " +
            "        cours.id = ?";


    public static final String SELECT_LAST_OF_SQL = "" +
            "SELECT " +
            "        MAX(id), " +
            "        cours.code, " +
            "        cours.nom, " +
            "        cours.prof, " +
            "        cours.session, " +
            "        cours.technique " +
            "FROM " +
                    "cours ";


    public static final String UPDATE_SQL = "" +
            "UPDATE cours " +
            "SET " +
            "        code = ?, " +
            "        nom = ?, " +
            "        prof = ?, " +
            "        session = ?, " +
            "        technique = ? " +
            "WHERE " +
            "        id = ?";

    public static final String DELETE_SQL = "" +
            "DELETE FROM cours " +
            "WHERE " +
            "        id = ?";

    private CoursTable() {
        //Constructeur privé pour éviter toute instanciation
    }
}
