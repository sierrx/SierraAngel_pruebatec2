package com.turnos.servlets;

import com.turnos.model.ControladoraModel;
import com.turnos.model.Turno;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TurnoSv", urlPatterns = {"/TurnoSv"})
public class TurnoSv extends HttpServlet {

    private final ControladoraModel control = new ControladoraModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String busquedaFecha = request.getParameter("busquedaFecha");
        if (busquedaFecha != null && !busquedaFecha.isEmpty()) {
            try {
                LocalDate fecha = LocalDate.parse(busquedaFecha);
                List<Turno> listaTurno = control.buscarPorFecha(fecha);
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("listaTurnoFecha", listaTurno); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String busquedaStatus = request.getParameter("busquedaStatus");
        if (busquedaStatus != null && !busquedaStatus.isEmpty()) {
            List<Turno> listaTurnos = control.buscarPorStatus(busquedaStatus);
            HttpSession miSesion = request.getSession();
            miSesion.setAttribute("listaTurnoStatus", listaTurnos);
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fechaString = request.getParameter("fecha");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        String tema = request.getParameter("tema");
        String descripcion = request.getParameter("descripcion");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");

        control.crearTurno(fecha, tema, descripcion, nombre, apellido, telefono);
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de turnos";
    }
}
