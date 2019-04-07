package com.example.sottox19.remembermeapp.Interfaces;

public interface IRecurrenceSchema {
    String recurrenceTable = "Recurrence";
    String COLUMN_IDRECURRENCE = "idRecurrence";
    String COLUMN_NAME = "name";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_INTERVALTYPE = "intervalType";
    String COLUMN_INTERVAL = "interval";
    String CONSTRAINT_PRIMARYKEY = "Constraint pk_recurrence primary key (" + COLUMN_IDRECURRENCE + ")";
    String createTable = "create table  if not exists " + recurrenceTable +" (" +
            COLUMN_IDRECURRENCE + " int not null," + COLUMN_NAME +" varchar(25) not null,"+
            COLUMN_DESCRIPTION +" text null," + COLUMN_INTERVALTYPE + " varchar(25)," +
            COLUMN_INTERVAL +"interval int," + CONSTRAINT_PRIMARYKEY +")";

    String [] recurrenceColumn = new String[]{COLUMN_IDRECURRENCE,COLUMN_NAME,COLUMN_DESCRIPTION,
                                COLUMN_INTERVALTYPE,COLUMN_INTERVAL};


}
