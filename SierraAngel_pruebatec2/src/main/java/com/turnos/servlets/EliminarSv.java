package com.turnos.servlets;

import com.turnos.model.ControladoraModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EliminarSv", urlPatterns = {"/EliminarSv"})
public class EliminarSv extends HttpServlet {

    private final ControladoraModel control = new ControladoraModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTurno = request.getParameter("idTurno");

        try {
            Long id = Long.parseLong(idTurno);
            control.borrarTurnoLogico(id);
            request.setAttribute("mensaje", "Turno eliminado correctamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al eliminar el turno: " + e.getMessage());
        }

        // Redirigir a la página principal o a la búsqueda
        response.sendRedirect("index.jsp");
    }
}

