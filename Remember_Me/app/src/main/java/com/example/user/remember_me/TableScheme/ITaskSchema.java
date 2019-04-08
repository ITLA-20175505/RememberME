package com.example.user.remember_me.TableScheme;

public interface ITaskSchema {
    String taskTable = "Task";
    String COLUMN_IDTASK = "idTask";
    String COLUMN_NAME = "name";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_ISCANCELLED = "isCancelled";
    String COLUMN_CREATEDDATE = "createdDate";
    String COLUMN_DATE = "date";
    String COLUMN_NEXTDATE = "nextDate";
    String COLUMN_RECURRENCE = "idRecurrence";
    String COLUMN_ISDONE = "isDone";
    String CONSTRAINT_PRIMARYKEY = "Constraint pk_Task primary key (" + COLUMN_IDTASK +"),";
    String CONSTRAINT_FOREIGNKEY = "foreign key (" + COLUMN_RECURRENCE + ") refences Recurrence ("+
     COLUMN_RECURRENCE + ")";
    String createTable = "Create table if not exists " + taskTable + " ("+
            COLUMN_IDTASK + " int not null primary key,"+ COLUMN_NAME + " text not null,"+
            COLUMN_DESCRIPTION + " text null,"+ COLUMN_CREATEDDATE +" date," +
            COLUMN_DATE + " date," + COLUMN_NEXTDATE + " date," + COLUMN_RECURRENCE + " int not null"+
            COLUMN_ISCANCELLED + " boolean default 0," + COLUMN_ISDONE + " boolean default 0, " +
            CONSTRAINT_FOREIGNKEY +   ")";

    String [] taskColumn = new String[]{ COLUMN_IDTASK,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_CREATEDDATE,COLUMN_DATE,
        COLUMN_NEXTDATE,COLUMN_RECURRENCE,COLUMN_ISCANCELLED,COLUMN_ISDONE};



}
