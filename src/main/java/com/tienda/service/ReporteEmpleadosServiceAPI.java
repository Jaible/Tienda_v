package com.tienda.service;

import com.tienda.entity.Reporte;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

public interface ReporteEmpleadosServiceAPI {
    
    Reporte obtenerReporte(Map<String, Object> params) throws JRException, IOException, SQLException;
}
