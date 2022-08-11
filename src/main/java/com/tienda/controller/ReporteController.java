package com.tienda.controller;

import com.tienda.entity.Reporte;
import com.tienda.enums.TipoReportEnum;
import com.tienda.service.ReporteEmpleadosServiceAPI;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReporteController {

    @Autowired
    private ReporteEmpleadosServiceAPI reporteVentasServiceAPI;

    @GetMapping(path = "/ventas/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        Reporte dto = reporteVentasServiceAPI.obtenerReporte(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReportEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFilename() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }

}
