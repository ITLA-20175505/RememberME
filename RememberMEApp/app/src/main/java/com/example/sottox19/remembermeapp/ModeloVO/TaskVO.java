package com.example.sottox19.remembermeapp.ModeloVO;

public class TaskVO {
    private int midTask;
    private String mname;
    private String mdescription;
    private byte mIsCancelled;
    private String mtaskDate;
    private String mnextDate;
    private byte misDone;
    private RecurrenceVO mRecurrenceVO;
    /* Metodos Getter y Setter de la clase Task */
    public int getMidTask() {
        return midTask;
    }

    public void setMidTask(int midTask) {
        this.midTask = midTask;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }

    public byte getIsCancelled() {
        return mIsCancelled;
    }

    public void setIsCancelled(byte isCancelled) {
        mIsCancelled = isCancelled;
    }

    public String getMtaskDate() {
        return mtaskDate;
    }

    public void setMtaskDate(String mtaskDate) {
        this.mtaskDate = mtaskDate;
    }

    public String getMnextDate() {
        return mnextDate;
    }

    public void setMnextDate(String mnextDate) {
        this.mnextDate = mnextDate;
    }

    public byte getMisDone() {
        return misDone;
    }

    public void setMisDone(byte misDone) {
        this.misDone = misDone;
    }

    public RecurrenceVO getRecurrenceVO() {
        return mRecurrenceVO;
    }

    public void setRecurrenceVO(RecurrenceVO recurrenceVO) {
        mRecurrenceVO = recurrenceVO;
    }
}
