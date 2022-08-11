package com.tienda.entity;

import java.io.ByteArrayInputStream;
import javax.persistence.Entity;

public class Reporte {
    
    private String filename;
    private ByteArrayInputStream stream; 
    private int length;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ByteArrayInputStream getStream() {
        return stream;
    }

    public void setStream(ByteArrayInputStream stream) {
        this.stream = stream;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    
}
