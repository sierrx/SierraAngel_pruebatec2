<%@page import="java.util.List"%>
<%@page import="com.turnos.model.Turno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Turnos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h1>Alta de Turnos</h1>
            <form action="TurnoSv" method="POST">
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" required>
                </div>
                <div class="form-group">
                    <label for="tema">Asunto del Problema:</label>
                    <input type="text" class="form-control" id="tema" name="tema" required>
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción del Problema:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" required>
                </div>
                <div class="form-group">
                    <label for="telefono">Teléfono:</label>
                    <input type="text" class="form-control" id="telefono" name="telefono" required>
                </div>
                <button type="submit" class="btn btn-primary">Registrar Turno</button>
            </form>

            <h1 class="mt-4">Búsqueda</h1>

            <!-- Búsqueda por Fecha -->
            <form action="TurnoSv" method="GET">
                <div class="form-group">
                    <label for="busquedaFecha">Búsqueda por Fecha:</label>
                    <input type="date" class="form-control" id="busquedaFecha" name="busquedaFecha">
                </div>
                <button type="submit" class="btn btn-primary">Buscar por Fecha</button>
            </form>

            <!-- Búsqueda por Estatus -->
            <form action="TurnoSv" method="GET">
                <div class="form-group">
                    <label for="busquedaStatus">Búsqueda por Estatus:</label>
                    <select class="form-control" id="busquedaStatus" name="busquedaStatus">
                        <option value="">Selecciona un estatus</option>
                        <option value="En espera">En espera</option>
                        <option value="Atendido">Atendido</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Buscar por Estatus</button>
            </form>

            <!-- Mensaje de éxito o error -->
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null && !mensaje.isEmpty()) {
            %>
            <div class="alert alert-info mt-3">
                <%= mensaje %>
            </div>
            <%
                }
            %>

            <!-- Tabla de Resultados por Fecha -->
            <div class="results-table mt-4">
                <h3>Resultados por Fecha</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Asunto</th>
                            <th>Descripción</th>
                            <th>Ciudadano</th>
                            <th>Estatus</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Turno> listaTurnoFecha = (List<Turno>) session.getAttribute("listaTurnoFecha");
                            if (listaTurnoFecha != null && !listaTurnoFecha.isEmpty()) {
                                for (Turno turno : listaTurnoFecha) {
                        %>
                        <tr>
                            <td><%= turno.getFecha() %></td>
                            <td><%= turno.getTema() %></td>
                            <td><%= turno.getDescripcion() %></td>
                            <td><%= turno.getCiudadano().getNombre() + " " + turno.getCiudadano().getApellido() %></td>
                            <td><%= turno.getStatus() %></td>
                            <td>
                                <% if (!"Atendido".equals(turno.getStatus())) { %>
                                    <form action="ActualizarEstadoSv" method="POST" style="display:inline;">
                                        <input type="hidden" name="idTurno" value="<%= turno.getId() %>">
                                        <button type="submit" class="btn btn-success btn-sm">Atender</button>
                                    </form>
                                <% } %>
                                <form action="EliminarSv" method="POST" style="display:inline;">
                                    <input type="hidden" name="idTurno" value="<%= turno.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>

            <!-- Tabla de Resultados por Estatus -->
            <div class="results-table mt-4">
                <h3>Resultados por Estatus</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Asunto</th>
                            <th>Descripción</th>
                            <th>Ciudadano</th>
                            <th>Estatus</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Turno> listaTurnoStatus = (List<Turno>) session.getAttribute("listaTurnoStatus");
                            if (listaTurnoStatus != null && !listaTurnoStatus.isEmpty()) {
                                for (Turno turno : listaTurnoStatus) {
                        %>
                        <tr>
                            <td><%= turno.getFecha() %></td>
                            <td><%= turno.getTema() %></td>
                            <td><%= turno.getDescripcion() %></td>
                            <td><%= turno.getCiudadano().getNombre() + " " + turno.getCiudadano().getApellido() %></td>
                            <td><%= turno.getStatus() %></td>
                            <td>
                                <% if (!"Atendido".equals(turno.getStatus())) { %>
                                    <form action="ActualizarEstadoSv" method="POST" style="display:inline;">
                                        <input type="hidden" name="idTurno" value="<%= turno.getId() %>">
                                        <button type="submit" class="btn btn-success btn-sm">Atender</button>
                                    </form>
                                <% } %>
                                <form action="EliminarSv" method="POST" style="display:inline;">
                                    <input type="hidden" name="idTurno" value="<%= turno.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>

