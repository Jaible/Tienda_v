package com.tienda.service;

import com.tienda.commons.JasperReportManager;
import com.tienda.entity.Reporte;
import com.tienda.enums.TipoReportEnum;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteEmpleadosServiceImpl implements ReporteEmpleadosServiceAPI {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public Reporte obtenerReporte(Map<String, Object> params) throws JRException, IOException, SQLException {
        Reporte reporte = new Reporte();
        String filename = "ReporteEmpleado";
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReportEnum.EXCEL.name()) ? ".xlsx" : ".pdf";

        reporte.setFilename(filename + extension);
        ByteArrayOutputStream stream = reportManager.export(filename,
                params.get("tipo").toString(), params, dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        reporte.setStream(new ByteArrayInputStream(bs));
        reporte.setLength(bs.length);

        return reporte;
    }

}
