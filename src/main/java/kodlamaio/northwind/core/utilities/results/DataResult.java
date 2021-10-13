package kodlamaio.northwind.core.utilities.results;

public class DataResult<T> extends Result {

    private T data;

    public DataResult(T data, boolean success, String message) {
        super(success, message); //BASE SINIFIN CONSTRUCTOR LARINI ÇALIŞTIRIR
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success); //BASE SINIFIN CONSTRUCTOR LARINI ÇALIŞTIRIR
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
