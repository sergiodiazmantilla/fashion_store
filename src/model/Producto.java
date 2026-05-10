package model;

public abstract class Producto {

    protected int id;
    protected String nombre;
    protected String tipo;
    protected double precio;

    protected int stock;
    protected int stockMinimo;

    protected String talla;
    protected String color;

    protected String atributo;

    // Constructor vacío
    public Producto() {
    }

    // Constructor completo
    public Producto(
            int id,
            String nombre,
            String tipo,
            double precio,
            int stock,
            int stockMinimo,
            String talla,
            String color,
            String atributo
    ) {

        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.talla = talla;
        this.color = color;
        this.atributo = atributo;
    }

    // Método abstracto
    public abstract void mostrarInformacion();

    // Método para validar stock bajo
    public boolean stockBajo() {
        return stock <= stockMinimo;
    }

    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}