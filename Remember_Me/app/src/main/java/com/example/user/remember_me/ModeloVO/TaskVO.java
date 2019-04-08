package com.example.user.remember_me.ModeloVO;

public class TaskVO {
    private int midTask;
    private String mname;
    private String mdescription;
    private boolean mIsCancelled;
    private String mtaskDate;
    private String mnextDate;
    private boolean misDone;
    private RecurrenceVO mRecurrence;
    /* Metodos Getter y Setter de la clase Task */
    public int getidTask() {
        return midTask;
    }

    public void setidTask(int midTask) {
        this.midTask = midTask;
    }

    public String getname() {
        return mname;
    }

    public void setname(String mname) {
        this.mname = mname;
    }

    public String getdescription() {
        return mdescription;
    }

    public void setdescription(String mdescription) {
        this.mdescription = mdescription;
    }

    public boolean getIsCancelled() {
        return mIsCancelled;
    }

    public void setIsCancelled(boolean isCancelled) {
        mIsCancelled = isCancelled;
    }

    public String gettaskDate() {
        return mtaskDate;
    }

    public void settaskDate(String mtaskDate) {
        this.mtaskDate = mtaskDate;
    }

    public String getnextDate() {
        return mnextDate;
    }

    public void setnextDate(String mnextDate) {
        this.mnextDate = mnextDate;
    }

    public boolean getisDone() {
        return misDone;
    }

    public void setisDone(boolean misDone) {
        this.misDone = misDone;
    }

    public RecurrenceVO getRecurrence() {
        return mRecurrence;
    }

    public void setRecurrence(RecurrenceVO recurrenceVO) {
        mRecurrence = recurrenceVO;
    }
}
