package pojos.gorest;

public class User {

    private int code;
    private Object meta;
    private Data data;

    public User() {

    }

    public User(int code, Object meta, Data data) {
        this.code = code;
        this.meta = meta;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "\t\nUser{" +
                "\t\ncode = " + code +
                "\t\n meta = " + meta +
                "\t\n data = " + data +
                '}';
    }
}
