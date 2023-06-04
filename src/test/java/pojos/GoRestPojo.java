package pojos;

public class GoRestPojo {
    private Object meta;
    private GoRestDataPojos data;

    public GoRestPojo() {
    }

    public GoRestPojo(Object meta, GoRestDataPojos data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojos getData() {
        return data;
    }

    public void setData(GoRestDataPojos data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
