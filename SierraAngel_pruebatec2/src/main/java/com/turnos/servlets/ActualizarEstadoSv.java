package com.turnos.servlets;

import com.turnos.model.ControladoraModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ActualizarEstadoSv", urlPatterns = {"/ActualizarEstadoSv"})
public class ActualizarEstadoSv extends HttpServlet {

    private final ControladoraModel controladora = new ControladoraModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idTurno = Long.parseLong(request.getParameter("idTurno"));
        try {
            controladora.actualizarEstadoTurno(idTurno, "Atendido");
            request.setAttribute("mensaje", "Estado del turno actualizado a 'Atendido'");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al actualizar el estado: " + e.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}