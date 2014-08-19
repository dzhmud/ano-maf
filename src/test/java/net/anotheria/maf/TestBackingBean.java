package net.anotheria.maf;

import net.anotheria.maf.bean.FormBean;

public class TestBackingBean implements FormBean {

	private int subject;
	private int requestId;

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "TestBackingBean{" +
                "subject=" + subject +
                ", requestId=" + requestId +
                '}';
    }
}